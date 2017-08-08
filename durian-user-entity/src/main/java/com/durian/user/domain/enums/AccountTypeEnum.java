package com.durian.user.domain.enums;

/**
 * 账户类型
 * Created by huangguang on 2017/7/13.
 */
public enum AccountTypeEnum {
    NORMAL("1", "普通账户");

    private String code;

    private String desc;

    AccountTypeEnum(String code, String desc) {
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
