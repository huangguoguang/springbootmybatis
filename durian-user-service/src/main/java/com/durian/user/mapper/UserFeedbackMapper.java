package com.durian.user.mapper;


import com.durian.user.domain.po.UserFeedback;
import com.platform.common.domain.annotation.EnableDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFeedbackMapper {

    @EnableDataSource("slave")
    UserFeedback selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserFeedback record);

    int updateByPrimaryKeySelective(UserFeedback record);

    @EnableDataSource("slave")
    List<UserFeedback> selectByType (UserFeedback record);
}