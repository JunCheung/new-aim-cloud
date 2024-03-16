package com.newaim.module.product.enums;

import com.newaim.framework.common.enums.RpcConstants;

/**
 * API 相关的枚举
 */
public class ApiConstants {

    /**
     * 服务名
     *
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public static final String NAME = "product-server";

    public static final String PREFIX = RpcConstants.RPC_API_PREFIX +  "/product";

    public static final String VERSION = "1.0.0";

}