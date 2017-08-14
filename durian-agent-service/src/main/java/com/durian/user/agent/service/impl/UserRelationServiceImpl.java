package com.durian.user.agent.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.durian.user.agent.dao.UserRelationDao;
import com.durian.user.agent.domain.enums.AgentRedisKeyEnums;
import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.domain.to.UserLevelRelation;
import com.durian.user.agent.service.UserRelationService;
import com.durian.user.capital.domain.enums.CapitalOperateEnums;
import com.durian.user.capital.domain.enums.CapitalPurposeEnums;
import com.durian.user.capital.domain.enums.CapitalRedisKeyEnums;
import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.domain.po.UserCapital;
import com.durian.user.dao.UserInfoDao;
import com.durian.user.domain.po.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author jacket
 *
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRelationServiceImpl.class);

    @Autowired
    private UserRelationDao userRelationDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public String getUserReferrals(String userId) throws Exception {

        if(redisTemplate.opsForValue().get(AgentRedisKeyEnums.AGENT_INFO+userId)==null) {
            userReferrals(userId);
        }
        return redisTemplate.opsForValue().get(AgentRedisKeyEnums.AGENT_INFO+userId);
    }

    @Override
    public Boolean userReferrals(String userId) throws Exception {

        UserLevelRelation userLevelRelation = new UserLevelRelation();
        userLevelRelation.setLevelCount1(String.valueOf(userRelationDao.selectUserAgentInfo(userId).size()));  //获取1级代理数据
        userLevelRelation.setLevelCount2(String.valueOf(userRelationDao.selectUserAgent2Info(userId).size()));  //获取2级代理数据
        userLevelRelation.setLevelCount3(String.valueOf(userRelationDao.selectUserAgent3Info(userId).size()));  //获取3级代理数据

        //写入redis缓存起来，5分钟失效
        //JSONObject.toJSONString(userLevelRelation);
        if(redisTemplate.opsForValue().get(AgentRedisKeyEnums.AGENT_INFO+userId)==null) {
            redisTemplate.opsForValue().set(AgentRedisKeyEnums.AGENT_INFO + userId, JSONObject.toJSONString(userLevelRelation).toString(), 3000, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public Boolean userBrokerage(String userId) throws Exception {
        //根据做单用户信息找到上级进行佣金的分润
        //获取上级用户ID对其进行佣金分成
        UserRelation userAgent1 = new UserRelation();
        UserRelation userAgent2 = new UserRelation();
        UserRelation userAgent3 = new UserRelation();


        //上级代理分润、比例的分成，金额的改变，流水的写入0.25
        userAgent1 = userRelationDao.selectBrokerageInfo(userId);
        //调用资金变化接口 传入userid和变动的金额

        userBrokerageOperate(userId,"1");

        //获取上上级用户ID对其进行佣金分成
        //比例的分成，金额的改变，流水的写入  0.1
        userAgent2 = userRelationDao.selectBrokerage2Info(userId);
        //调用资金变化接口 传入userid和变动的金额

        userBrokerageOperate(userId,"2");
        //获取上上上级用户ID对其进行佣金分成
        //比例的分成，金额的改变，流水的写入 0.05
        userAgent3 = userRelationDao.selectBrokerage3Info(userId);
        //调用资金变化接口 传入userid和变动的金额
        userBrokerageOperate(userId,"3");
        return true;
    }


    private Boolean userBrokerageOperate(String userId,String levelInfo) throws  Exception{
        UserCapital userCapital = new UserCapital();
        UserBilling userBilling = new UserBilling();
        BigDecimal amount= BigDecimal.valueOf(0);
        BigDecimal balance= BigDecimal.valueOf(0);
        if(levelInfo.equals("1")){
            amount = BigDecimal.valueOf(1*0.5);
        }
        if(levelInfo.equals("2")){
            amount = BigDecimal.valueOf(1*0.25);
        }
        if(levelInfo.equals("3")){
            amount = BigDecimal.valueOf(1*0.05);
        }

        String key = MessageFormat.format(CapitalRedisKeyEnums.CAPITAL_ACCOUNT.getCode(), userId);
        balance = BigDecimal.valueOf(Double.valueOf(redisTemplate.opsForValue().get(key)));

        userCapital.setUserId(userId);
        //userCapital.setAmount();

        userBilling.setUserId(userId);
        userBilling.setOperate(CapitalOperateEnums.ADD.toString());
        userBilling.setAmount(amount);
        userBilling.setPurpose(CapitalPurposeEnums.BROKERAGE.toString());
        userBilling.setDescription(levelInfo);
        userBilling.setBalance(balance);
        //调用变换金额的接口

        return  true;

    }

    @Override
    public Boolean registerAgentUser(String userId,String nickName) throws Exception {
        UserInfo userInfo = new UserInfo();
        UserRelation userRelation = new UserRelation();

        userInfo.setUserId(userId);
        userInfo.setNickName(nickName);

        userRelation.setInviterId(userId);

        userInfoDao.updateNickName(userInfo);
        //userRelationDao.updateRelationInfo(userRelation);

        return true;
    }

    @Override
    public Boolean inviteUser(UserRelation userRelation) throws Exception {
        int i =  userRelationDao.saveUserRelation(userRelation);

        if(i==1){
            return  true;
        }else {
            return false;
        }
    }
}
