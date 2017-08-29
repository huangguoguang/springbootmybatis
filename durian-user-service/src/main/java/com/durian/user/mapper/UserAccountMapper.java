package com.durian.user.mapper;


import com.durian.user.domain.po.UserAccount;
import org.springframework.stereotype.Repository;

import com.durian.user.domain.to.UserAllInfo;
import com.platform.common.domain.annotation.EnableDataSource;

import java.util.List;


@Repository
public interface UserAccountMapper {
    @EnableDataSource("master")
    int deleteByPrimaryKey(String id);

    @EnableDataSource("master")
    int insert(UserAccount record);

    @EnableDataSource("master")
    int insertSelective(UserAccount record);

    @EnableDataSource("slave")
    UserAccount selectByPrimaryKey(String id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(UserAccount record);

    @EnableDataSource("master")
    int updateByPrimaryKey(UserAccount record);

    @EnableDataSource("slave")
    UserAllInfo selectByMoblie(String moblie);



    @EnableDataSource("slave")
    UserAllInfo selectById(String id);
    
    @EnableDataSource("master")
    int updateStatusById(UserAccount record);

    @EnableDataSource("slave")
    List<UserAllInfo> selectUserInfoList(UserAllInfo userAllInfo);

}