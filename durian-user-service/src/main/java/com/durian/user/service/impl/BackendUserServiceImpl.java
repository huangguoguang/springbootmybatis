package com.durian.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.durian.user.dao.BackendUserDao;
import com.durian.user.dao.UserBusinessDao;
import com.durian.user.dao.UserInfoDao;
import com.durian.user.domain.enums.LoginTypeEnum;
import com.durian.user.domain.enums.UserExceptionEnum;
import com.durian.user.domain.po.BackendUser;
import com.durian.user.domain.po.BackendUserLogin;
import com.durian.user.domain.po.UserBusiness;
import com.durian.user.domain.po.UserInfo;
import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.domain.to.UserInfoDetail;
import com.durian.user.service.BackendUserService;
import com.durian.user.utils.date.DateFormatUtil;
import com.durian.user.utils.encrypt.MD5Utils;
import com.durian.user.utils.token.TokenGenerator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.exception.CustomException;
import com.platform.common.domain.to.PageTo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 
 * @author hufeng
 *
 */
@Service
public class BackendUserServiceImpl implements BackendUserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendUserServiceImpl.class);
    
    @Autowired
    private BackendUserDao backendUserDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserBusinessDao userBusinessDao;


    @Autowired
    private TokenGenerator tokenGenerator;
    
    @Override
	public UserAllInfo login(LoginUser loginUser) throws Exception {
		UserAllInfo userAllInfo = backendUserDao.getUserInfoByName(loginUser.getName());
		if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_PASSWORD);
        }
		BackendUserLogin backendUserLogin = new BackendUserLogin();
        backendUserLogin.setIp(loginUser.getIp());
        backendUserLogin.setUserId(userAllInfo.getId());
        backendUserLogin.setCreateTime(new Date().getTime());
        backendUserLogin.setType(loginUser.getType());
        LOGGER.debug("用户登录数据:"+JSONObject.toJSONString(loginUser));
		if(!userAllInfo.getPassword().equals(MD5Utils.sign(loginUser.getPassword(), MD5Utils.PWD_KEY, MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET))){
			backendUserLogin.setStatus(1);
			backendUserDao.saveLogin( backendUserLogin);
			throw new CustomException(UserExceptionEnum.USER_MOBILE_PASSWORD);
        }
		//创建token返回用户对象.  设置用户登录时间为 24小时
        Integer expires = 60 * 1;
        String accessToken = tokenGenerator.generatorToken(userAllInfo.getMobile(),userAllInfo.getId(), LoginTypeEnum.BACKEND.getType(), expires);
        userAllInfo.setAccessToken(accessToken);
        //设置刷新登录token
        String refreshToken = tokenGenerator.generatorToken(userAllInfo.getMobile(),userAllInfo.getId(), LoginTypeEnum.BACKEND.getType(), 60 * 6);
        userAllInfo.setRefreshToken(refreshToken);
        userAllInfo.setExpires(expires);
        //设置登录时间
        userAllInfo.setLoginTime(new Date().getTime());
        userAllInfo.setPassword(null);
        userAllInfo.setMobile(null);
        backendUserLogin.setStatus(0);
        backendUserDao.saveLogin( backendUserLogin);
        return userAllInfo;
	}

	@Override
	public UserAllInfo register(RegisterUser registerUser) throws Exception {
		LOGGER.info(registerUser.toString());
        //手机号码格式不正确
        if(StringUtils.isBlank(registerUser.getName())){
            throw new CustomException(UserExceptionEnum.USER_NIKENAME_NULL);
        }
      //判断密码等级
        UserAllInfo userAllInfo = backendUserDao.getUserInfoByName(registerUser.getName());
        if(userAllInfo !=null){
            throw new CustomException(UserExceptionEnum.USER_EXIST);
        }
        return  backendUserDao.saveUser(registerUser);
	}

    @Override
    public UserInfoDetail getUserInfoDetailById(String userId) throws Exception {
        if (userId == null){
            throw new CustomException(UserExceptionEnum.USER_ID_NULL);
        }
        UserInfo userInfo = userInfoDao.getUserInfoByUserId(userId);
        UserBusiness userBusiness = userBusinessDao.getUserBusinessByUserId(userId);
        if (userInfo == null || userBusinessDao == null){
            throw  new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        UserInfoDetail userInfoDetail = new UserInfoDetail();
        userInfoDetail.setUserId(userId);
        userInfoDetail.setName(userBusiness.getName());
        userInfoDetail.setGender(userInfo.getGender());
        userInfoDetail.setMobile(userBusiness.getMobile());
        userInfoDetail.setBirthday(DateFormatUtil.format(userBusiness.getBirthday(),"yyyy-MM-dd"));

        userInfoDetail.setArea(userInfo.getProvince()+userInfo.getCity());
        //userInfoDetail.setRemark();
        //userInfoDetail.setTotalOrder();
        //userInfoDetail.setLastOrderTime();
        //userInfoDetail.setDefaultAddress();
        //userInfoDetail.setAddress();
        //userInfoDetail.setWeixinAccount();
        //userInfoDetail.setQqAccount();
        //userInfoDetail.setWeiboAccont();
        return userInfoDetail;
    }

    @Override
    public PageInfo<BackendUser> getUserList(PageTo pageTo) throws Exception {
        Page<Object> page = PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
        backendUserDao.getUserList();
        PageInfo<BackendUser> pageInfo = new PageInfo(page);
        return pageInfo;
    }
}
