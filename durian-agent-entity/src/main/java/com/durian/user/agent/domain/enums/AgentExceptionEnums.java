package com.durian.user.agent.domain.enums;

import com.platform.common.domain.exception.CustomExceptionCode;


/**
 * Created by xx on 2017/4/14.
 */
public enum AgentExceptionEnums implements CustomExceptionCode {
    AGENT_NOT_EXISTS(2000, "代理不存在", 500);


    private Integer code;

    private String msg;

    private Integer httpCode;

    AgentExceptionEnums(Integer code, String msg, Integer httpCode) {
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
    public static AgentExceptionEnums get(Integer code) {
        for (AgentExceptionEnums item : values()) {
            if (code.compareTo(item.getCode()) == 0) {
                return item;
            }
        }
        return null;
    }
}
