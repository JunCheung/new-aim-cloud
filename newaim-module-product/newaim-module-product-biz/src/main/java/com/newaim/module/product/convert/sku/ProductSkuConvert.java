package com.newaim.module.product.convert.sku;

import com.newaim.module.product.controller.admin.sku.vo.ProductSkuRespVO;
import com.newaim.module.product.dal.dataobject.sku.ProductSkuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品 SKU Convert
 */
@Mapper
public interface ProductSkuConvert {

    ProductSkuConvert INSTANCE = Mappers.getMapper(ProductSkuConvert.class);

    ProductSkuRespVO convert(ProductSkuDO productSkuDO);

    List<ProductSkuRespVO> convertList(List<ProductSkuDO> productSkuDOList);

}
