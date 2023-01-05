package com.lny.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.to.SkuReductionTo;
import com.lny.common.utils.PageUtils;
import com.lny.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:54:43
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 远程调用满减信息
     * @param skuReductionTo
     */
    void saveSkuReduction(SkuReductionTo skuReductionTo);
}

