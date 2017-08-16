package com.durian.user.agent.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.platform.common.dispatcher.aop.DataSourceAspect;
import com.platform.common.dispatcher.config.param.DataSourceConfigParam;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据库连接配置
 * <p>
 * Created by wangyang on 2017/4/13.
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DataSourceConfigParam dataSourceConfigParam() {
        DataSourceConfigParam param = new DataSourceConfigParam();
        param.addDataSource("master", masterDataSource());
        param.addDataSource("slave", slaveDataSource());
        param.setDefaultDataSourceName("master");
        return param;
    }

    // 开启多数据源拦载
    @Bean
    public DataSourceAspect dataSourceAspect() {
        return new DataSourceAspect();
    }

}
