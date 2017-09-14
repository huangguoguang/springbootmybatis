package com.durian.user.client;

import com.durian.user.thrift.api.service.BackendStatisticsServiceApi;
import com.platform.common.thrift.client.annotation.ThriftLoadBalancerClient;
import com.platform.common.thrift.client.config.HttpThriftClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * Created by lj on 2017/9/14.
 */
@Configuration
@Import(HttpThriftClientAutoConfiguration.class)
public class BackendStatisticsClientConfig {
		@ThriftLoadBalancerClient(serviceId = "guess-user", url = "/api/backendStatistics")
		private BackendStatisticsServiceApi.Iface backendStatisticsServiceApi;

		@Bean
		@Scope("prototype")
		public BackendStatisticsServiceApi.Iface backendStatisticsServiceApi() {
				return this.backendStatisticsServiceApi;
		}
}
