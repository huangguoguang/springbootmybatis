package com.durian.user.capital.mapper;

import org.springframework.stereotype.Repository;
import com.durian.user.capital.domain.po.UserCapitalAccount;
import com.durian.user.capital.domain.po.UserBilling;

import java.util.Map;

/**
 *
 * Created by lj on 2017/4/12.
 */
@Repository
public interface UserCapitalMapper {

    /**
     * 增加用户资金账户
     *
     * @param userCapitalAccount 用户资金账户信息
     * @return 插入条数
     * */
    int addUserCapitalAccount(UserCapitalAccount userCapitalAccount);

    /**
     * 获取用户资金账户
     *
     * @param userId 用户ID
     * @return 用户资金账户信息
     * */
    UserCapitalAccount getUserCapitalAccount(String userId);

    /**
     * 更新用户资金信息
     *
     * @param paramMap userId 用户ID, amount 金额, operate 操作 isUse 是否可用 modifyDate 更新时间
     * @return 更新条数
     * */
    int updateUserCapitalAccount(Map<String, Object> paramMap);

    /**
     * 增加用户流水
     *
     * @param userBilling 流水信息
     * @return 插入条数
     * */
    int addUserBilling(UserBilling userBilling);
}
