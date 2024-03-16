package com.newaim.module.product.service.sku;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.newaim.module.product.dal.dataobject.sku.ProductSkuDO;
import com.newaim.module.product.dal.es.ProductSkuRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 商品 SKU Service 实现类
 */
@Service
@Validated
public class ProductSkuServiceImpl implements ProductSkuService {

    @Resource
    private ProductSkuRepository productSkuRepository;

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Resource
    ElasticsearchClient elasticsearchClient;

    @Override
    public ProductSkuDO getSku(String skuId) {
        return productSkuRepository.findById(skuId).orElse(null);
    }

    @Override
    public Page<ProductSkuDO> getSkuPage(String query, Integer page, Integer size) {
        return productSkuRepository.findByTitle(query, PageRequest.of(page, size));
    }

    @Override
    public List<ProductSkuDO> getSuggestions() {
        Query query = new StringQuery("""
                {
                    "function_score": {
                      "query": {
                        "match_all": {}
                      },
                      "random_score": {},
                      "boost_mode": "replace"
                    }
                  }
                """);

        SearchHits<ProductSkuDO> search = elasticsearchOperations.search(query, ProductSkuDO.class);

        return search.getSearchHits().stream().map(SearchHit::getContent).toList();
    }

}
