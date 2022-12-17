package com.lny.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:41
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

