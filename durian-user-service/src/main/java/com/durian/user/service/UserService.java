package com.durian.user.service;


import com.durian.user.domain.po.UserLogin;
import com.durian.user.domain.to.FindPwd;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.to.PageTo;

import java.util.List;

/**
 * 用户服务接口定义
 * <p>
 * Created by wangyang on 2017/5/11.
 */
public interface UserService {

    /**
     * 注册用户
     * @param registerUser
     */
	UserAllInfo registerUser(RegisterUser registerUser) throws Exception ;


	/**
	 * 注册为代理用户
	 * @param userId
	 * @param nickName
	 */
	void registerAgentUser(String userId,String nickName) throws Exception ;


	/**
     *
     * @param registerUser
     * @return
     * @throws Exception
     */
    UserAllInfo validateAuthCode(RegisterUser registerUser) throws Exception;


    /**
     * 用户手机号码发送短信
     * @param registerUser
     */
    void registerMobileCode(RegisterUser registerUser) throws Exception;


    /**
     * 找回密码
     * @param findPwd
     * @return
     * @throws Exception
     */
    String findPwd(FindPwd findPwd)  throws Exception ;


    /**
     * 找回密码获取短信验证码
     * @param findPwd
     * @return
     * @throws Exception
     */
    String findPwdMobileCode(FindPwd findPwd) throws Exception ;

    /**
     * 重置密码
     * @param findPwd
     * @return
     * @throws Exception
     */
    void resetPwd(FindPwd findPwd)  throws Exception ;

    

    /**
     * 启用用户
     * @param userId
     * @return
     */
	String enableUser(String userId) throws Exception ;


	/**
	 * 禁用用户
	 * @param userId
	 * @return
	 */
	String disableUser(String userId) throws Exception ;

	/**
	 * 
	 * @param userId
	 * @return
	 */
	UserAllInfo userInfo(String userId) throws Exception ;

	/**
	 * 设置个人信息
	 * @param userId
	 * @param userAllInfo
	 * @return
	 * @throwsException
	 */
	String updateUserInfo(String userId, UserAllInfo userAllInfo) throws Exception;

	/**
	 * 注册用户列表
	 * @param userId
	 * @return
	 */
	List<UserAllInfo> selectUserInfoList(String userId) throws Exception;

	/**
	 * 用户登录历史
	 * @author daixibiao
	 * @param userId
	 * @return
	 * @throws Exception
     * */
	List<UserLogin> userLoginHistory(String userId) throws Exception;


	/**
	 * 修改密码
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	String modifyPwd(String userId, String oldPwd, String newPwd) throws Exception;


	/**
	 * 用户token转用户ID
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	UserAllInfo userInfoByToken(String accessToken)throws Exception;


	void test(String levelAllot1, String levelAllot2, String levelAllot3);

	/**
	 * 获取提现订单列表
	 * @param pageParam
	 * @return
	 * */
	PageInfo<UserAllInfo> getUserAllInfo(PageTo pageParam) throws Exception;


	/**
	 * 用户分页列表查询
	 * @return
	 * @throws Exception
	 */
	List<UserAllInfo> getAllUserInfo() throws Exception;


	/**
	 * 查询今日注册量
	 * @return
	 * @throws Exception
	 */
	Integer statisticsTodayUserRegister() throws Exception;


	/**
	 * 查询制定时间注册量
	 * @return
	 * @throws Exception
	 */
	Integer statisticsUserRegisterCount(String startDate) throws Exception;
}

