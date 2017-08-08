package com.durian.user.mapper;


import com.durian.user.domain.po.UserRelation;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

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
}