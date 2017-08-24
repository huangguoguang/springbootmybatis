package com.durian.user.interceptor;

import com.durian.user.domain.annotation.NoLoginAuth;
import com.durian.user.utils.token.Token;
import com.durian.user.utils.token.TokenGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证拦截器
 *
 * @author WangYang
 * @version 1.0
 * @datetime 2017/2/28 17:47
 */
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	boolean result = true;
    	System.out.println("qingqiu:"+request.getRequestURI());
    	if(request.getRequestURI().equals("/error")) {
    		return true;
    	}
        LOGGER.info("AuthenticationInterceptor parameter[{}] :"+request2Map(request).toString());
        //加入了忽略登录标签,则不进行拦截.其他的都进行拦截
        boolean noLogin = isLoginAuthentication(handler);
        if(noLogin) {
        	result = true ;
        }else {
        	//Map<String, String> headersMap  = getHeadersInfo(request);
    		//LOGGER.info(headersMap.toString());
    		//LoginToken tokenInfo = parameter.getParameterAnnotation(LoginToken.class);
    		String accessToken = request.getParameter("accessToken");
    		Token token = TokenGenerator.validateToken(accessToken);
			if(token == null ) {
				LOGGER.info("服务器模拟accessToken异常,请传入Header token参数!");
				// throw new CustomException(TokenExceptionEnum.TOKEN_EXPIRES_CODE);
				result = true;
			}
        }
        LOGGER.info("***** AuthenticationInterceptor: path[{}]  result[{}] ******", request.getRequestURI(), result ? "Success" : "Failure");
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o,
                           ModelAndView modelAndView) throws Exception {
        LOGGER.info("这是认证拦截器 postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        LOGGER.info("这是认证拦截器 afterCompletion");
    }
    
    
    
    /**
     * 对登录进行拦截,如果指定注解,不进行拦截.
     * @param handler
     * @return
     */
    public boolean isLoginAuthentication(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			NoLoginAuth methodAnnotation = handlerMethod.getMethodAnnotation(NoLoginAuth.class);
			if (methodAnnotation == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
    
    

	/**
	 * get request headers
	 * @param request
	 * @return
	 */
	  private Map<String, String> getHeadersInfo(HttpServletRequest request) {
	    Map<String, String> map = new HashMap<String, String>();
	    Enumeration<String> headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        map.put(key, value);
	    }
	    return map;
	  }
	  
	  
	  private  Map<String, String> request2Map(HttpServletRequest req) {
	        Map<String, String> map = new HashMap<String, String>();
	        Enumeration<String> enums = req.getParameterNames();
	        while (enums.hasMoreElements()) {
	            String name = enums.nextElement();
	            String value = req.getParameter(name);
	            if (req.getParameterValues(name) != null) {
	                value = StringUtils.join(req.getParameterValues(name), ",");
	            }
	            map.put(name, value);
	        }
	        return map;
	    }

}
