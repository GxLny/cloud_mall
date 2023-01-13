package com.lny.mall.search.service;

import com.lny.common.to.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {
    /**
     * 批量保存上架商品
     * @param skuEsModels
     * @return
     */
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
