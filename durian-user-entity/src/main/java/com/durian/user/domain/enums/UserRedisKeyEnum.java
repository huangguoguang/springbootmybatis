package com.durian.user.domain.enums;

/**
 * 用户RedisKey
 * <p>
 * Created by wangyang on 2017/5/11.
 */
public enum UserRedisKeyEnum {

    USER("user:{0}"),
    INFO("info"),
    TOKEN_REFRESH_FREQUENCY("token_refresh_frequency"),  

    TOKEN_ACCESS(":token:access"),
    TOKEN_REFRESH(":token:refresh"),
    ;

    private String key;

    UserRedisKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
