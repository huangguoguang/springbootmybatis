package com.durian.user.service;


import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.UserAllInfo;

/**
 * 用户登录接口
 * @author hufeng
 *
 */
public interface LoginService {

    /**
     *
     * @param loginUser
     * @return
     * @throws Exception
     */
    UserAllInfo login(LoginUser loginUser) throws Exception;


    /**
     *
     * @param refreshToken
     * @return
     * @throws Exception
     */
    UserAllInfo refreshToken(String refreshToken,String type) throws Exception;
}
