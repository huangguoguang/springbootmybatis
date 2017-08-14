package com.durian.user.agent.dao.impl;

import com.durian.user.agent.dao.UserRelationDao;
import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.mapper.UserRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhe on 2017/8/3.
 */
@Service("userRelationDao")
public class UserRelationDaoImpl implements UserRelationDao {

    @Autowired
    private UserRelationMapper userRelationMapper ;


    public int saveUserRelation(UserRelation userRelation) {
        return userRelationMapper.insert(userRelation);
    }

    public int updateRelationInfo(UserRelation userRelation) {
        return userRelationMapper.updateRelationInfo(userRelation);
    }

    public List<Map<String, Object>> selectUserAgentInfo(String userId) {
        return userRelationMapper.selectUserAgentInfo(userId);
    }

    public List<Map<String, Object>> selectUserAgent2Info(String userId) {
        return userRelationMapper.selectUserAgent2Info(userId);
    }

    public List<Map<String, Object>> selectUserAgent3Info(String userId) {
        return  userRelationMapper.selectUserAgent3Info(userId);
    }

    @Override
    public UserRelation selectBrokerageInfo(String userId) {
        return userRelationMapper.selectBrokerageInfo(userId);
    }

    @Override
    public UserRelation selectBrokerage2Info(String userId) {
        return userRelationMapper.selectBrokerage2Info(userId);
    }

    @Override
    public UserRelation selectBrokerage3Info(String userId) {
        return userRelationMapper.selectBrokerage3Info(userId);
    }


}
