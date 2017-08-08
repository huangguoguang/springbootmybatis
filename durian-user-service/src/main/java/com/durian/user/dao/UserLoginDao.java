package com.durian.user.dao;

import com.durian.user.domain.po.UserLogin;

import java.util.List;

/**
 * 用户查询登陆历史接口
 * Created by daixibiao on 2017/7/10.
 */
public interface UserLoginDao {

    /**
     * 查询指定userId的登陆历史
     * @param  userId
     * @return
     * @throws Exception
     */
     List<UserLogin> getLoginHistoryList(String userId) throws Exception;
}
