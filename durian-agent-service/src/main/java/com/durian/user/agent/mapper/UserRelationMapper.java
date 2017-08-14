package com.durian.user.agent.mapper;


import com.durian.user.agent.domain.po.UserRelation;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRelationMapper {

    @EnableDataSource("master")
    int deleteByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int insert(UserRelation record);

    @EnableDataSource("master")
    int insertSelective(UserRelation record);

    @EnableDataSource("slave")
    UserRelation selectByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(UserRelation record);

    @EnableDataSource("master")
    int updateByPrimaryKey(UserRelation record);

    @EnableDataSource("master")
    int updateRelationInfo(UserRelation record);

    List<Map<String, Object>> selectUserAgentInfo(String userId);

    List<Map<String, Object>> selectUserAgent2Info(String userId);

    List<Map<String, Object>> selectUserAgent3Info(String userId);

    UserRelation selectBrokerageInfo(String userId);

    UserRelation selectBrokerage2Info(String userId);

    UserRelation selectBrokerage3Info(String userId);
}