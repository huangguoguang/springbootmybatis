package com.durian.user.domain.enums;


/**
 * 短信枚举
 * <p>
 * Created by wangyang on 2017/5/12.
 */
public enum UserSmsEnum {
    REGISTER("01", "用户注册"),
    LOGIN("02", "用户登录"),
    FIND_PWD("03", "找回密码"),
    REGISTER_CONTENT("04", "感谢您注册,手机验证码为{0},祝您购物愉快!"),
    FINDPWD_CONTENT("05", "感谢您使用重置密码手机验证码为{0},为了保证您的信息安全,请不要泄露此验证码,祝您购物愉快!");
    ;

    private String code;
    private String desc;

    private UserSmsEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static UserSmsEnum get(String code) {
        for (UserSmsEnum userSmsEnum :values()){
            if (userSmsEnum.getCode().equalsIgnoreCase(code)) {
                return userSmsEnum ;
            }
        }
        return null;
    }
}
