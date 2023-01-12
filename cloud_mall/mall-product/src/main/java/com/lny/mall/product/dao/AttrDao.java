package com.lny.mall.product.dao;

import com.lny.mall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:41
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
    /**
     * 在指定的所有属性集合里面，挑出检索属性
     * @param attrIds
     * @return
     */
    List<Long> selectSearchAttrIds(@Param("attrIds")List<Long> attrIds);
}
