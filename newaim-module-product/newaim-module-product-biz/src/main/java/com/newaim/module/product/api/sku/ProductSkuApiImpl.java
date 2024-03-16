package com.newaim.module.product.api.sku;

import com.newaim.framework.common.pojo.CommonResult;
import com.newaim.framework.common.util.object.BeanUtils;
import com.newaim.module.product.api.sku.dto.ProductSkuRespDTO;
import com.newaim.module.product.dal.dataobject.sku.ProductSkuDO;
import com.newaim.module.product.service.sku.ProductSkuService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static com.newaim.framework.common.pojo.CommonResult.success;

/**
 * 商品 SKU API 实现类
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class ProductSkuApiImpl implements ProductSkuApi {

    @Resource
    private ProductSkuService productSkuService;

    @Override
    public CommonResult<ProductSkuRespDTO> getSku(String skuId) {
        ProductSkuDO sku = productSkuService.getSku(skuId);
        return success(BeanUtils.toBean(sku, ProductSkuRespDTO.class));
    }

}
