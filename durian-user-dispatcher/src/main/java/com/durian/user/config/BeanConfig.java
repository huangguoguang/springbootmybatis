package com.durian.user.config;

import com.anchol.common.component.distributedlock.RedisBasedDistributedLock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Bean配置
 * <p>
 * Created by wangyang on 2017/4/11.
 */
@Configuration
public class BeanConfig {

    @Bean
    public RedisBasedDistributedLock distributedLock(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        RedisBasedDistributedLock distributedLock = new RedisBasedDistributedLock();
        distributedLock.setRedisTemplate(redisTemplate);
        return distributedLock;
    }

}
