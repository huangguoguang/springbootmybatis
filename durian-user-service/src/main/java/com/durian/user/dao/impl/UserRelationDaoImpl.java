package com.durian.user.dao.impl;

import com.durian.user.dao.UserRelationDao;
import com.durian.user.domain.po.UserRelation;
import com.durian.user.mapper.UserRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhe on 2017/8/3.
 */
@Service("userRelationDao")
public class UserRelationDaoImpl implements UserRelationDao {

    @Autowired
    private UserRelationMapper userRelationMapper ;


    @Override
    public int saveUserRelation(UserRelation userRelation) {
        return userRelationMapper.insert(userRelation);
    }

    @Override
    public int updateRelationInfo(UserRelation userRelation) {
        return userRelationMapper.updateRelationInfo(userRelation);
    }


}
