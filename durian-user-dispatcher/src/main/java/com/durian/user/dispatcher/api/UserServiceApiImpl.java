package com.durian.user.dispatcher.api;

import com.durian.user.domain.to.FindPwd;
import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.LoginService;
import com.durian.user.service.UserService;
import com.durian.user.thrift.api.domain.LoginUserTo;
import com.durian.user.thrift.api.domain.RegisterUserTo;
import com.durian.user.thrift.api.domain.UserAllInfoTo;
import com.durian.user.thrift.api.service.UserServiceApi;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hufeng on 2017/8/11.
 */
@EnableThriftService(path = "/api/user")
@Service
public class UserServiceApiImpl implements UserServiceApi.Iface{


    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    @Override
    public UserAllInfoTo login(LoginUserTo loginUserTo) throws TException {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(loginUserTo, loginUser);
        try {
            //判断用户登录
            UserAllInfo userAllInfo = loginService.login(loginUser);
            UserAllInfoTo userAllInfoTo = new UserAllInfoTo();
            BeanUtils.copyProperties(userAllInfo,userAllInfoTo);
            return userAllInfoTo ;
        } catch (Exception e) {
            e.printStackTrace();

            throw new TException(e);
        }
    }

    @Override
    public UserAllInfoTo refreshToken(String refreshToken, String type) throws TException {
        try {
            UserAllInfo userAllInfo = loginService.refreshToken(refreshToken,type);
            UserAllInfoTo userAllInfoTo = new UserAllInfoTo();
            BeanUtils.copyProperties(userAllInfo,userAllInfoTo);
            return userAllInfoTo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }
    }


    @Override
    public void registerUser(RegisterUserTo registerUserTo) throws TException {

        try {
            RegisterUser registerUser = new RegisterUser();
            BeanUtils.copyProperties(registerUserTo, registerUser);
            //判断用户登录
            UserAllInfo userAllInfo = userService.registerUser(registerUser);

        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }
    }

    @Override
    public void registerMobileCode(RegisterUserTo registerUserTo) throws TException {
        try {
            RegisterUser registerUser = new RegisterUser();
            BeanUtils.copyProperties(registerUserTo, registerUser);
            //判断用户登录
            userService.registerMobileCode(registerUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }
    }

    @Override
    public void findPwdMobileCode(RegisterUserTo registerUserTo) throws TException {
        try {
            FindPwd findPwd = new FindPwd();
            BeanUtils.copyProperties(registerUserTo, findPwd);
            userService.findPwdMobileCode(findPwd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }

    }

    @Override
    public void resetPwd(RegisterUserTo registerUserTo) throws TException {
        try {
            FindPwd findPwd = new FindPwd();
            BeanUtils.copyProperties(registerUserTo, findPwd);
            userService.resetPwd(findPwd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }
    }


    @Override
    public UserAllInfoTo userInfoByUserId(String userId) throws TException {
        try {
            UserAllInfoTo userAllInfoTo = new UserAllInfoTo();
            BeanUtils.copyProperties(userService.userInfo(userId),userAllInfoTo);
            return userAllInfoTo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }
    }

    @Override
    public void modifyPwd(String userId, String oldPwd, String newPwd) throws TException {
        try {
            userService.modifyPwd(userId,oldPwd,newPwd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }
    }

    @Override
    public void registerAgentUser(String userId, String nickName) throws TException {
        try {
            userService.registerAgentUser(userId,nickName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TException(e);
        }
    }
}
