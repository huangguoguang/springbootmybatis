package com.durian.user.client;

import com.durian.user.thrift.api.service.UserServiceApi;
import com.platform.common.thrift.client.annotation.ThriftLoadBalancerClient;
import com.platform.common.thrift.client.config.HttpThriftClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * Created by wangyang on 2017/5/24.
 */
@Configuration
@Import(HttpThriftClientAutoConfiguration.class)
public class UserClientConfig {

    @ThriftLoadBalancerClient(serviceId = "user", url = "/api/user")
    private UserServiceApi.Iface userServiceApi;

    @Bean
    @Scope("prototype")
    public UserServiceApi.Iface userServiceApi() {
        return this.userServiceApi;
    }

}
