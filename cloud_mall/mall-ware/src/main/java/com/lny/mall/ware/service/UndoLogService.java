package com.lny.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 11:48:42
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

