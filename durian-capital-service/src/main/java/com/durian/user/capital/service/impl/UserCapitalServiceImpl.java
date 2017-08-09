package com.durian.user.capital.service.impl;

import com.durian.user.capital.dao.UserCapitalDao;
import com.durian.user.capital.domain.enums.*;
import com.durian.user.capital.mapper.UserCapitalMapper;
import com.durian.user.capital.service.UserCapitalService;
import com.platform.common.domain.exception.CustomException;
import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.domain.po.UserCapitalAccount;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户资金服务接口实现
 *
 * Created by lj on 2017/4/11.
 */
@Service
public class UserCapitalServiceImpl implements UserCapitalService {

    @Resource
    private UserCapitalDao userCapitalDao;

    @Resource
    private UserCapitalMapper userCapitalMapper;


    @Resource
    private ApplicationContext applicationContext;

    @Override
    public BigDecimal getUserCapitalAccount(String userId) throws Exception{
        // get user capital account by redis
        return userCapitalDao.getUserCapitalByRedis(userId);
    }

/*
@Override
public boolean setUserCapitalAccount(String userId,BigDecimal amount) throws Exception{
// add capital lock
String capitalLock = distributedLock.getLock(CapitalLockEnums.CAPITAL.getCode(), userId); // 资金锁
if (capitalLock == null) {
throw new CustomException(CapitalExceptionCodeEnums.CHANGE_CAPITAL_FAIL); // TODO
}
try {
// set user amount
userCapitalDao.setUserCapitalToRedis(userId, amount);
} finally {
distributedLock.releaseLock(CapitalLockEnums.CAPITAL.getCode(), userId, capitalLock);
}
return true;
}
*/

    @Override
    public boolean changeUserCapitalToRedis(String userId, BigDecimal amount, CapitalOperateEnums capitalOperateEnums) throws Exception {
        // add capital lock
        String capitalLock =""; //distributedLock.(CapitalLockEnums.LOCK_WALLET.getCode(), userId); // 资金锁
        if (capitalLock == null) {
            throw new CustomException(CapitalExceptionCodeEnums.CHANGE_CAPITAL_FAIL);
        }
        try {
            BigDecimal currentBalance = userCapitalDao.getUserCapitalByRedis(userId);
            BigDecimal newBalance = calculate(currentBalance, amount, capitalOperateEnums);
            if(BigDecimal.ZERO.compareTo(newBalance) == 1){
                // 不可以小于零
                throw new CustomException(CapitalExceptionCodeEnums.CAPITAL_SCARCITY);
            }
            // set user amount
            userCapitalDao.setUserCapitalToRedis(userId, newBalance);
        } finally {
            //distributedLock.releaseLock(CapitalLockEnums.LOCK_WALLET.getCode(), userId, capitalLock);
        }
        return true;
    }

    @Override
    public boolean changeUserCapital(String userId, BigDecimal amount, CapitalOperateEnums capitalOperateEnums, CapitalUseTypeEnums capitalUseTypeEnums, String desc, boolean setRedis) throws Exception{
        // add capital lock
        String capitalLock =""; //distributedLock.getLock(CapitalLockEnums.LOCK_MYSQL_WALLET.getCode(), userId);
        if (capitalLock == null) {
            throw new CustomException(CapitalExceptionCodeEnums.CHANGE_CAPITAL_FAIL);
        }
        try {

            // get user capital account by mysql
            UserCapitalAccount vUserCapitalAccount = userCapitalMapper.getUserCapitalAccount(userId);
            if (vUserCapitalAccount == null) {
                throw new CustomException(CapitalExceptionCodeEnums.ACCOUNT_NOT_EXISTS);
            }
            BigDecimal newBalance = calculate(vUserCapitalAccount.getBalance(), amount, capitalOperateEnums);
            if(BigDecimal.ZERO.compareTo(newBalance) == 1){
                // 不可以小于零
                throw new CustomException(CapitalExceptionCodeEnums.CAPITAL_SCARCITY);
            }

            Date currentDate = new Date();
            // user billing
            UserBilling userBilling = new UserBilling();
            userBilling.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            userBilling.setUserId(userId);
            userBilling.setBeforeBalance(vUserCapitalAccount.getBalance());
            userBilling.setAmount(amount);
            userBilling.setAfterBalance(newBalance);
            userBilling.setOperate(capitalOperateEnums.getCode());
            userBilling.setUseType(capitalUseTypeEnums.getCode());
            userBilling.setDesc(desc);
            userBilling.setIsUse(RecordStatusEnums.VALID.getCode());
            userBilling.setCreateDate(currentDate);

            // to mysql
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", userBilling.getUserId());
            paramMap.put("amount", userBilling.getAmount());
            paramMap.put("operate", userBilling.getOperate());
            paramMap.put("modifyDate", new Date());
            userCapitalMapper.updateUserCapitalAccount(paramMap); // change capital
            userCapitalMapper.addUserBilling(userBilling); // save billing

            // balance to redis
            if(setRedis){
                BigDecimal currentBalance = userCapitalDao.getUserCapitalByRedis(userId);
                BigDecimal rnewBalance = calculate(currentBalance, amount, capitalOperateEnums);
//                if(BigDecimal.ZERO.compareTo(newBalance) == 1){
//                    // 不可以小于零
//                    throw new CustomException(CapitalExceptionCodeEnums.CAPITAL_SCARCITY);
//                }
                // set user amount
                userCapitalDao.setUserCapitalToRedis(userId, rnewBalance);
            }

        } finally {
            //distributedLock.releaseLock(CapitalLockEnums.LOCK_MYSQL_WALLET.getCode(), userId, capitalLock);
        }
        return true;
    }

    private BigDecimal calculate(BigDecimal currentBalance,BigDecimal amount, CapitalOperateEnums capitalOperateEnums){
        return CapitalOperateEnums.ADD == capitalOperateEnums ? currentBalance.add(amount) : currentBalance.subtract(amount);
    }

    @Override
    public boolean addUserBillingToMysql(UserBilling userBilling) {
        userCapitalDao.addUserBillingToMysql(userBilling);
        return true;
    }
}
