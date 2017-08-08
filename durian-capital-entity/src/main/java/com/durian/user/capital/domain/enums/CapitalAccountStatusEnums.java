package com.durian.user.capital.domain.enums;

/**
 * Created by lj on 2017/4/14.
 */
public enum CapitalAccountStatusEnums {
    DISABLE("0", "不可用"), ENABLE("1", "可用"), FREEZE("2", "冻结");

    private String code;
    private String desc;

    CapitalAccountStatusEnums(String code, String desc){
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
