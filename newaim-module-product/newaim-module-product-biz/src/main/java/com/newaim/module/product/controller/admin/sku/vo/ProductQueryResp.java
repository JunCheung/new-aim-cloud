package com.newaim.module.product.controller.admin.sku.vo;

import com.newaim.framework.common.pojo.PageResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "商品 SKU 查询 Response VO")
@Data
public class ProductQueryResp {

    private PageResult<ProductSkuRespVO> pageResult;

    private List<ProductSkuRespVO> suggestions;
}
