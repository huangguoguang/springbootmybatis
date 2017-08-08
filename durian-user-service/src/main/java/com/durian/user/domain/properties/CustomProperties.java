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

    @Value("${custom.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
