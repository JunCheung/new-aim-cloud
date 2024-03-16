package com.newaim.module.system.config;

import com.newaim.framework.swagger.config.NewaimSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi systemGroupedOpenApiPub() {
        return NewaimSwaggerAutoConfiguration.buildGroupedOpenApi("system-pub", "system", "/pub-api/");
    }
    @Bean
    public GroupedOpenApi systemGroupedOpenApiAdmin() {
        return NewaimSwaggerAutoConfiguration.buildGroupedOpenApi("system-admin", "system", "/admin-api/");
    }
}
