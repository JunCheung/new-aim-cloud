package com.newaim.gateway.filter.limit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newaim.framework.common.enums.WebFilterOrderEnum;
import com.newaim.framework.common.pojo.CommonResult;
import com.newaim.gateway.util.SecurityFrameworkUtils;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;

import static com.newaim.framework.common.exception.enums.GlobalErrorCodeConstants.REQUESTS_LIMIT;

public class RateLimitFilter implements GatewayFilter, Ordered {

    private final RedisTemplate<String, String> redisTemplate;

    private final int dailyLimit;

    public RateLimitFilter(RedisTemplate<String, String> redisTemplate, int dailyLimit) {
        this.redisTemplate = redisTemplate;
        this.dailyLimit = dailyLimit;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Long userId = SecurityFrameworkUtils.getLoginUserId(exchange);
        if (userId == null) {
            return chain.filter(exchange);
        }
        String requestUrl = exchange.getRequest().getURI().getPath();
        String dailyLimitKeyPrefix = "dailyLimit:";
        String dailyLimitKey = dailyLimitKeyPrefix + userId + ":" + requestUrl + LocalDate.now();

        Long count = redisTemplate.opsForValue().increment(dailyLimitKey, 1);
        redisTemplate.expire(dailyLimitKey, Duration.ofDays(1)); // expire after 1 day

        if (count != null && count > dailyLimit) {
            ObjectMapper objectMapper = new ObjectMapper();
            CommonResult<?> result = CommonResult.error(REQUESTS_LIMIT);
            String jsonResult = objectMapper.writeValueAsString(result);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(jsonResult.getBytes(StandardCharsets.UTF_8));
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return WebFilterOrderEnum.TOKEN_AUTHENTICATION_FILTER + 1;
    }
}
