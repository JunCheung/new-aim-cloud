package com.newaim.module.product.controller.admin.sku;

import com.newaim.framework.common.pojo.CommonResult;
import com.newaim.framework.common.pojo.PageResult;
import com.newaim.module.product.controller.admin.sku.vo.ProductQueryResp;
import com.newaim.module.product.controller.admin.sku.vo.ProductSkuRespVO;
import com.newaim.module.product.convert.sku.ProductSkuConvert;
import com.newaim.module.product.dal.dataobject.sku.ProductSkuDO;
import com.newaim.module.product.service.sku.ProductSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "管理后台 - 商品 SKU")
@RestController
@RequestMapping("/product/sku")
@Validated
public class ProductSkuController {
    @Resource
    private ProductSkuService productSkuService;

    @GetMapping("/query")
    @Operation(summary = "查询产品SKU")
    @SecurityRequirement(name = "Authorization")
    @Parameter(name = "query", description = "查询关键字")
    public CommonResult<ProductQueryResp> query(@RequestParam("query") String query,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        ProductQueryResp productQueryResp = new ProductQueryResp();
        Page<ProductSkuDO> skuPage = productSkuService.getSkuPage(query, page, size);
        PageResult<ProductSkuRespVO> pageResult = new PageResult<>();
        pageResult.setTotal(skuPage.getTotalElements());
        pageResult.setList(ProductSkuConvert.INSTANCE.convertList(skuPage.getContent()));
        productQueryResp.setPageResult(pageResult);
        if (pageResult.getList().isEmpty()) {
            List<ProductSkuDO> top20Sku = productSkuService.getSuggestions();
            productQueryResp.setSuggestions(ProductSkuConvert.INSTANCE.convertList(top20Sku));
        }
        return CommonResult.success(productQueryResp);
    }
}
