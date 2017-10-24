package com.durian.user.dao.impl;

import com.durian.user.dao.UserFeedbackDao;
import com.durian.user.domain.po.UserFeedback;
import com.durian.user.mapper.UserFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/10/19.
 */
@Service("userFeedbackDao")
public class UserFeedbackDaoImpl implements UserFeedbackDao{

    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    @Override
    public UserFeedback getUserFeedBackById(Long id) {
        return userFeedbackMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveUserFeedback(UserFeedback userFeedback) {
        int count = userFeedbackMapper.insertSelective(userFeedback);
        return count;
    }

    @Override
    public int updateUserFeedback(UserFeedback userFeedback) {
        int count = userFeedbackMapper.updateByPrimaryKeySelective(userFeedback);
        return count;
    }

    @Override
    public int deleteUserFeedback(Long id) {
        int count = userFeedbackMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public List<UserFeedback> getUserFeedbackList(UserFeedback record) {
        return userFeedbackMapper.selectByType(record);
    }

    @Override
    public List<UserFeedback> queryCode(String type) {
        return userFeedbackMapper.queryCode(type);
    }

    @Override
    public int updateCodeStatus(UserFeedback record) throws Exception {
        return userFeedbackMapper.updateCodeStatus(record);
    }
}
