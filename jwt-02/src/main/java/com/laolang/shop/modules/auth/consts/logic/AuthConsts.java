package com.laolang.shop.modules.auth.consts.logic;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthConsts {

    /**
     * token 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    // request 用户信息名称
    public static final String AUTH_USER_ATTR_NAME = "authUser";
}
