package com.durian.user.dao.impl;

import com.durian.user.dao.UserBusinessDao;
import com.durian.user.domain.po.UserBusiness;
import com.durian.user.mapper.UserBusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huangguang on 2017/7/4.
 */
@Service("userBusinessDao")
public class UserBusinessDaoImpl implements UserBusinessDao {
    @Autowired
    private UserBusinessMapper userBusinessMapper;

    @Override
    public UserBusiness getUserBusinessByUserId(String userId) {
        UserBusiness userBusiness = userBusinessMapper.selectByUserId(userId);
        return userBusiness;
    }

    @Override
    @Transactional
    public int updateUserBusiness(UserBusiness userBusiness) {
        int count = userBusinessMapper.updateByPrimaryKeySelective(userBusiness);
        return count;
    }
}
