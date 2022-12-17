package com.lny.mall.coupon.dao;

import com.lny.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:54:42
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
