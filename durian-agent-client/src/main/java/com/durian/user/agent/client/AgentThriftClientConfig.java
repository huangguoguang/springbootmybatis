package com.durian.user.agent.client;

import com.durian.user.agent.thrift.api.service.UserAgentServiceApi;
import com.platform.common.thrift.client.annotation.ThriftLoadBalancerClient;
import com.platform.common.thrift.client.config.HttpThriftClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * 用户客户端配置
 * <p>
 * Created by wangyang on 2017/5/22.
 */
@Configuration
@Import(HttpThriftClientAutoConfiguration.class)
public class AgentThriftClientConfig {

    @ThriftLoadBalancerClient(serviceId = "durian-agent-service", url = "/api/agent")
    private UserAgentServiceApi.Iface userAgentServiceApi;

    @Bean
    @Scope("prototype")
    public UserAgentServiceApi.Iface userAgentServiceApi() {
        return this.userAgentServiceApi;
    }

}
