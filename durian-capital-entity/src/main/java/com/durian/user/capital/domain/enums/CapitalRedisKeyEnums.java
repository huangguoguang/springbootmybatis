package com.durian.user.capital.domain.enums;

/**
 * Created by lj on 2017/4/12.
 */
public enum CapitalRedisKeyEnums {
    CAPITAL_ACCOUNT("user.{0}.capital", "用户资金账户");
    private String code;
    private String desc;

    CapitalRedisKeyEnums(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
