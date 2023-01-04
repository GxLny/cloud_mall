package com.lny.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.product.entity.SpuInfoEntity;
import com.lny.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:40
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存spu所有信息
     * @param spuSaveVo
     */
    void saveSupInfo(SpuSaveVo spuSaveVo);

    /**
     * 保存spu基本信息
     * @param spuInfoEntity
     */
    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);
}

