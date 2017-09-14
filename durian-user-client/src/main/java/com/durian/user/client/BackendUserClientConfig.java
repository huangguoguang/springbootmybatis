package com.durian.user.client;

import com.durian.user.thrift.api.service.BackendUserServiceApi;
import com.platform.common.thrift.client.annotation.ThriftHttpClient;
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
public class BackendUserClientConfig {

    @ThriftLoadBalancerClient(serviceId = "guess-user", url = "/api/backenduser")
    private BackendUserServiceApi.Iface backendUserServiceApi;

    @ThriftHttpClient(host = "127.0.0.1", port = 8088, url = "/api/backenduser")
    private BackendUserServiceApi.Iface backendUserServiceApiHttp;

    @Bean
    @Scope("prototype")
    public BackendUserServiceApi.Iface backendUserServiceApi() {
        return this.backendUserServiceApi;
    }


    @Bean
    @Scope("prototype")
    public BackendUserServiceApi.Iface backendUserServiceApiHttp() {
        return this.backendUserServiceApiHttp;
    }
}
