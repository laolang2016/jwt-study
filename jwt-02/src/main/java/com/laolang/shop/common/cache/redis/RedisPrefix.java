package com.laolang.shop.common.cache.redis;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RedisPrefix {

    // token 前缀
    public static final String AUTH_TOKEN_PREFIX = "auth::token::";
}
