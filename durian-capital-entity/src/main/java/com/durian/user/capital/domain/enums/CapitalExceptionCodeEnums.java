package com.durian.user.capital.domain.enums;

//import com.acl.mall.common.exception.CustomExceptionCode;
import com.platform.common.domain.exception.CustomExceptionCode;


/**
 * Created by lj on 2017/4/14.
 */
public enum CapitalExceptionCodeEnums implements CustomExceptionCode {
    ACCOUNT_EXISTS(8000, "您的账户已经存在", 500),
    ACCOUNT_NOT_EXISTS(8001,"您的账户不存在", 500),
    CHANGE_CAPITAL_FAIL(8002,"余额变更失败", 500),
    CAPITAL_SCARCITY(8003,"您的余额不足", 500),
    CAPITAL_NOT_FOUND(8004,"您的余额未找到", 500);

    private Integer code;

    private String msg;

    private Integer httpCode;

    CapitalExceptionCodeEnums(Integer code, String msg, Integer httpCode) {
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
    public static CapitalExceptionCodeEnums get(Integer code) {
        for (CapitalExceptionCodeEnums item : values()) {
            if (code.compareTo(item.getCode()) == 0) {
                return item;
            }
        }
        return null;
    }
}
