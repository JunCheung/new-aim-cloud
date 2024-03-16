package com.newaim.module.product.config;

import com.newaim.framework.swagger.config.NewaimSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi productGroupedOpenApiPub() {
        return NewaimSwaggerAutoConfiguration.buildGroupedOpenApi("product-pub", "product", "/pub-api/");
    }

    @Bean
    public GroupedOpenApi productGroupedOpenApiAdmin() {
        return NewaimSwaggerAutoConfiguration.buildGroupedOpenApi("product-admin", "product", "/admin-api/");
    }
}
