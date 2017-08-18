package com.durian.user.agent.service;

import com.durian.user.agent.domain.po.UserAgentConfig;
import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.domain.to.UserLevelRelation;

import java.math.BigDecimal;


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


	/**
	 * 获取代理比例信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserAgentConfig getUserAgentConfig(String id) throws Exception;

	/**
	 * 更新代理比例信息
	 * @param userAgentConfig
	 * @return
	 * @throws Exception
	 */
	Boolean updateUserAgentConfig(UserAgentConfig userAgentConfig) throws Exception;

	/**
	 * 保存代理比例信息
	 * @param userAgentConfig
	 * @return
	 * @throws Exception
	 */
	Boolean saveUserAgentConfig(UserAgentConfig userAgentConfig) throws Exception;






}
