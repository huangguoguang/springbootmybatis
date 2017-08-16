package com.durian.user.capital.service.impl;

import com.durian.user.capital.dao.UserCapitalDao;
import com.durian.user.capital.domain.enums.CapitalStatusEnums;
import com.durian.user.capital.domain.po.UserCapital;
import com.durian.user.capital.event.UserCapitalSyncEvent;
import com.durian.user.capital.service.UserCapitalService;
import com.durian.user.capital.domain.po.UserBilling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

/**
 * 用户资金服务接口实现
 *
 * Created by lj on 2017/4/11.
 */
@Service("userCapitalService")
public class UserCapitalServiceImpl implements UserCapitalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCapitalServiceImpl.class);

    @Resource
    private UserCapitalDao userCapitalDao;
    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void createUserCapital(String userId) throws Exception {
        UserCapital userCapital = new UserCapital();
        userCapital.setUserId(userId);
        userCapital.setAmount(BigDecimal.ZERO);
        userCapital.setStatus(CapitalStatusEnums.ENABLE.getCode());
        userCapital.setCreateTime(System.currentTimeMillis());
        userCapitalDao.insertUserCapital(userCapital);
    }

    @Override
    public void freezeUserCapital(String userId) throws Exception {
        userCapitalDao.changeUserCapitalStatus(userId, CapitalStatusEnums.FREEZE);
    }

    @Override
    public void disableUserCapital(String userId) throws Exception {
        userCapitalDao.changeUserCapitalStatus(userId, CapitalStatusEnums.DISABLE);
    }

    @Override
    public UserCapital getUserCapital(String userId) throws Exception {
        return userCapitalDao.getUserCapital(userId);
    }

    @Override
    public void changeUserBalance(UserBilling userBilling) throws Exception {
        userCapitalDao.changeUserBalance(userBilling);

        applicationContext.publishEvent(new UserCapitalSyncEvent(userBilling.getUserId()));
    }

    @Override
    public void addUserBalance(List<UserBilling> userBillings) throws Exception {
        userCapitalDao.addUserBalance(userBillings);

        for (int i=0; i<userBillings.size(); i++){
            UserBilling userBilling = userBillings.get(i);
            applicationContext.publishEvent(new UserCapitalSyncEvent(userBilling.getUserId()));
        }
    }

    @Override
    public void syncUserCapital(String userId){
        applicationContext.publishEvent(new UserCapitalSyncEvent(userId));
        LOGGER.info(MessageFormat.format("同步用户资金：{0}", userId));
    }

    @Override
    public List<UserBilling> getUserBilling(Map<String, Object> queryParam, int pageNum, int perPageRow) {
        return null;
    }
}
