package com.durian.user.dao.impl;

import com.durian.user.dao.UserSmsDao;
import com.durian.user.domain.po.UserSms;
import com.durian.user.mapper.UserSmsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/12.
 */
@Service("userSmsDao")
public class UserSmsDaoImpl implements UserSmsDao {

    @Autowired
    private UserSmsMapper userSmsMapper ;

    @Override
    public int saveUserSms(UserSms userSms) {

        return userSmsMapper.insert(userSms);
    }
}
