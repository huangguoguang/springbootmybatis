package com.durian.user.capital.domain.enums;

import com.platform.common.domain.exception.CustomExceptionCode;


/**
 * Created by lj on 2017/4/14.
 */
public enum CapitalExceptionEnums implements CustomExceptionCode {
    ACCOUNT_EXISTS(8000, "您的账户已经存在", 500),
    ACCOUNT_NOT_FOUND(8001, "您的资金账户未找到", 500),
    ACCOUNT_FREEZE(8003, "您的资金账户已冻结", 500),
    ACCOUNT_DISABLE(8004, "您的资金账户不可用", 500),
    CAPITAL_CHANGE_FAIL(8005, "资金变化失败", 500),
    CAPITAL_SCARCITY(8006, "您的余额不足", 500);


    private Integer code;

    private String msg;

    private Integer httpCode;

    CapitalExceptionEnums(Integer code, String msg, Integer httpCode) {
        this.code = code;
        this.msg = msg;
        this.httpCode = httpCode;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // 获得 enum 对象
    public static CapitalExceptionEnums get(Integer code) {
        for (CapitalExceptionEnums item : values()) {
            if (code.compareTo(item.getCode()) == 0) {
                return item;
            }
        }
        return null;
    }
}
