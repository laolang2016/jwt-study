package com.laolang.shop.common.consts;


import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalConst {
    /**
     * 管理员用户ID
     */
    public static final Long ADMIN_ID = 1L;

    public static final String ADMIN_ACCOUNT = "admin";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * jwt token claims 中的 user key
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    public static final String LIMIT_1 = "limit 1";
}
