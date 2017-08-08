package com.durian.user.client.service;

import java.util.Map;

import com.platform.common.domain.result.ResultDataStruct;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.durian.user.domain.to.UserAllInfo;

/**
 * Created by hufeng on 2017/6/13.
 */
@FeignClient("durian-user")
@Service
public interface LoginServiceClient {

    /**
     * 用户登录
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    ResultDataStruct<UserAllInfo> login(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 用户刷新token
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/token/refresh", method = RequestMethod.POST)
    ResultDataStruct<UserAllInfo> refreshToken(@RequestParam Map<String, Object> paramMap) throws Exception;


}
