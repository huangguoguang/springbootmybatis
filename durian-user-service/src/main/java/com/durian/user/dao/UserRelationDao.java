package com.durian.user.dao;

import com.durian.user.domain.po.UserRelation;

/**
 * Created by wangzhe on 2017/8/3.
 */
public interface UserRelationDao {

    /**
     * 保存用户关系
     * @param userRelation
     */
    int saveUserRelation(UserRelation userRelation) ;



    /**
     * 更新用户关系
     * @param userRelation
     */
    int updateRelationInfo(UserRelation userRelation) ;
}
