package com.laolang.shop.common.domain;

import com.laolang.shop.common.consts.StatusCodeConst;
import com.laolang.shop.common.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class R<T> {

    /**
     * 接口请求结果的业务状态吗
     */
    private String code;

    /**
     * 判断接口请求是否成功的唯一标识
     */
    private Boolean success;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据体
     */
    private T body;

    /**
     * 扩充字段,正常情况下此字段为空，当此字段有值时，意味着当前接口结构不稳定，以后会修改,即保持 extra 为空
     */
    private Object extra;

    public static <T> R<T> build(String code, boolean success, String msg, T body) {
        R<T> ajax = new R<>();
        ajax.setCode(code);
        ajax.setSuccess(success);
        ajax.setMsg(msg);
        ajax.setBody(body);
        ajax.setExtra(null);
        return ajax;
    }

    public void setPropFromBusinessException(BusinessException e) {
        setMsg(e.getMsg());
        setCode(e.getCode());
        setSuccess(false);
    }

    public static <T> R<T> ok() {
        return build(StatusCodeConst.OK.getCode(), true, StatusCodeConst.OK.getMsg(), null);
    }

    public static <T> R<T> ok(String code, String msg) {
        return build(code, true, msg, null);
    }

    public static <T> R<T> ok(String code, String msg, T body) {
        return build(code, true, msg, body);
    }

    public static <T> R<T> ok(T body) {
        return build(StatusCodeConst.OK.getCode(), true, StatusCodeConst.OK.getMsg(), body);
    }

    public static <T> R<T> failed() {
        return build(StatusCodeConst.FAILED.getCode(), false, StatusCodeConst.FAILED.getMsg(), null);
    }

    public static <T> R<T> failed(String msg) {
        return build(StatusCodeConst.FAILED.getCode(), false, msg, null);
    }

    public static <T> R<T> doOverdue() {
        return build(StatusCodeConst.OVERDUE.getCode(), false, StatusCodeConst.OVERDUE.getMsg(), null);
    }

    public static <T> R<T> forbid() {
        return build(StatusCodeConst.FORBID.getCode(), false, StatusCodeConst.FORBID.getMsg(), null);
    }

    public static <T> R<T> error() {
        return build(StatusCodeConst.ERROR.getCode(), false, StatusCodeConst.ERROR.getMsg(), null);
    }

    public static <T> R<T> error(String msg) {
        return build(StatusCodeConst.ERROR.getCode(), false, msg, null);
    }

    public static <T> R<T> error(String code, String msg) {
        return build(code, false, msg, null);
    }

    public static <T> R<T> doFixing() {
        return build(StatusCodeConst.FIXING.getCode(), false, StatusCodeConst.FIXING.getMsg(), null);
    }

    public static <T> R<T> notFount() {
        return build(StatusCodeConst.NOT_FOUND.getCode(), false, StatusCodeConst.NOT_FOUND.getMsg(), null);
    }

    public static <T> R<T> badRequest() {
        return build(StatusCodeConst.BAD_REQUEST.getCode(), false, StatusCodeConst.BAD_REQUEST.getMsg(), null);
    }

}

