package com.durian.user.domain.enums;


import com.platform.common.domain.exception.CustomExceptionCode;

/**
 * 用户服务异常枚举
 * <p>
 * Created by wangyang on 2017/5/12.
 */
public enum TokenExceptionEnum implements CustomExceptionCode {

    TOKEN_EXIST(600, "用户名不存在", 500),
    TOKEN_NOT_EXIST(600, "用户名不存在", 500),
    TOKEN_EXPIRES_CODE(601, "用户信息已过期", 500)
    ;

    private Integer code;
    private String msg;
    private Integer httpCode;

    TokenExceptionEnum(Integer code, String msg, Integer httpCode) {
        this.code = code;
        this.msg = msg;
        this.httpCode = httpCode;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public void setCode(Integer integer) {

    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public void setMsg(String s) {

    }

    @Override
    public Integer getHttpCode() {
        return null;
    }

    @Override
    public void setHttpCode(Integer integer) {

    }
}
