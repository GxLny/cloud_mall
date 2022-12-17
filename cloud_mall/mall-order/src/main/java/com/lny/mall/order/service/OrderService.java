package com.lny.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:52:26
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

