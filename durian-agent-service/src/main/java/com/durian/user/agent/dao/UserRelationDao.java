package com.durian.user.agent.dao;

import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.domain.to.UserLevelRelation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * 获取用户代理1级数据
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserAgentInfo(String userId);
    /**
     * 获取用户代理2级数据
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserAgent2Info(String userId);
    /**
     * 获取用户代理3级数据
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserAgent3Info(String userId);

    /**
     * 获取用户手续费1级
     * @param userId
     * @return
     */
    UserRelation selectBrokerageInfo(String userId);
    /**
     * 获取用户手续费2级
     * @param userId
     * @return
     */
    UserRelation selectBrokerage2Info(String userId);
    /**
     * 获取用户手续费3级
     * @param userId
     * @return
     */
    UserRelation selectBrokerage3Info(String userId);
}
