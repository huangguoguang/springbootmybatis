package com.durian.user.mapper;

import org.springframework.stereotype.Repository;

import com.durian.user.domain.po.BackendUserLogin;
import com.platform.common.domain.annotation.EnableDataSource;

@Repository
public interface BackendUserLoginMapper {
	@EnableDataSource("master")
    int deleteByPrimaryKey(Integer id);

	@EnableDataSource("master")
    int insert(BackendUserLogin record);

	@EnableDataSource("master")
    int insertSelective(BackendUserLogin record);

    @EnableDataSource("slave")
    BackendUserLogin selectByPrimaryKey(Integer id);

    @EnableDataSource("master")
    int updateByPrimaryKeySelective(BackendUserLogin record);

    @EnableDataSource("master")
    int updateByPrimaryKey(BackendUserLogin record);
}