package com.lny.mall.product.service.impl;

import com.lny.mall.product.service.CategoryBrandRelationService;
import com.lny.mall.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lny.common.utils.PageUtils;
import com.lny.common.utils.Query;

import com.lny.mall.product.dao.CategoryDao;
import com.lny.mall.product.entity.CategoryEntity;
import com.lny.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //获取所有数据
        List<CategoryEntity> entityList = baseMapper.selectList(null);

        List<CategoryEntity> level1Menus = entityList.stream()
                //获取父级菜单
                .filter((CategoryEntity parentMenus) -> {
                    return parentMenus.getParentCid() ==0;
                })//数据处理
                .map((CategoryEntity childrenMenus) ->{
                    childrenMenus.setChildren(getChildrenList(childrenMenus,entityList));
                    return childrenMenus;
                })//排序
                .sorted((CategoryEntity menu1,CategoryEntity menu2) ->{
                    return (menu1.getSort() == null?0:menu1.getSort()) - (menu2.getSort() == null?0:menu2.getSort());
                })
                //转换数据
                .collect(Collectors.toList());
        return level1Menus;
    }

    @Override
    public void deleteMenus(List<Long> asList) {
        // todo 检查当前删除的菜单，是否被别的地方引用
        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long attrGroupId) {
        List<Long> paths = new ArrayList<>();
        //递归查询父级id
        List<Long> parents = findParents(attrGroupId, paths);
        //把数据逆序排序
        Collections.reverse(parents);
        //转换成long数组格式
        return parents.toArray(new Long[paths.size()]);
    }

    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());

    }

    @Override
    public List<CategoryEntity> getLevel1Categorys() {

        return this.list(new QueryWrapper<CategoryEntity>().eq("parent_cid",0));
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        //将数据库的多次查询变为一次
        List<CategoryEntity> selectList = this.baseMapper.selectList(null);

        //1、查出所有分类
        //1、1）查出所有一级分类
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);

        //封装数据
        Map<String, List<Catelog2Vo>> parentCid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1、每一个的一级分类,查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());

            //2、封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName().toString());

                    //1、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParent_cid(selectList, l2.getCatId());

                    if (level3Catelog != null) {
                        List<Catelog2Vo.Category3Vo> category3Vos = level3Catelog.stream().map(l3 -> {
                            //2、封装成指定格式
                            Catelog2Vo.Category3Vo category3Vo = new Catelog2Vo.Category3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());

                            return category3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(category3Vos);
                    }

                    return catelog2Vo;
                }).collect(Collectors.toList());
            }

            return catelog2Vos;
        }));

        return parentCid;
    }

    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList,Long parentCid) {
        List<CategoryEntity> categoryEntities = selectList.stream().filter(item -> item.getParentCid().equals(parentCid)).collect(Collectors.toList());
        return categoryEntities;
    }

    /**
     * 递归查询父级id
     * @param attrGroupId
     * @param paths
     * @return
     */
    private List<Long> findParents(Long attrGroupId,List<Long> paths){
        //添加当前节点id
        paths.add(attrGroupId);
        //查询当前节点信息
        CategoryEntity categoryEntity = this.getById(attrGroupId);
        //如果父节点id不为空，则继续往上级查询
        if(categoryEntity.getParentCid() != 0){
            findParents(categoryEntity.getParentCid(),paths);
        }
        return paths;
    }

    /**
     * 递归获取菜单的子菜单
     * @param root 当前菜单
     * @param all 所有菜单
     * @return
     */
    private List<CategoryEntity> getChildrenList(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> childrenList = all.stream()
                //获取父级菜单
                .filter((CategoryEntity parentMenus) -> {
                    return parentMenus.getParentCid() == root.getCatId();
                })//数据处理
                .map((CategoryEntity childrenMenus) ->{
                    //找子菜单
                    childrenMenus.setChildren(getChildrenList(childrenMenus,all));
                    return childrenMenus;
                })//排序
                .sorted((CategoryEntity menu1,CategoryEntity menu2) ->{
                    return (menu1.getSort() == null?0:menu1.getSort()) - (menu2.getSort() == null?0:menu2.getSort());
                })
                //转换数据
                .collect(Collectors.toList());
        return childrenList;

    }

}