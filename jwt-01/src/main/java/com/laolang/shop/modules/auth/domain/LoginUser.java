package com.laolang.shop.modules.auth.domain;

import lombok.Data;

/**
 * 登录请求实体
 */
@Data
public class LoginUser {

    private String username;
    private String password;
}
