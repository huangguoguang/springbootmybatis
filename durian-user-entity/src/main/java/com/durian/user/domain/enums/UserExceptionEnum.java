package com.durian.user.domain.enums;


import com.platform.common.domain.exception.CustomExceptionCode;

/**
 * 用户服务异常枚举
 * <p>
 * Created by wangyang on 2017/5/12.
 */
public enum UserExceptionEnum implements CustomExceptionCode {

    USER_EXIST(600, "用户名已存在", 500),
    USER_NOT_EXIST(600, "用户名不存在", 500),
    USER_IMAGE_CODE(601, "图片验证码错误", 500),
    USER_MOBLLE_CODE_NULL(602, "手机短信验证码错误", 500),
    USER_MOBLLE_CODE_ERROR(602, "手机短信验证码错误", 500),
    USER_NIKENAME_NULL(603, "昵称不能为空", 500),
    USER_MOBILE_NULL(604, "手机号码不能为空", 500),
    USER_MOBILE_FORMAT(605, "手机号码格式不正确", 500),
    USER_MOBILE_PASSWORD(606, "用户名或密码错误", 500),
    USER_MOBILE_DISABLE(607, "用户名已经禁用", 500),
    USER_MOBILE_LOGINPASSWORDLIMITED(608, "登录次数受限制", 500),
    USER_ADDRESS_MAX(609, "绑定的收货地址已达上限", 500),
    USER_NICKICON_ERROR(610, "编辑图像失败", 500),
    USER_ID_NULL(611,"用户Id为空",500),
    USER_PASSWORD_ERROR(612, "用户密码错误", 500),
    MODIFY_PASSWORD_ERROR(613, "修改密码失败", 500)
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
        return null;
    }

    @Override
    public void setCode(Integer integer) {

    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public void setMsg(String s) {

    }

    @Override
    public Integer getHttpCode() {
        return null;
    }

    @Override
    public void setHttpCode(Integer integer) {

    }
}
