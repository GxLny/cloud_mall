package com.lny.mall.ware.dao;

import com.lny.mall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:48:42
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
