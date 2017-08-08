package com.durian.user.domain.enums;

/**
 * 收货地址是否默认枚举
 * Created by huangguang on 2017/7/6.
 */
public enum AddressStatusEnum {
    Default (1, "默认"),
    UnDefault (0, "非默认");

    private Integer code;
    private String statusDesc;

    AddressStatusEnum(Integer code, String statusDesc) {
        this.code = code;
        this.statusDesc = statusDesc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
