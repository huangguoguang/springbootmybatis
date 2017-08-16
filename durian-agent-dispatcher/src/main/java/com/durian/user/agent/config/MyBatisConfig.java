package com.durian.user.agent.config;

import com.platform.common.dispatcher.config.param.MyBatisConfigParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis扫描配置
 * <p>
 * Created by wangyang on 2017/5/13.
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public MyBatisConfigParam myBatisConfigParam() {
        MyBatisConfigParam properties = new MyBatisConfigParam();
        properties.setBasePackage("com.durian.**.mapper");
        properties.setMapperLocations("classpath*:mapper/*.xml");
        properties.setTypeAliasesPackage("com.durian.**.domain");
        return properties;
    }

}
