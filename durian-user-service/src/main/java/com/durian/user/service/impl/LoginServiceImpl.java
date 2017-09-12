package com.durian.user.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.durian.user.dao.UserAccountDao;
import com.durian.user.domain.enums.TokenExceptionEnum;
import com.durian.user.domain.enums.UserExceptionEnum;
import com.durian.user.domain.enums.UserStatusEnum;
import com.durian.user.domain.po.UserLogin;
import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.LoginService;
import com.durian.user.utils.encrypt.MD5Utils;
import com.durian.user.utils.token.Token;
import com.durian.user.utils.token.TokenGenerator;
import com.durian.user.utils.validate.RegexValidateUtil;
import com.platform.common.domain.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 登录验证功能.
 * @author hufeng
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Autowired
    private UserAccountDao userAccountDao ;


    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public UserAllInfo login(LoginUser loginUser) throws Exception {
        //判断手机号码是否合法
        if(StringUtils.isBlank(loginUser.getMobile())){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_NULL);
        }
        //手机号码格式不正确
        if(!RegexValidateUtil.checkMobile(loginUser.getMobile())){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_FORMAT);
        }


        //判断用户密码是否合法
        UserAllInfo userAllInfo = userAccountDao.getUserInfoByMoblie(loginUser.getMobile());
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_PASSWORD);
        }
        UserLogin userLogin = new UserLogin();
        userLogin.setIp(loginUser.getIp());
        userLogin.setUserId(userAllInfo.getId());
        userLogin.setCreateTime(new Date().getTime());
        userLogin.setType(loginUser.getType());
        LOGGER.info("用户登录数据:"+JSONObject.toJSONString(userAllInfo));
        if((!userAllInfo.getPassword().equals(MD5Utils.sign(loginUser.getPassword(), MD5Utils.PWD_KEY, MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET))) ){
            userLogin.setStatus(1);
            userAccountDao.saveLogin(userLogin);
            throw new CustomException(UserExceptionEnum.USER_MOBILE_PASSWORD);
        }
        //禁用账号
        System.out.println(userAllInfo.getStatus());
        System.out.println(UserStatusEnum.Disable.getCode());
        //用户禁用
        if((userAllInfo.getStatus() == UserStatusEnum.Disable.getCode()) ){
            userLogin.setStatus(1);
            userAccountDao.saveLogin( userLogin);
            throw new CustomException(UserExceptionEnum.USER_MOBILE_DISABLE);
        }
        //登录次数受限制
        if((userAllInfo.getStatus() == UserStatusEnum.LoginPasswordLimited.getCode()) ){
            userLogin.setStatus(1);
            userAccountDao.saveLogin( userLogin);
            throw new CustomException(UserExceptionEnum.USER_MOBILE_LOGINPASSWORDLIMITED);
        }
        //今日登陆受限

        //创建token返回用户对象.  设置用户登录时间为 24小时
        Integer expires = 60 * 60 * 24;
        String accessToken = tokenGenerator.generatorToken(userAllInfo.getMobile(),userAllInfo.getId(),loginUser.getType(),expires);
        LOGGER.info("accessToken :" + accessToken);
        userAllInfo.setAccessToken(accessToken);
        //设置刷新登录token
        String refreshToken = tokenGenerator.generatorToken(userAllInfo.getMobile(),userAllInfo.getId(),loginUser.getType(),expires);
        userAllInfo.setRefreshToken(refreshToken);
        userAllInfo.setExpires(expires);
        //设置登录时间
        userAllInfo.setLoginTime(new Date().getTime());
        userAllInfo.setPassword(null);
        userAllInfo.setMobile(null);
        userLogin.setStatus(0);
        userAccountDao.saveLogin( userLogin);
        userAllInfo.setReturnUrl(loginUser.getReturnUrl());
        return userAllInfo;
    }

    @Override
    public UserAllInfo refreshToken(String refreshToken,String type) throws Exception {
       Token token =  tokenGenerator.validateToken(refreshToken);
       LOGGER.info(JSONObject.toJSONString(token));
       if(token==null){
           throw new CustomException(TokenExceptionEnum.TOKEN_EXPIRES_CODE);
       }
       UserAllInfo userAllInfo =  userAccountDao.getUserInfoById(token.getUserId());
        //创建token返回用户对象.  设置用户登录时间为 24小时
        Integer expires = 60 * 60 * 24;
        String accessToken = tokenGenerator.generatorToken(userAllInfo.getMobile(),userAllInfo.getId(),type,expires);
        userAllInfo.setAccessToken(accessToken);
        userAllInfo.setCreateTime(new Date().getTime());
        userAllInfo.setRefreshToken(refreshToken);
        userAllInfo.setExpires(expires);
        //设置登录时间
        userAllInfo.setLoginTime(new Date().getTime());

        userAllInfo.setPassword(null);
        userAllInfo.setMobile(null);
        return userAllInfo;
    }
}
