package com.laolang.shop.common.consts;

public enum StatusCodeConst implements IBizCode {

    /**
     * 业务操作成功
     */
    OK("200", "操作成功"),
    /**
     * 业务操作失败
     */
    FAILED("1", "操作失败"),
    /**
     * 用户未登录或登录过期
     */
    OVERDUE("401", "token过期或不存在"),
    /**
     * 无权操作
     */
    FORBID("403", "无权操作"),
    /**
     * 请求地址不存在
     */
    NOT_FOUND("404", "请求地址不存在"),
    /**
     * 请求参数有误
     */
    BAD_REQUEST("405", "请求参数有误"),
    /**
     * 服务器内部错误
     */
    ERROR("500", "服务器内部错误"),
    /**
     * 维护中
     */
    FIXING("502", "维护中");

    /**
     * 业务状态码
     */
    private final String code;

    /**
     * 提示信息
     */
    private final String msg;

    StatusCodeConst(String code, String msg) {
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
