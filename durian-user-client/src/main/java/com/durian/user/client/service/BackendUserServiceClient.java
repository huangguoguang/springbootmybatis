package com.durian.user.client.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.durian.user.domain.to.UserAllInfo;


/**
 * 后台用户
 * @author hufeng
 *
 */
@FeignClient("durian-user")
@Service
public interface BackendUserServiceClient {
	
    /**
     * 注册用户
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/backendUser/register", method = RequestMethod.POST)
    UserAllInfo register(@RequestParam Map<String, Object> paramMap) throws Exception ;
    
    

    /**
     * 用户登录
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/backendUser/login", method = RequestMethod.POST)
    UserAllInfo login(@RequestParam Map<String, Object> paramMap) throws Exception;

    
    
    /**
     * 启用用户
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userCenter/enableUser", method = RequestMethod.POST)
    String enableUser(@RequestParam Map<String, Object> paramMap) throws Exception;
    
    
    /**
     * 禁用用户
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userCenter/disableUser", method = RequestMethod.POST)
    String disableUser(@RequestParam Map<String, Object> paramMap) throws Exception;

}
