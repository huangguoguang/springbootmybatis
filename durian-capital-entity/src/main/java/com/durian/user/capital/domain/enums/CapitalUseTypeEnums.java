package com.durian.user.capital.domain.enums;

/**
 * 资金流向类型
 *
 * Created by lj on 2017/4/11.
 */
public enum CapitalUseTypeEnums {
    SHOPPING("26", "购物"),
    REFUND("27", "退款");

    private String code;
    private String desc;

    CapitalUseTypeEnums(String code, String desc){
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
