package com.lny.mall.ware.dao;

import com.lny.mall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:48:42
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
    /**
     * 添加库存信息
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    /**
     * 查询库存总数
     * 锁定库存：下单后
     * 库存数减去锁定库存数得到总库存数
     * @param skuId
     * @return
     */
    Long getSkuStock(@Param("skuId")Long skuId);
}
