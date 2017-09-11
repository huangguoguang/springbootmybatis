package com.durian.user.mapper;

import com.durian.user.domain.po.BackendUser;
import com.durian.user.domain.to.UserAllInfo;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackendUserMapper {
	@EnableDataSource("master")
    int deleteByPrimaryKey(Integer id);

	@EnableDataSource("master")
    int insert(BackendUser record);

	@EnableDataSource("master")
    int insertSelective(BackendUser record);

	@EnableDataSource("slave")
    BackendUser selectByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(BackendUser record);

    @EnableDataSource("master")
    int updateByPrimaryKey(BackendUser record);

    
    @EnableDataSource("slave")
	UserAllInfo selectByName(String name);


    @EnableDataSource("slave")
    List<BackendUser> getUserList();
}