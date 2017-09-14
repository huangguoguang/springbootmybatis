package com.durian.user.mapper;

import com.durian.user.domain.po.UserLogin;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);

    @EnableDataSource("slave")
    List<UserLogin> selectByUserId(String userId);
}