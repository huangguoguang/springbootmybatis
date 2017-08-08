package com.durian.user.capital.domain.enums;

/**
 * 资金操作枚举
 *
 * Created by lj on 2017/4/11.
 */
public enum CapitalOperateEnums {
    ADD("add", "增加"),
    SUBTRACT("subtract", "减少");

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

    CapitalOperateEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
