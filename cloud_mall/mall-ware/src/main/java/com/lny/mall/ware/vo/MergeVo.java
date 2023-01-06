package com.lny.mall.ware.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 合并采购需求
 */
@Data
public class MergeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 采购单id
     */
    private Long purchaseId;
    /**
     * 采购需求
     */
    private List<Long> items;

}
