package com.newaim.module.product;

import com.newaim.module.product.dal.dataobject.sku.ProductSkuDO;
import com.newaim.module.product.dal.es.ProductSkuRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ESTest {

    @Resource
    private ProductSkuRepository productSkuRepository;

    @Test
    void add() {
        ProductSkuDO productSkuDO = new ProductSkuDO();
        productSkuDO.setSku("sku");
        productSkuDO.setTitle("title");
        productSkuDO.setDescription("description");
        productSkuRepository.save(productSkuDO);

    }

    @Test
    void init() {
        List<ProductSkuDO> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/sku_list.csv"))) {
            CSVReader csvReader = new CSVReader(br);
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                ProductSkuDO product = new ProductSkuDO();
                product.setSku(nextLine[0]);
                product.setTitle(nextLine[1]);
                product.setDescription(nextLine[2]);
                list.add(product);
                System.out.println(list.size());
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < list.size(); ) {
            int toIndex = i + 1000;
            if (toIndex > list.size()) toIndex = list.size();
            productSkuRepository.saveAll(list.subList(i, toIndex));
            i = toIndex;
            System.out.println(i);
        }


    }

    @Test
    void query() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<ProductSkuDO> queryResult = productSkuRepository.findByTitle("Everfit", pageRequest);
        System.out.println(queryResult.getTotalElements());
    }
}
