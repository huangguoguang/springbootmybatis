package com.durian.user.capital.domain.enums;

/**
 * Created by lj on 2017/4/12.
 */
public enum CapitalRedisKeyEnums {
    OLD_CAPITAL_USER_PREFIX("wallet_{0}", "用户资金前缀");
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
