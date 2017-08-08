package com.durian.user.capital.domain.enums;

/**
 * Created by lj on 2017/4/14.
 */
public enum RecordStatusEnums {
    INVALID("0", "无效"), VALID("1", "有效");

    private String code;
    private String desc;

    RecordStatusEnums(String code, String desc){
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
