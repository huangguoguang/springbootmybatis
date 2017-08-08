package com.durian.user.domain.enums;

/**
 * 登录类型
 * Created by huangguang on 2017/7/13.
 */
public enum LoginTypeEnum {
    PC("1",  "pc"),//pc端登录
    ANDROID("2", "Android"),//Android移动客户端
    IPAD("3", "iPad"),//iPad客户端
    IPHONE("4", "iPhone"),//iPhone移动客户端
    BACKEND("5", "backend");//后台登录
    private String code;

    private String type;

    LoginTypeEnum(String code, String type) {
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
