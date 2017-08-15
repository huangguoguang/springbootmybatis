package com.durian.user.agent.service;

import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.domain.to.UserLevelRelation;

import java.util.List;


/**
 * 后台用户下线服务
 * @author jack
 *
 */
public interface UserRelationService {

	/**
	 * 用户下线
	 * @param userId
	 * @throws Exception
	 */
	String getUserReferrals(String userId)  throws Exception;

	/**
	 * 用户下线
	 * @param userId
	 * @throws Exception
	 */
	Boolean userReferrals(String userId)  throws Exception;

	/**
	 * 用户佣金
	 * @param userId
	 * @throws Exception
	 */
	Boolean userBrokerage(String userId)  throws Exception;


	/**
	 * 邀请用户
	 * @param userRelation
	 */
	Boolean inviteUser(UserRelation userRelation) throws Exception ;


}
