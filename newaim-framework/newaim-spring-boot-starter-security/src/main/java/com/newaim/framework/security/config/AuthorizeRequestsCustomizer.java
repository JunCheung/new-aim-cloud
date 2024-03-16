package com.newaim.framework.security.config;

import com.newaim.framework.web.config.WebProperties;
import jakarta.annotation.Resource;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * 自定义的 URL 的安全配置
 * 目的：每个 Maven Module 可以自定义规则！
 */
public abstract class AuthorizeRequestsCustomizer
        implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>, Ordered {

    @Resource
    private WebProperties webProperties;

    protected String buildAdminApi(String url) {
        return webProperties.getAdminApi().getPrefix() + url;
    }

    protected String buildPubApi(String url) {
        return webProperties.getPubApi().getPrefix() + url;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
