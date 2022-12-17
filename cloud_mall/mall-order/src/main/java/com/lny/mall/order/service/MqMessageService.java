package com.lny.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.order.entity.MqMessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:52:26
 */
public interface MqMessageService extends IService<MqMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

