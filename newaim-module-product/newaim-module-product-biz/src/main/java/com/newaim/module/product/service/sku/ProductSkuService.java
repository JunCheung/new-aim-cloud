package com.newaim.module.product.service.sku;

import com.newaim.module.product.dal.dataobject.sku.ProductSkuDO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品 SKU Service 接口
 */
public interface ProductSkuService {

    /**
     * 获得商品 SKU 信息
     *
     * @param skuId 商品 SKU 编号
     * @return 商品 SKU 信息
     */
    ProductSkuDO getSku(String skuId);

    Page<ProductSkuDO> getSkuPage(String query, Integer page, Integer size);

    List<ProductSkuDO> getSuggestions();
}
