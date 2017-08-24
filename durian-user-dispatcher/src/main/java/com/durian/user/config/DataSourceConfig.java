package com.durian.user.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.platform.common.dispatcher.aop.DataSourceAspect;
import com.platform.common.dispatcher.config.param.DataSourceConfigParam;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
        List<Filter> filterList=new ArrayList<>();
        filterList.add(wallFilter());
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(filterList);
        return druidDataSource;

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


    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter=new WallFilter();
        wallFilter.setConfig(wallConfig());
        return  wallFilter;
    }
    @Bean
    public WallConfig wallConfig(){
        WallConfig config =new WallConfig();
        config.setMultiStatementAllow(true); //允许一次执行多条语句
//        config.setNoneBaseStatementAllow(true); //允许非基本语句的其他语句
        return config;
    }
}
