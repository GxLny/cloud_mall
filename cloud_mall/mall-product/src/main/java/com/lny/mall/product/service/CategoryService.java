package com.lny.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.product.entity.CategoryEntity;
import com.lny.mall.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:41
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取数据结构
     * @return
     */
    List<CategoryEntity> listWithTree();

    /**
     * 逻辑删除数据
     * @param asList
     */
    void deleteMenus(List<Long> asList);

    /**
     * 查询子分类完整路径
     * @param attrGroupId
     * @return
     */
    Long[] findCatelogPath(Long attrGroupId);

    /**
     * 级联更新分类
     * @param category
     */
    void updateCascade(CategoryEntity category);

    /**
     * 查出所有的一级分类
     * @return
     */
    List<CategoryEntity> getLevel1Categorys();

    /**
     * 获取二级分类和三级分类
     * @return
     */
    Map<String, List<Catelog2Vo>> getCatalogJson();
}

