package com.newaim.module.product.api.sku;

import com.newaim.framework.common.pojo.CommonResult;
import com.newaim.module.product.api.sku.dto.ProductSkuRespDTO;
import com.newaim.module.product.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 商品 SKU")
public interface ProductSkuApi {

    String PREFIX = ApiConstants.PREFIX + "/sku";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "查询 SKU 信息")
    @Parameter(name = "skuId", description = "SKU 编号", required = true, example = "1024")
    CommonResult<ProductSkuRespDTO> getSku(@RequestParam("skuId") String skuId);


}
