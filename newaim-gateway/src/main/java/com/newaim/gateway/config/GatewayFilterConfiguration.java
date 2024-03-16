package com.newaim.gateway.config;

import com.newaim.gateway.filter.limit.RateLimitFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class GatewayFilterConfiguration {

    @Value("${newaim.daily-limit:100}")
    private int dailyLimit;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, RedisTemplate<String, String> redisTemplate) {
        return builder.routes().route(r ->
                        r.path("/admin-api/product/sku/query")
                                .filters(f -> f.filter(new RateLimitFilter(redisTemplate, dailyLimit)))
                                .uri("lb://product-server"))
                .build();
    }
}
