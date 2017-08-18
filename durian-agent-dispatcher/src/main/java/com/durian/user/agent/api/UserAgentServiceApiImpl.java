package com.durian.user.agent.api;


import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.domain.po.UserAgentConfig;
import com.durian.user.agent.domain.to.UserLevelRelation;
import com.durian.user.agent.service.UserRelationService;
import com.durian.user.agent.thrift.api.domain.*;
import com.durian.user.agent.thrift.api.service.UserAgentServiceApi;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.to.PageTo;
import com.platform.common.thrift.context.rpc.RpcContext;
import com.platform.common.thrift.context.rpc.RpcContextBean;
import com.platform.common.thrift.context.rpc.RpcContextParams;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务API实现
 * <p>
 * Created by wangyang on 2017/5/22.
 */
@EnableThriftService(path = "/api/agent")
@Slf4j
public class UserAgentServiceApiImpl implements UserAgentServiceApi.Iface {

    @Autowired
    private UserRelationService userRelationService;


    @Override
    public ResultDataStructTo userAgentCount(String userId) throws TException {

        ResultDataStructTo result = new ResultDataStructTo();
        try {
            UserLevelRelation userLevelRelation = new UserLevelRelation();
            userLevelRelation = userRelationService.userReferrals(userId);
            UserLevelRelationTo userLevelRelationTo = new UserLevelRelationTo();
            BeanUtils.copyProperties(userLevelRelation, userLevelRelationTo);
            result.setUserLevelRelationTo(userLevelRelationTo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    @Override
    public boolean registerUserAgent(String userId, String agentName) throws TException {
        try {
            return userRelationService.registerUserAgent(userId, agentName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean userAgentBrokerage(String userId, double amount) throws TException {
        try {
            return userRelationService.userBrokerAge(userId, BigDecimal.valueOf(amount));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveUserRelation(UserRelationTo userRelation) throws TException {
        try {
            UserRelation userRelationParam = new UserRelation();
            BeanUtils.copyProperties(userRelation, userRelationParam);
            return userRelationService.inviteUser(userRelationParam);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveUserAgentConfig(UserAgentConfigTo UserAgentConfig) throws TException {
        try {
            UserAgentConfig userAgentConfigParam = new UserAgentConfig();
            BeanUtils.copyProperties(UserAgentConfig, userAgentConfigParam);
            return userRelationService.saveUserAgentConfig(userAgentConfigParam);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserAgentConfig(UserAgentConfigTo UserAgentConfig) throws TException {

        try {
            UserAgentConfig userAgentConfigParam = new UserAgentConfig();
            BeanUtils.copyProperties(UserAgentConfig, userAgentConfigParam);
            return userRelationService.updateUserAgentConfig(userAgentConfigParam);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ResultDataConfigStructTo getUserAgentConfig(String id) throws TException {

        ResultDataConfigStructTo result = new ResultDataConfigStructTo();

        try {
            UserAgentConfig userAgentConfigParam = new UserAgentConfig();
            UserAgentConfigTo userAgentConfigTo = new UserAgentConfigTo();
            userAgentConfigParam = userRelationService.getUserAgentConfig(id);
            BeanUtils.copyProperties(userAgentConfigParam, userAgentConfigTo);
            result.setUserAgentConfigTo(userAgentConfigTo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
}



