package com.newaim.module.product.dal.es;

import com.newaim.module.product.dal.dataobject.sku.ProductSkuDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkuRepository extends ElasticsearchRepository<ProductSkuDO, String> {
    Page<ProductSkuDO> findByTitle(String title, Pageable pageable);

    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"must\": [\n" +
            "        { \"function_score\": { \"random_score\": {} }}\n" +
            "      ]\n" +
            "    }\n" +
            "  }")
    List<ProductSkuDO> findRandom();
}
