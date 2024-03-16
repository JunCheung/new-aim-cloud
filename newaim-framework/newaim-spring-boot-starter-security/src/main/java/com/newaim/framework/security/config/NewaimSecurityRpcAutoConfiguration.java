package com.newaim.framework.security.config;

import com.newaim.framework.security.core.rpc.LoginUserRequestInterceptor;
import com.newaim.module.system.api.oauth2.OAuth2TokenApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Security 使用到 Feign 的配置项
 */
@AutoConfiguration
@EnableFeignClients(clients = {OAuth2TokenApi.class})
public class NewaimSecurityRpcAutoConfiguration {

    @Bean
    public LoginUserRequestInterceptor loginUserRequestInterceptor() {
        return new LoginUserRequestInterceptor();
    }

}
