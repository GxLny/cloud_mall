/**
  * Copyright 2022 bejson.com 
  */
package com.lny.mall.product.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2022-12-28 16:50:48
 * 需要保存的spu信息集
 */
@Data
public class SpuSaveVo {
    /**
     * spu名称
     */
    private String spuName;
    /**
     * sup描述信息
     */
    private String spuDescription;
    /**
     * 分类id
     */
    private Long catalogId;
    /**
     * 名牌id
     */
    private Long brandId;
    /**
     * 商品重量
     */
    private BigDecimal weight;
    /**
     * 发布状态
     */
    private int publishStatus;
    /**
     * 商品介绍图
     */
    private List<String> decript;
    /**
     * 商品图集 每个颜色对应不用图片
     */
    private List<String> images;
    /**
     * 积分
     */
    private Bounds bounds;
    /**
     * 规则参数信息
     */
    private List<BaseAttrsVo> baseAttrs;
    /**
     * 销售属性信息
     */
    private List<Skus> skus;



}