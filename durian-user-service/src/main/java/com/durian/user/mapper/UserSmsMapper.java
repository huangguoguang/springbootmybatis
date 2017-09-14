package com.durian.user.mapper;


import com.durian.user.domain.po.UserSms;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSmsMapper {

    @EnableDataSource("master")
    int deleteByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int insert(UserSms record);

    @EnableDataSource("master")
    int insertSelective(UserSms record);

    @EnableDataSource("slave")
    UserSms selectByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(UserSms record);

    @EnableDataSource("master")
    int updateByPrimaryKey(UserSms record);
}