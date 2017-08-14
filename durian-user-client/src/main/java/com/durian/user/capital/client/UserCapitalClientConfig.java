package com.durian.user.capital.client;

import com.durian.user.capital.thrift.api.service.UserCapitalServiceApi;
import com.platform.common.thrift.client.annotation.ThriftLoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Created by lj on 2017/8/11.
 */
//@Configuration
//@Import(HttpThriftClientAutoConfiguration.class)
public class UserCapitalClientConfig {
		@ThriftLoadBalancerClient(serviceId = "capital", url = "/api/capital")
		private UserCapitalServiceApi.Iface userCapitalServiceApi;

		@Bean
		@Scope("prototype")
		public UserCapitalServiceApi.Iface userCapitalServiceApi() {
				return this.userCapitalServiceApi;
		}
}
