package com.durian.user.dao.impl;

import com.durian.user.dao.UserLoginDao;
import com.durian.user.domain.po.UserLogin;
import com.durian.user.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daixibiao on 2017/7/10.
 */
@Service("userLoginDao")
public class UserLoginDaoImpl implements UserLoginDao{

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public List<UserLogin> getLoginHistoryList(String userId) throws Exception {
        //最近10次登录记录
        List<UserLogin> userLoginList = userLoginMapper.selectByUserId(userId);
        return userLoginList;
    }
}
