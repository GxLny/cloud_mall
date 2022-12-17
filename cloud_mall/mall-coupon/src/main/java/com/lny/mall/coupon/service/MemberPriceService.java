package com.lny.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.coupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:54:42
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

