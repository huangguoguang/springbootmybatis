package com.durian.user.capital.mapper;

import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.domain.po.UserCapital;
import org.springframework.stereotype.Repository;

import java.util.List;
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
     * @param userCapital 用户资金
     * */
    int insertUserCapital(UserCapital userCapital);

    /**
     * 查询用户资金账户
     *
     * @param userId 用户ID
     * */
    UserCapital selectUserCapital(String userId);

    /**
     * 查询用户资金账户
     *
     * @param userBillings 用户
     * */
    List<UserCapital> selectUserCapital(List<UserBilling> userBillings);

    /**
     * 更新用户资金账户
     *
     * @param userCapital
     * */
    int updateUserCapital(UserCapital userCapital);

    /**
     * 用户资金变动
     *
     * @param userBilling
     * */
    int updateUserBilling(UserBilling userBilling);

    /**
     * 用户资金变动
     *
     * @param userBillings
     * */
    int updateUserCapitalList(List<UserBilling> userBillings);

    /**
     * 增加用户流水
     *
     * @param userBilling 流水信息
     * */
    int insertUserBilling(UserBilling userBilling);

    /**
     * 增加用户流水
     *
     * @param userBillings 流水信息
     * */
    int insertUserBillingList(List<UserBilling> userBillings);

    /**
     * 用户流水
     *
     * */
    List<UserBilling> selectUserBilling(Map<String, Object> queryParam, int pageNum, int perPageRow);
}
