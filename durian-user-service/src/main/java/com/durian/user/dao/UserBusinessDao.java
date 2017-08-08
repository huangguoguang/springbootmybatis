package com.durian.user.dao;

import com.durian.user.domain.po.UserBusiness;

/**
 * Created by huangguang on 2017/7/4.
 */
public interface UserBusinessDao {
    /**
     * 查询指定userId的userbusiness
     * @param userId
     * @return
     */
    UserBusiness getUserBusinessByUserId(String userId);

    /**
     * 更新userBusiness信息
     * @param oldUserBusiness
     * @return
     */
    int updateUserBusiness(UserBusiness oldUserBusiness);
}
