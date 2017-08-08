package com.durian.user.capital.domain.enums;

/**
 * Created by lj on 2017/4/17.
 */
public enum CapitalLockEnums {
    LOCK_WALLET("wallet","redis"),
    LOCK_MYSQL_WALLET("mysql_wallet","mysql");

    private String code;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    CapitalLockEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
