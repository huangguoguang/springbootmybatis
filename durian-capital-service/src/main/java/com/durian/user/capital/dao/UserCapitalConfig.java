package com.durian.user.capital.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by lj on 2017/8/10.
 */
@Configuration
public class UserCapitalConfig {
		@Bean
		public ThreadPoolTaskExecutor userCapitalTaskExecutor() {
				ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
				executor.setCorePoolSize(10);
				executor.setQueueCapacity(100);
				executor.setMaxPoolSize(25);
				executor.setQueueCapacity(20000);
				executor.initialize();
				return executor;
		}
}
