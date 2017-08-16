package com.durian.user.agent.service;

import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.domain.to.UserLevelRelation;

import java.math.BigDecimal;
import java.util.List;


/**
 * 后台用户下线服务
 * @author jack
 *
 */
public interface UserRelationService {

	/**
	 * 得到用户下线信息
	 * @param userId
	 * @throws Exception
	 */
	String getUserReferrals(String userId)  throws Exception;

	/**
	 * 用户下线
	 * @param userId
	 * @throws Exception
	 */
	UserLevelRelation userReferrals(String userId)  throws Exception;

	/**
	 * 用户佣金
	 * @param userId
	 * @throws Exception
	 */
	Boolean userBrokerAge(String userId,BigDecimal brokerAge)  throws Exception;


	/**
	 * 邀请用户
	 * @param userRelation
	 */
	Boolean inviteUser(UserRelation userRelation) throws Exception ;


	/**
	 * 注册代理用户
	 * @param userId
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	Boolean registerUserAgent(String userId,String userName) throws Exception;


}
