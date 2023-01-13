/**
  * Copyright 2022 bejson.com 
  */
package com.lny.mall.product.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2022-12-28 16:50:48
 * 销售属性
 */
@Data
public class Skus {
    /**
     * 销售属性信息
     */
    private List<Attr> attr;
    /**
     * 名称
     */
    private String skuName;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 标题
     */
    private String skuTitle;
    /**
     * 副标题
     */
    private String skuSubtitle;
    /**
     * 当前属性分类的图片
     */
    private List<Images> images;
    /**
     * 当前销售属性，所有信息
     */
    private List<String> descar;
    /**
     * 折扣值
     */
    private int fullCount;
    /**
     * 打折数
     */
    private BigDecimal discount;
    /**
     * 是否可以叠加折扣
     */
    private int countStatus;
    /**
     * 满减价格
     */
    private BigDecimal fullPrice;
    /**
     * 满减多少价格
     */
    private BigDecimal reducePrice;
    /**
     * 是否可以叠加优惠满减
     */
    private int priceStatus;
    /**
     * 会员价格
     */
    private List<MemberPrice> memberPrice;




}