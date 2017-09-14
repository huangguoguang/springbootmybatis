package com.durian.user.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import java.util.concurrent.Callable;

/**
 * 自定义回调处理拦截器
 *
 * @author WangYang
 * @version 1.0
 * @datetime 2017/2/8 17:27
 */
@Component
public class CustomCallableProcessingInterceptor implements CallableProcessingInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomCallableProcessingInterceptor.class);

    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        LOGGER.debug("beforeConcurrentHandling");
    }

    @Override
    public <T> void preProcess(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        LOGGER.debug("preProcess");
    }

    @Override
    public <T> void postProcess(NativeWebRequest nativeWebRequest, Callable<T> callable, Object o) throws Exception {
        LOGGER.debug("postProcess");
    }

    @Override
    public <T> Object handleTimeout(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        LOGGER.debug("handleTimeout");
        return null;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        LOGGER.debug("afterCompletion");
    }
}
