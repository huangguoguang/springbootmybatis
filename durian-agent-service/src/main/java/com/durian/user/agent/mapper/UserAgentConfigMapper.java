package com.durian.user.agent.mapper;


import com.durian.user.agent.domain.po.UserAgentConfig;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAgentConfigMapper {

    @EnableDataSource("master")
    int deleteByPrimaryKey(String id);

    @EnableDataSource("master")
    int insert(UserAgentConfig record);


    @EnableDataSource("master")
    UserAgentConfig insertSelective(UserAgentConfig record);

    @EnableDataSource("slave")
    UserAgentConfig selectByPrimaryKey(String id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(UserAgentConfig record);

    @EnableDataSource("master")
    int updateByPrimaryKey(UserAgentConfig record);


}