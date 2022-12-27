package com.lny.mall.product.service.impl;

import com.lny.mall.product.service.CategoryBrandRelationService;
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