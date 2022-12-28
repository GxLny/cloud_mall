package com.lny.mall.product.dao;

import com.lny.mall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:41
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {
    /**
     * 移除属性分组关联关系，支持批量删除
     * @param entities
     */
    void deleteBatchRelation(@Param("entities") List<AttrAttrgroupRelationEntity> entities);


}
