package com.lny.mall.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo{

    /**
     * 所属分类名称
     */
    private String catelogName;
    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 完整路径
     */
    private Long[] catelogPath;


}
