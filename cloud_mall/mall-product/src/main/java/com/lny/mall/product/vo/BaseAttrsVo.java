/**
  * Copyright 2022 bejson.com 
  */
package com.lny.mall.product.vo;

import lombok.Data;

/**
 * 规格参数属性信息
 */
@Data
public class BaseAttrsVo {
    /**
     * 属性分类id
     */
    private Long attrId;
    /**
     * 属性值
     */
    private String attrValues;
    private int showDesc;


}