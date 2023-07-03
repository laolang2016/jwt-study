package com.laolang.shop.modules.auth.domain;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class AuthUser {

    private Long id;
    private String username;
    private String uuid;
}
