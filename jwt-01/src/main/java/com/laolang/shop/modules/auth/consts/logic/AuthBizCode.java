package com.laolang.shop.modules.auth.consts.logic;

import com.laolang.shop.common.consts.IBizCode;

public enum AuthBizCode implements IBizCode {
    login_expired("auth_0001", "token 已过期"),
    error_token("auth_0002", "非法的 token");

    /**
     * 业务状态码
     */
    private final String code;

    /**
     * 提示信息
     */
    private final String msg;

    AuthBizCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
