package com.durian.user.mapper;


import com.durian.user.domain.po.UserInfo;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    @EnableDataSource("slave")
    UserInfo selectByUserId(String userId);

    int updateUserInfo(UserInfo record);
}