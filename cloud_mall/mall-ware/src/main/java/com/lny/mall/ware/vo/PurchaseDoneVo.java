package com.lny.mall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 完成采购单
 */
@Data
public class PurchaseDoneVo {
    @NotNull(message = "id不允许为空")
    private Long id; //采购id

    private List<PurchaseItemDoneVo> items;

}
