package com.durian.user.agent.client;

import com.durian.user.agent.thrift.api.service.UserAgentServiceApi;
import com.platform.common.thrift.client.annotation.ThriftLoadBalancerClient;
import com.platform.common.thrift.client.config.HttpThriftClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * Created by wz on 2017/8/11.
 */
@Configuration
@Import(HttpThriftClientAutoConfiguration.class)
public class UserAgentClientConfig {
		@ThriftLoadBalancerClient(serviceId = "user", url = "/api/agent")
		private UserAgentServiceApi.Iface userAgentServiceApi;

		@Bean
		@Scope("prototype")
		public UserAgentServiceApi.Iface userAgentServiceApi() {
				return this.userAgentServiceApi;
		}
}
