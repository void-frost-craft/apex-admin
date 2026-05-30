package com.apex.common.core.domain;

import java.io.Serializable;

/**
 * 统一响应结果
 *
 * @author apex
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 状态码 */
    private int code;

    /** 消息 */
    private String msg;

    /** 数据 */
    private T data;

    /** 时间戳 */
    private long timestamp = System.currentTimeMillis();

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;
    public static final int UNAUTHORIZED_CODE = 401;
    public static final int FORBIDDEN_CODE = 403;

    public R() {
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> ok() {
        return new R<>(SUCCESS_CODE, "操作成功");
    }

    public static <T> R<T> ok(T data) {
        return new R<>(SUCCESS_CODE, "操作成功", data);
    }

    public static <T> R<T> ok(String msg) {
        return new R<>(SUCCESS_CODE, msg);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<>(SUCCESS_CODE, msg, data);
    }

    public static <T> R<T> fail() {
        return new R<>(FAIL_CODE, "操作失败");
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(FAIL_CODE, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> unauthorized() {
        return new R<>(UNAUTHORIZED_CODE, "未授权");
    }

    public static <T> R<T> unauthorized(String msg) {
        return new R<>(UNAUTHORIZED_CODE, msg);
    }

    public static <T> R<T> forbidden() {
        return new R<>(FORBIDDEN_CODE, "禁止访问");
    }

    public static <T> R<T> forbidden(String msg) {
        return new R<>(FORBIDDEN_CODE, msg);
    }

    public boolean isSuccess() {
        return SUCCESS_CODE == this.code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
