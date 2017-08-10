package com.durian.user.capital.dao;


import com.durian.user.capital.domain.enums.CapitalStatusEnums;
import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.domain.po.UserCapital;

import java.util.List;

/**
 * 用户资金操作
 *
 * Created by lj on 2017/4/11.
 */
public interface UserCapitalDao {

    /**
     * 取用户资金账户
     *
     * @param userId 用户ID
     * @return UserCapital
     * */
    UserCapital getUserCapital(String userId) throws Exception;

    /**
     * 取用户资金余额
     *
     * @param userCapital 用户资金信息
     * @return UserCapital
     * */
    void insertUserCapital(UserCapital userCapital) throws Exception;

    /**
     * 用户资金变化
     *
     * @param userBilling 流水
     * */
    void changeUserBalance(UserBilling userBilling) throws Exception;

    /**
     * 批量增加用户资金变化
     *
     * @param userBillings 流水
     * */
    void addUserBalance(List<UserBilling> userBillings) throws Exception;


    /**
     * 资金账户状态变化
     *
     * @param userId
     * @param capitalStatus
     * */
    void changeUserCapitalStatus(String userId, CapitalStatusEnums capitalStatus) throws Exception;


    /**
     * 同步用户资金
     *
     * @param userId
     * */
    UserCapital syncUserCapital(String userId);

}
