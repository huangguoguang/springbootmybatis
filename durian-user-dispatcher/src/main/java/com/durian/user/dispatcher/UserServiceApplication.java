package com.durian.user.dispatcher;

import com.platform.common.dispatcher.CommonDispatcherConfig;
import com.platform.common.service.redis.config.DistributedLockConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 用户服务启动类
 * <p>
 * Created by wangyang on 2017/5/11.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, TransactionAutoConfiguration.class, MybatisAutoConfiguration.class})
@Import({CommonDispatcherConfig.class, DistributedLockConfig.class})
@ComponentScan("com.durian")
@EnableAspectJAutoProxy
@EnableAsync
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserServiceApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
    }

}
