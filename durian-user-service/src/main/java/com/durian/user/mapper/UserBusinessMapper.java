package com.durian.user.mapper;


import com.durian.user.domain.po.UserBusiness;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBusiness record);

    int insertSelective(UserBusiness record);

    UserBusiness selectByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(UserBusiness record);

    int updateByPrimaryKey(UserBusiness record);


    int updateByMobile(UserBusiness record);

    @EnableDataSource("slave")
    UserBusiness selectByUserId(String userId);
}