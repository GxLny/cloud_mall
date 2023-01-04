package com.lny.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lny.common.utils.PageUtils;
import com.lny.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author lny
 * @email lny@gmail.com
 * @date 2022-12-16 10:14:40
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存商品规格参数
     * @param collect
     */
    void saveProductAttr(List<ProductAttrValueEntity> collect);
}

