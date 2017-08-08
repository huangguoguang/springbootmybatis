package com.durian.user.mapper;


import com.durian.user.domain.po.UserAddress;
import org.springframework.stereotype.Repository;

import com.platform.common.domain.annotation.EnableDataSource;

import java.util.List;

@Repository
public interface UserAddressMapper {
	@EnableDataSource("master")
    int deleteByPrimaryKey(Integer id);

	@EnableDataSource("master")
    int insert(UserAddress record);

	@EnableDataSource("master")
    int insertSelective(UserAddress record);

	@EnableDataSource("slave")
    UserAddress selectByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(UserAddress record);

    @EnableDataSource("master")
    int updateByPrimaryKey(UserAddress record);
    
    @EnableDataSource("slave")
    UserAddress selectByUserId(String userId);

    @EnableDataSource("slave")
    List<UserAddress> selectByUserId(UserAddress userAddress);
}