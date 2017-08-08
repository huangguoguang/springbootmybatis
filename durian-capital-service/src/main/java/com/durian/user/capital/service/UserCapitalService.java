package com.durian.user.capital.service;

import com.durian.user.capital.domain.enums.CapitalOperateEnums;
import com.durian.user.capital.domain.enums.CapitalUseTypeEnums;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.durian.user.capital.domain.po.UserBilling;

import java.math.BigDecimal;

/**
 * 用户资金服务接口定义
 *
 * Created by lj on 2017/4/11.
 */
public interface UserCapitalService {

    /**
     * 获取用户资金信息 redis
     *
     * @param userId 用户Id
     * @return 用户资金账户信息
     * */
    BigDecimal  getUserCapitalAccount(String userId) throws Exception;


//    /**
//     * 设置用户资金信息 redis
//     *
//     * @param userId 用户Id
//     * @param amount 金额
//     * @return 用户资金账户信息
//     * */
//    public boolean setUserCapitalAccount(String userId, BigDecimal amount) throws Exception;

    /**
     * 设置用户资金信息 redis
     *
     * @param userId 用户Id
     * @param amount 金额
     * @return 用户资金账户信息
     * */
    boolean changeUserCapitalToRedis(String userId, BigDecimal amount, CapitalOperateEnums capitalOperateEnums)throws Exception;

    /**
     * 用户资金变动
     *
     * @param userId 用户ID
     * @param amount 变动金额
     * @param capitalOperateEnums 操作方向
     * @param capitalUseTypeEnums 使用类型
     * @param desc 描述
     * @param setRedis
     * @return 是否成功
     * */
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    boolean changeUserCapital(String userId, BigDecimal amount, CapitalOperateEnums capitalOperateEnums, CapitalUseTypeEnums capitalUseTypeEnums, String desc, boolean setRedis) throws Exception;


    /**
     * 保存用户流水to mongodb
     *
     * @param userBilling 用户流水
     *
     * @return 是否成功
     * */
    boolean addUserBillingToMysql(UserBilling userBilling);

}
