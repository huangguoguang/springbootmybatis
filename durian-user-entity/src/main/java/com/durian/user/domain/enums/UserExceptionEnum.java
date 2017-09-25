package com.durian.user.domain.enums;


import com.platform.common.domain.exception.CustomExceptionCode;

/**
 * 用户服务异常枚举
 * <p>
 * Created by wangyang on 2017/5/12.
 */
public enum UserExceptionEnum implements CustomExceptionCode {

    USER_EXIST(600, "手机号已存在", 888),
    USER_NOT_EXIST(600, "用户不存在", 888),
    USER_IMAGE_CODE(601, "图片验证码错误", 500),
    USER_MOBLLE_CODE_NULL(602, "手机短信验证码错误", 888),
    USER_NIKENAME_NULL(603, "昵称不能为空", 888),
    USER_MOBILE_NULL(604, "手机号码不能为空", 888),
    USER_PASSWORD_NULL(604, "用户密码不能为空", 888),
    USER_MOBILE_FORMAT(605, "手机号码格式不正确", 888),
    USER_MOBILE_PASSWORD(606, "用户名或密码错误", 888),
    USER_MOBILE_DISABLE(607, "用户已经禁用", 888),
    USER_MOBILE_LOGINPASSWORDLIMITED(608, "登录次数受限制", 888),
    USER_ADDRESS_MAX(609, "绑定的收货地址已达上限", 888),
    USER_NICKICON_ERROR(610, "编辑图像失败", 888),
    USER_ID_NULL(611,"用户Id为空",888),
    USER_PASSWORD_ERROR(612, "用户密码错误", 888),
    USER_OLD_PASSWORD_ERROR(612, "旧密码错误", 888),
    MODIFY_PASSWORD_ERROR(613, "修改密码失败", 888),
    USER_YAOQING_CODE_NULL(614, "邀请码错误", 888),
    USER_SMS_CODE_ERROR(602, "发送短信验证码错误", 888),
    USER_PASSWORD_FORMAT_NULL(605, "用户密码长度错误", 888),
    ;

    private Integer code;
    private String msg;
    private Integer httpCode;

    UserExceptionEnum(Integer code, String msg, Integer httpCode) {
        this.code = code;
        this.msg = msg;
        this.httpCode = httpCode;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Integer getHttpCode() {
        return this.httpCode;
    }

    @Override
    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }
}
