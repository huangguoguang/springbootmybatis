package com.durian.user.domain.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义参数
 * <p>
 * Created by wangyang on 2017/5/13.
 */
@Configuration
public class CustomProperties {

    @Value("${token.stringKey}")
    private String stringKey;


    @Value("${token.owmuser}")
    private String owmuser;


    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getOwmuser() {
        return owmuser;
    }

    public void setOwmuser(String owmuser) {
        this.owmuser = owmuser;
    }
}
