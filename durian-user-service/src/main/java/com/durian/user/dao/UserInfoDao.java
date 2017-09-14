package com.durian.user.dao;

import com.durian.user.domain.po.UserInfo;

/**
 * 用户个人信息操作接口
 * Created by huangguang on 2017/7/4.
 */
public interface UserInfoDao {

    /**
     * 更新userInfo
     * @param userInfo
     * @return
     * @throws Exception
     */
    int updateUserInfo(UserInfo userInfo) throws Exception;

    /**
     * 查询指定userId的userInfo
     * @param userId
     * @return
     */
    UserInfo getUserInfoByUserId(String userId);

    int updateNickName(UserInfo userInfo);
}
