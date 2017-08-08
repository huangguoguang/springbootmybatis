package com.durian.user.capital.dao;


import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.domain.po.UserCapitalAccount;

import java.math.BigDecimal;

/**
 * 用户资金操作
 *
 * Created by lj on 2017/4/11.
 */
public interface UserCapitalDao {

    /**
     * 取用户资金信息
     *
     * @param userId 用户ID
     * @return 金额
     * */
    BigDecimal getUserCapitalByRedis(String userId) throws Exception;

    /**
     * 设置用户资金信息
     *
     * @param userId 用户ID
     * @param amount 金额
     * */
    void setUserCapitalToRedis(String userId, BigDecimal amount);

    /**
     * 增加用户流水
     *
     * @param userBilling 流水信息
     * */
    void addUserBillingToMysql(UserBilling userBilling);

    int addUserCapitalAccount(UserCapitalAccount userCapitalAccount);
}
