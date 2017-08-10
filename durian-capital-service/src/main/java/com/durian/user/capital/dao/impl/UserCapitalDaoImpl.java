package com.durian.user.capital.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.durian.user.capital.dao.UserCapitalDao;


import com.durian.user.capital.domain.enums.CapitalExceptionEnums;
import com.durian.user.capital.domain.enums.CapitalOperateEnums;
import com.durian.user.capital.domain.enums.CapitalRedisKeyEnums;
import com.durian.user.capital.domain.enums.CapitalStatusEnums;
import com.durian.user.capital.domain.po.UserCapital;
import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.mapper.UserCapitalMapper;
import com.platform.common.domain.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by lj on 2017/4/11.
 */
@Service("userCapitalDao")
public class UserCapitalDaoImpl implements UserCapitalDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCapitalDaoImpl.class);

    @Resource
    private UserCapitalMapper userCapitalMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public UserCapital getUserCapital(String userId) throws Exception {
        UserCapital userCapital = getUserCapitalFromRedis(userId);
        if(userCapital == null){
            return syncUserCapital(userId);
        } else{
            return userCapital;
        }
    }

    @Override
    public void insertUserCapital(UserCapital userCapital) throws Exception {
        // to db
        userCapitalMapper.insertUserCapital(userCapital);
        // to redis
        setUserCapitalToRedis(userCapital);
    }

    @Override
    public void changeUserBalance(UserBilling userBilling) throws Exception {

        // TODO set lock
        UserCapital userCapital = userCapitalMapper.selectUserCapital(userBilling.getUserId());

        String userCapitalStatus = userCapital.getStatus();
        if(CapitalStatusEnums.DISABLE.getCode().equals(userCapitalStatus)){
            throw new CustomException(CapitalExceptionEnums.ACCOUNT_DISABLE);
        }

        if(CapitalOperateEnums.SUBTRACT.getCode().equals(userBilling.getOperate())){
            if(CapitalStatusEnums.FREEZE.getCode().equals(userCapitalStatus)){
                throw new CustomException(CapitalExceptionEnums.ACCOUNT_FREEZE);
            }

            // 减
            if(userCapital.getAmount().compareTo(userBilling.getAmount()) == -1){
                throw new CustomException(CapitalExceptionEnums.CAPITAL_SCARCITY);
            }
        }
        userBilling.setCreateTime(System.currentTimeMillis());

        // 更新余额
        userCapitalMapper.updateUserCapital(userBilling);
        // 写流水
        userCapitalMapper.insertUserBilling(userBilling);
        // TODO release lock
    }

    @Override
    public void addUserBalance(List<UserBilling> userBillings) throws Exception {
        // TODO set lock
        List<UserCapital> userCapitalList = userCapitalMapper.selectUserCapital(userBillings);

        List<UserBilling> doitList = new ArrayList<UserBilling>();
        for(int i = 0; i < userCapitalList.size(); i++){
            UserCapital userCapital = userCapitalList.get(i);
            String userCapitalStatus = userCapital.getStatus();
            if(CapitalStatusEnums.DISABLE.getCode().equals(userCapitalStatus)){
                continue;
            }
            UserBilling userBilling = userBillings.get(i);
            userBilling.setCreateTime(System.currentTimeMillis());

            doitList.add(userBilling);
        }
        // 更新余额
        userCapitalMapper.updateUserCapital(doitList);
        // 写流水
        userCapitalMapper.insertUserBilling(doitList);
        // TODO release lock
    }

    @Override
    public void changeUserCapitalStatus(String userId, CapitalStatusEnums capitalStatus) throws Exception {
        UserCapital userCapital = new UserCapital();
        userCapital.setUserId(userId);
        userCapital.setStatus(capitalStatus.getCode());
        userCapital.setUpdateTime(System.currentTimeMillis());
        userCapitalMapper.updateUserCapital(userCapital);
    }

    @Override
    public UserCapital syncUserCapital(String userId){
        return setUserCapitalToRedis(userCapitalMapper.selectUserCapital(userId));
    }

    private UserCapital setUserCapitalToRedis(UserCapital userCapital){
        if(userCapital != null){
            String key = MessageFormat.format(CapitalRedisKeyEnums.CAPITAL_ACCOUNT.getCode(), userCapital.getUserId());
            stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(userCapital));
        }
        return userCapital;
    }

    private UserCapital getUserCapitalFromRedis(String userId){
        String key = MessageFormat.format(CapitalRedisKeyEnums.CAPITAL_ACCOUNT.getCode(), userId);
        String value = stringRedisTemplate.opsForValue().get(key);
        if(value == null){
            return new UserCapital();
        } else{
            return JSONObject.parseObject(value, UserCapital.class);
        }
    }

}
