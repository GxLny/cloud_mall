package com.lny.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:40
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存sku基本信息
     * @param skuInfoEntity
     */
    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);
}

