package com.durian.user.capital.service;

import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.domain.po.UserCapital;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户资金服务接口定义
 *
 * Created by lj on 2017/4/11.
 */
public interface UserCapitalService {

    /**
     * 创建用户资金账户
     *
     * @param userId
     * */
    void  createUserCapital(String userId) throws Exception;

    /**
     * 冻结用户资金账户
     *
     * @param userId
     * */
    void freezeUserCapital(String userId) throws Exception;

    /**
     * 禁用用户资金账户
     *
     * @param userId
     * */
    void disableUserCapital(String userId) throws Exception;

    /**
     * 取用户资金余额
     *
     * @param userId 用户ID
     * @return balance
     * */
    UserCapital getUserCapital(String userId) throws Exception;

    /**
     * 用户余额变化
     *
     * @param userBilling
     * */
    void changeUserBalance(UserBilling userBilling) throws Exception;

    /**
     * 用户余额增加
     *
     * @param userBillings
     * */
    void addUserBalance(List<UserBilling> userBillings) throws Exception;

    /**
     * 同步用户余额
     *
     * @param userId
     * */
    void syncUserCapital(String userId);


    /**
     * 获取用户流水
     *
     * */
    List<UserBilling> getUserBilling(Map<String, Object> queryParam, int pageNum, int perPageRow);
}
