package com.durian.user.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.durian.user.interceptor.AuthHandlerInterceptor;
import com.durian.user.interceptor.CustomCallableProcessingInterceptor;
import com.durian.user.interceptor.FunctionHandlerInterceptor;

/**
 * 拦截器注册
 *
 * @author WangYang
 * @version 1.0
 * @datetime 2017/2/28 17:55
 */
@Service("webMvcConfiguration")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private AuthHandlerInterceptor authHandlerInterceptor ;
	
	@Autowired
	private CustomCallableProcessingInterceptor customCallableProcessingInterceptor ;
	
	@Autowired
	private FunctionHandlerInterceptor functionHandlerInterceptor ;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.registerCallableInterceptors(customCallableProcessingInterceptor);
        configurer.setTaskExecutor(mvcTaskExecutor());//所借助的TaskExecutor
        configurer.setDefaultTimeout(30 * 1000L); //tomcat默认10秒
        super.configureAsyncSupport(configurer);
    }

    @Bean
    public ThreadPoolTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setQueueCapacity(100);
        executor.setMaxPoolSize(25);
        executor.setQueueCapacity(2000);
        executor.initialize();
        return executor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 认证拦截器
        InterceptorRegistration authInterceptor = registry.addInterceptor(authHandlerInterceptor);
        // 配置拦截的路径
        authInterceptor.addPathPatterns("/**");
        // 配置不拦截的路径
        authInterceptor.excludePathPatterns("/test/**");

        // 权限拦截器
        InterceptorRegistration functionInterceptor = registry.addInterceptor(functionHandlerInterceptor);
        // 配置拦截的路径
        functionInterceptor.addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "accessToken")
                .exposedHeaders("Content-Type", "accessToken")
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }
    
}
