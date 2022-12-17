package com.lny.mall.product.dao;

import com.lny.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:41
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
