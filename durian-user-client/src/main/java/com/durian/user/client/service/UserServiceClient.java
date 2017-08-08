package com.durian.user.client.service;

import java.util.List;
import java.util.Map;

import com.platform.common.domain.result.ResultDataStruct;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.durian.user.domain.po.UserLogin;
import com.durian.user.domain.to.UserAllInfo;

/**
 * 用户服务API
 * <p>
 * Created by hufeng on 2017/5/24.
 */
@FeignClient("durian-user")
@Service
public interface UserServiceClient {


    /**
     * 注册用户
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    void register(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 发送短信验证码
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/verify/code", method = RequestMethod.POST)
    void registerMobileCode(@RequestParam Map<String, Object> paramMap) throws Exception;


    /**
     * 验证用户名是否存在,生成临时token
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/password/forget", method = RequestMethod.POST)
    ResultDataStruct<String> findPwd(@RequestParam Map<String, Object> paramMap) throws Exception;


    /**
     * 发送找回密码短信
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/password/forget/code", method = RequestMethod.POST)
    String findPwdMobileCode(@RequestParam Map<String, Object> paramMap) throws Exception;


    /**
     * 重置密码
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/password/reset", method = RequestMethod.POST)
    void resetPwd(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 注册用户列表
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/info/list", method = RequestMethod.GET)
    ResultDataStruct<List<UserAllInfo>> userInfoList(@RequestParam Map<String, Object> paramMap) throws Exception;
    
    /**
     * 用户个人资料
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    ResultDataStruct<UserAllInfo> userInfo(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 设置修改个人资料
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.PUT)
    String updateUserInfo(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 用户登陆历史
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/login/history", method = RequestMethod.GET)
    ResultDataStruct<List<UserLogin>> userLoginHistory(@RequestParam Map<String, Object> paramMap) throws Exception;


    /**
     * 修改登录密码
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/user/password/update", method = RequestMethod.PUT)
    String modifyPwd(@RequestParam Map<String, Object> paramMap) throws Exception;
    
    
    /**
     * token 转 用户对象
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/info/token", method = RequestMethod.GET)
    ResultDataStruct<UserAllInfo> userInfoByToken(@RequestParam Map<String, Object> paramMap) throws Exception;
}
