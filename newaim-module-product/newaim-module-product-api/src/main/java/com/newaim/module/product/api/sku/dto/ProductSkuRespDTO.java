package com.newaim.module.product.api.sku.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 商品 SKU 信息")
@Data
public class ProductSkuRespDTO {

    @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String skuId;
    @Schema(description = "SPU 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    @Schema(description = "商品描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
