package com.durian.user.config;

import com.platform.common.thrift.context.rpc.RpcContextParamsBean;
import com.platform.common.thrift.service.config.HttpThriftServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Bean配置
 * <p>
 * Created by wangyang on 2017/4/11.
 */
@Configuration
@Import(HttpThriftServiceAutoConfiguration.class)
@ConditionalOnWebApplication
public class BeanConfig {

    @Bean
    public RpcContextParamsBean contextParamsBean() {
        RpcContextParamsBean bean = new RpcContextParamsBean();
        return bean.addProperty("params");
    }

}
