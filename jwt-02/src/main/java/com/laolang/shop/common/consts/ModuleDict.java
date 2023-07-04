package com.laolang.shop.common.consts;

/**
 * 模块
 */
public enum ModuleDict {
    auth("auth", "登录与授权"),
    admin("admin", "后台");

    private final String code;
    private final String name;

    ModuleDict(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
