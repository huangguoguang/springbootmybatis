package com.durian.user.capital.domain.enums;

/**
 * 资金用途
 *
 * Created by lj on 2017/4/11.
 */
public enum CapitalPurposeEnums {
    RECHARGE("01", "充值"),
    WITHDRAW("02","提现"),
    SHOPPING("03", "购物"),
    REFUND("04", "退款"),
    BROKERAGE("05", "佣金");


    private String code;
    private String desc;

    CapitalPurposeEnums(String code, String desc){
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
