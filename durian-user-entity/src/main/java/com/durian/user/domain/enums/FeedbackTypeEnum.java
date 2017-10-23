package com.durian.user.domain.enums;

/**
 * Created by wangli on 2017/10/20.
 */
public enum FeedbackTypeEnum {
    CODE("1","二维码"),
    FEEDBACK("2","意见反馈"),
    USE("0","可用"),
    NOUSE("1","不可用");

    private String code;

    private String type;

    FeedbackTypeEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
