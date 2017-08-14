package com.durian.user.dispatcher.api;

import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.LoginService;
import com.durian.user.thrift.api.domain.LoginUserTo;
import com.durian.user.thrift.api.domain.UserAllInfoTo;
import com.durian.user.thrift.api.service.UserServiceApi;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/11.
 */
@EnableThriftService(path = "/api/user")
@Service
public class UserServiceApiImpl implements UserServiceApi.Iface{


    @Autowired
    private LoginService loginService;


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
}
