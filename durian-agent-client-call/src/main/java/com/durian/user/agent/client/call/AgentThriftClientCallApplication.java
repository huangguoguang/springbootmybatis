package com.durian.user.agent.client.call;

//import com.platform.common.dispatcher.CommonDispatcherConfig;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 用户服务启动类
 * <p>
 * Created by wangyang on 2017/5/11.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, TransactionAutoConfiguration.class})
@ComponentScan("com.durian")
@EnableAspectJAutoProxy
@EnableAsync
@EnableDiscoveryClient
@EnableCircuitBreaker // 注解开启断路器功能
public class AgentThriftClientCallApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AgentThriftClientCallApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
    }

}
