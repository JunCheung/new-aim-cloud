package com.newaim.module.product.controller.admin.sku.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "商品 SKU Response VO")
@Data
public class ProductSkuRespVO {

    @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sku;
    @Schema(description = "SPU 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    @Schema(description = "商品描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

}
