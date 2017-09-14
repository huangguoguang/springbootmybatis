package com.durian.user.controller;


import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.domain.annotation.NoLoginAuth;
import com.durian.user.domain.to.LoginUser;
import com.durian.user.service.LoginService;

/**
 * 用户登录
 * @author hufeng
 *
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;
    

    /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    @NoLoginAuth
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Callable<UserAllInfo> login(@ModelAttribute LoginUser loginUser) throws Exception {
        LOGGER.info("用户登录:"+ JSONObject.toJSONString(loginUser));
        return () -> loginService.login(loginUser);
    }


    @RequestMapping(value = "token/refresh", method = RequestMethod.POST)
    public Callable<UserAllInfo> refreshToken(String refreshToken,String type) throws Exception {
        LOGGER.info("用户登录历史:"+type+"  token : " + refreshToken);
        return () -> loginService.refreshToken(refreshToken,type);
    }


}
