package com.durian.user.dao.impl;

import com.durian.user.dao.UserInfoDao;
import com.durian.user.domain.po.UserInfo;
import com.durian.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huangguang on 2017/7/4.
 */
@Service("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional
    public int updateUserInfo(UserInfo userInfo) throws Exception {
        int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return count;
    }

    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        UserInfo userInfo = userInfoMapper.selectByUserId(userId);
        return userInfo;
    }

    @Override
    public int updateNickName(UserInfo userInfo) {
        int count= userInfoMapper.updateUserInfo(userInfo);
        return count;
    }
}
