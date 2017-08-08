package com.durian.user.capital.dao.impl;

import com.durian.user.capital.dao.UserCapitalDao;


import com.durian.user.capital.domain.enums.CapitalExceptionCodeEnums;
import com.durian.user.capital.domain.enums.CapitalRedisKeyEnums;
import com.durian.user.capital.domain.po.UserCapitalAccount;
import com.durian.user.capital.mapper.UserCapitalMapper;
import com.platform.common.domain.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.durian.user.capital.domain.po.UserBilling;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 *
 * Created by lj on 2017/4/11.
 */
@Service("userCapitalDao")
public class UserCapitalDaoImpl implements UserCapitalDao {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserCapitalMapper userCapitalMapper;


    @Override
    public BigDecimal getUserCapitalByRedis(String userId) throws Exception{
        String balance = stringRedisTemplate.opsForValue().get(MessageFormat.format(CapitalRedisKeyEnums.OLD_CAPITAL_USER_PREFIX.getCode(), userId));
        if(StringUtils.isBlank(balance)){
            throw new CustomException(CapitalExceptionCodeEnums.CAPITAL_NOT_FOUND);
        }
        return new BigDecimal(balance);
    }

    @Override
    public void setUserCapitalToRedis(String userId, BigDecimal amount) {
        stringRedisTemplate.opsForValue()
                .set(MessageFormat.format(CapitalRedisKeyEnums.OLD_CAPITAL_USER_PREFIX.getCode(), userId) , amount.toString());
    }

    @Override
    public void addUserBillingToMysql(UserBilling userBilling) {
        //UserBilling userProfitLoss = new UserBilling();
        userCapitalMapper.addUserBilling(userBilling);
        //mongoTemplate.save(userProfitLoss, "UserProfitLoss");
    }

    @Override
    public int addUserCapitalAccount(UserCapitalAccount userCapitalAccount) {
        return userCapitalMapper.addUserCapitalAccount(userCapitalAccount);
    }
}
