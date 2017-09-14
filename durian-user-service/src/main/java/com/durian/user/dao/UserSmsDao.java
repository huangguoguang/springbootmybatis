package com.durian.user.dao;

import com.durian.user.domain.po.UserSms;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface UserSmsDao {

    /**
     * 发送手机短信
     * @param userSms
     */
    int saveUserSms(UserSms userSms) ;
}
