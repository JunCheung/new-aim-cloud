package com.newaim.module.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("elasticsearch")
@Data
public class ElasticSearchProperties {

    private String host;

//    private String port;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    private String caFingerprint;

    private String crt;
}
