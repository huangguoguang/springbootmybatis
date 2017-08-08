package com.durian.user.service;

import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.domain.to.UserInfoDetail;

/**
 * 后台用户服务
 * @author hufeng
 *
 */
public interface BackendUserService {

	/**
	 * 注册用户
	 * @param registerUser
	 * @throws Exception
	 */
	UserAllInfo register(RegisterUser registerUser)  throws Exception;

	
	/**
	 * 用户登录
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	UserAllInfo login(LoginUser loginUser) throws Exception;

	/**
	 * 查询用户信息详情
	 * @author daixibtiao
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	UserInfoDetail getUserInfoDetailById (String userId) throws Exception;
}
