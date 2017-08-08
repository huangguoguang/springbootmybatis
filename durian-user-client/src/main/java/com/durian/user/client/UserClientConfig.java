package com.durian.user.client;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangyang on 2017/5/24.
 */
@Configuration
@ComponentScan
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class UserClientConfig {
}
