package com.durian.user.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.durian.common.domain.enums.BillingStatisticsEnums;
import com.durian.tools.sms.domain.enums.SmsSendTypeEnums;
import com.durian.tools.sms.domain.exception.SmsException;
import com.durian.tools.sms.domain.po.SmsVerifyMessage;
import com.durian.tools.sms.service.SmsService;
import com.durian.user.agent.domain.po.UserAgentConfig;
import com.durian.user.agent.service.UserAgentConfigService;
import com.durian.user.agent.service.UserAgentService;
import com.durian.user.agent.service.UserRelationService;
import com.durian.user.dao.*;
import com.durian.user.domain.enums.*;
import com.durian.user.domain.po.UserBusiness;
import com.durian.user.domain.po.UserLogin;
import com.durian.user.domain.po.UserSms;
import com.durian.user.domain.to.FindPwd;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.UserService;
import com.durian.user.utils.code.RandomValidateCode;
import com.durian.user.utils.encrypt.MD5Utils;
import com.durian.user.utils.token.Token;
import com.durian.user.utils.token.TokenGenerator;
import com.durian.user.utils.validate.RegexValidateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.exception.CustomException;
import com.platform.common.domain.to.PageTo;
import com.platform.common.service.redis.util.JsonSerializerUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 用户服务接口定义实现
 * <p>
 * Created by hufeng on 2017/5/11.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserAccountDao userAccountDao ;

    @Autowired
    private UserSmsDao userSmsDao ;

    @Autowired
    private UserBusinessDao userBusinessDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserAgentService userAgentService;

    @Autowired
    private UserAgentConfigService userAgentConfigService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private UserRelationService userRelationService ;


    @Override
    public UserAllInfo registerUser(RegisterUser registerUser) throws Exception {
        LOGGER.info("用户登录:"+ JSONObject.toJSONString(registerUser));
        // 手机号码格式不正确
        if(StringUtils.isBlank(registerUser.getMobile())){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_NULL);
        }

        //手机号码格式不正确
        if(!RegexValidateUtil.checkMobile(registerUser.getMobile())){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_FORMAT);
        }

        //手机密码不能为空
        if(StringUtils.isBlank(registerUser.getPassword())){
            throw new CustomException(UserExceptionEnum.USER_PASSWORD_NULL);
        }

        //手机号码格式不正确
        if(StringUtils.isBlank(registerUser.getMobileCode())){
            throw new CustomException(UserExceptionEnum.USER_MOBLLE_CODE_NULL);
        }
/*        //判断昵称
        if(StringUtils.isBlank(registerUser.getNickName())){
            throw new CustomException(UserExceptionEnum.USER_NIKENAME_NULL);
        }*/
    	//判断手机短信

        try{
            SmsVerifyMessage smsVerifyMessage = new SmsVerifyMessage();
            smsVerifyMessage.setVerifyCode(registerUser.getMobileCode());
            smsVerifyMessage.setMobile(registerUser.getMobile());
            smsVerifyMessage.setOperator(registerUser.getMobile());
            smsVerifyMessage.setSystem("guess");
            smsVerifyMessage.setServices("user");
            smsVerifyMessage.setType("register");
            smsVerifyMessage.setDescription("用户手机号码注册");
            smsVerifyMessage.setOperator(registerUser.getMobile());
            smsVerifyMessage.setContent(registerUser.getMobileCode());
            boolean sendOk = smsService.verifyMessage(smsVerifyMessage);
            if(!sendOk){
                throw new CustomException(UserExceptionEnum.USER_MOBLLE_CODE_ERROR);
            }
           // LOGGER.info("用户手机号码:"+registerUser.getMobile()+" ,发送验证短信:"+content +" ,状态状态为: "+sendOk +" !");
        }catch (SmsException e){
            //throw new CustomException(e.getMessage());
            throw new CustomException(UserExceptionEnum.USER_MOBLLE_CODE_ERROR,e.getMessage());
        }

    	//判断密码等级
        UserAllInfo userAllInfo = userAccountDao.getUserInfoByMoblie(registerUser.getMobile());
        if(userAllInfo !=null){
            throw new CustomException(UserExceptionEnum.USER_EXIST);
        }
        userAllInfo = userAccountDao.saveUser(registerUser);
        return userAllInfo;

    }


    @Override
    public void registerAgentUser(String userId,String nickName) throws Exception {
        boolean agentOk = userAgentService.registerUserAgent(userId,nickName);
        LOGGER.info("用户:"+userId+"  设置代理昵称:"+nickName + " 结果为:"+agentOk);
    }

    @Override
    public UserAllInfo validateAuthCode(RegisterUser registerUser) throws Exception {
        return  null ;
    }

    @Override
    public void registerMobileCode(RegisterUser registerUser) throws Exception {
        //手机号码格式不正确
        if(StringUtils.isBlank(registerUser.getMobile())){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_NULL);
        }
        //手机号码格式不正确
        if(!RegexValidateUtil.checkMobile(registerUser.getMobile())){
            throw new CustomException(UserExceptionEnum.USER_MOBILE_FORMAT);
        }

        String mobileCode = RandomValidateCode.getRandomNumber(4);
        String content = MessageFormat.format(UserSmsEnum.REGISTER_CONTENT.getDesc(), mobileCode);

        try{
            SmsVerifyMessage smsVerifyMessage = new SmsVerifyMessage();
            smsVerifyMessage.setMobile(registerUser.getMobile());
            smsVerifyMessage.setContent(content);
            smsVerifyMessage.setSystem("guess");
            smsVerifyMessage.setServices("user");
            smsVerifyMessage.setType("register");
            smsVerifyMessage.setDescription("用户手机号码注册");
            smsVerifyMessage.setOperator(registerUser.getMobile());
            smsVerifyMessage.setVerifyCode(mobileCode);
            smsVerifyMessage.setActiveSecond(60*5);
            boolean sendOk= smsService.sendMessage(smsVerifyMessage, SmsSendTypeEnums.TEXT);
            if(!sendOk){
                throw new CustomException(UserExceptionEnum.USER_MOBLLE_CODE_ERROR);
            }
            LOGGER.info("用户手机号码:"+registerUser.getMobile()+" ,发送验证短信:"+content +" ,状态状态为: "+sendOk +" !");
        }catch (SmsException e){
            //throw new CustomException(e.getMessage());
            throw new CustomException(UserExceptionEnum.USER_MOBLLE_CODE_ERROR,e.getMessage());
        }


    }

    @Override
    public String findPwd(FindPwd findPwd) throws Exception {
        UserAllInfo userAllInfo = userAccountDao.getUserInfoByMoblie(findPwd.getMobile());
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-","") ;
        redisTemplate.opsForValue().set(uuid,userAllInfo.getId(), 60*5, TimeUnit.SECONDS);
        return uuid ;
    }

    @Override
    public String findPwdMobileCode(FindPwd findPwd) throws Exception {
        findPwd.setMobile(findPwd.getMobile());
        String mobileCode = RandomValidateCode.getRandomNumber(4);
        String content = MessageFormat.format(UserSmsEnum.FINDPWD_CONTENT.getDesc(), mobileCode);
        UserSms userSms = new UserSms();
        userSms.setContent(content);
        userSms.setCreateTime(new Date().getTime());
        userSms.setDescription(UserSmsEnum.FIND_PWD.getDesc());
        userSms.setType(UserSmsEnum.FIND_PWD.getCode());
        userSms.setMobile(findPwd.getMobile());
        SmsVerifyMessage smsVerifyMessage = new SmsVerifyMessage();
        smsVerifyMessage.setMobile(findPwd.getMobile());
        smsVerifyMessage.setContent(content);
        smsVerifyMessage.setServices("user");
        smsVerifyMessage.setDescription("找回密码");
        smsVerifyMessage.setOperator(findPwd.getMobile());
        smsVerifyMessage.setType("register");
        smsVerifyMessage.setSystem("user");
        smsVerifyMessage.setVerifyCode(mobileCode);
        smsVerifyMessage.setActiveSecond(60*5);
        boolean sendOk = smsService.sendMessage(smsVerifyMessage,SmsSendTypeEnums.TEXT);
        LOGGER.info("用户手机号码:"+findPwd.getMobile()+" ,发送验证短信:"+content +" ,状态状态为: "+sendOk+" !");
        return null;
    }

    @Override
    public void resetPwd(FindPwd findPwd) throws Exception {
        //判断手机短信
        if(!findPwd.getMobileCode().equalsIgnoreCase(redisTemplate.opsForValue().get("mobilecode:"+findPwd.getMobile()+":"+UserSmsEnum.FIND_PWD.getCode()))){
            throw new CustomException(UserExceptionEnum.USER_MOBLLE_CODE_ERROR);
        }
        userAccountDao.updatePwd(findPwd);
        //删除短信验证码.防止重复修改密码了.
        redisTemplate.delete("mobilecode:" + findPwd.getMobile() + ":" + UserSmsEnum.FIND_PWD.getCode());
    }

	@Override
	public int enableUser(String userId)  throws Exception {
		return userAccountDao.updateUserStatus(userId, UserStatusEnum.Enable.getCode());
	}

	@Override
	public int disableUser(String userId)  throws Exception {
		return  userAccountDao.updateUserStatus(userId, UserStatusEnum.Disable.getCode());
	}

	@Override
	public UserAllInfo userInfo(String userId)  throws Exception {
		UserAllInfo userAllInfo = userAccountDao.getUserInfoById(userId);
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
		return userAllInfo;
	}

    @Override
    public String updateUserInfo(String userId, UserAllInfo userAllInfo)throws Exception {
        //从Redis中取UserAllInfo
        UserAllInfo oldUserAllInfo = userAccountDao.getUserInfoById(userId);
        if(oldUserAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        userAllInfo.setId(oldUserAllInfo.getId());
        userAllInfo.setAccountType(oldUserAllInfo.getAccountType());
        userAllInfo.setStatus(oldUserAllInfo.getStatus());
        userAllInfo.setMobile(oldUserAllInfo.getMobile());
        userAllInfo.setAccessToken(null);
        int count = userAccountDao.updateUserAllInfo(userAllInfo);
        if (count == 1) {
            //更新个人信息成功，更新redis
            redisTemplate.opsForValue().set(MessageFormat.format(UserRedisKeyEnum.USER_ALL_INFO.getKey(), userAllInfo.getId()), JsonSerializerUtils.seriazile(userAllInfo));
        }
        return null;
    }

    @Override
    public List<UserAllInfo> selectUserInfoList(String userId) throws Exception{
        //从Redis中取UserAllInfo
        UserAllInfo oldUserAllInfo = userAccountDao.getUserInfoById(userId);
        if(oldUserAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        UserAllInfo userAllInfo = new UserAllInfo();
        return userAccountDao.selectUserInfoList(userAllInfo);
    }

    @Override
    public List<UserLogin> userLoginHistory(String userId) throws Exception {
        //从Redis中取UserAllInfo
        List<UserLogin> userLoginList = userLoginDao.getLoginHistoryList(userId);
        if(userLoginList == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        return userLoginList;
    }

    @Override
    public String modifyPwd(String userId, String oldPwd, String newPwd) throws Exception{
        //从Redis中取UserAllInfo
        UserAllInfo oldUserAllInfo = userAccountDao.getUserInfoById(userId);
        if(oldUserAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        //获取要修改的用户敏感信息
        UserBusiness userBusiness = userBusinessDao.getUserBusinessByUserId(oldUserAllInfo.getId());
        if (!userBusiness.getPassword().equals(MD5Utils.sign(oldPwd, MD5Utils.PWD_KEY, MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET))) {
            throw new CustomException(UserExceptionEnum.USER_PASSWORD_ERROR);
        }
        userBusiness.setPassword(MD5Utils.sign(newPwd, MD5Utils.PWD_KEY, MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET));
        int count = userBusinessDao.updateUserBusiness(userBusiness);
        if(count < 1) {
            throw new CustomException((UserExceptionEnum.MODIFY_PASSWORD_ERROR));
        }
        return null;
    }

	@Override
	public UserAllInfo userInfoByToken(String accessToken) throws Exception {
		Token token =  tokenGenerator.validateToken(accessToken);
		if(token==null){
	        throw new CustomException(TokenExceptionEnum.TOKEN_EXPIRES_CODE);
	    }
		UserAllInfo  userAllInfo =  userAccountDao.getUserInfoById(token.getUserId());
        if(userAllInfo == null){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
		return userAllInfo ;
	}

    @Override
    public void test(String levelAllot1, String levelAllot2, String levelAllot3) {
        try {
            UserAgentConfig userAgentConfig = new UserAgentConfig();
            userAgentConfig.setLevelAllot1(levelAllot1);
            userAgentConfig.setLevelAllot2(levelAllot2);
            userAgentConfig.setLevelAllot3(levelAllot3);
            userAgentConfigService.saveUserAgentConfig(userAgentConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<UserAllInfo> getUserAllInfo(PageTo pageParam) throws Exception {
        Page<Object> page = PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        userAccountDao.syntheticalUserAllInfoList();
        PageInfo<UserAllInfo> pageInfo = new PageInfo(page);
        return pageInfo;
    }

    @Override
    public List<UserAllInfo> getAllUserInfo() throws Exception {
        return userAccountDao.syntheticalUserAllInfoList();
    }

    @Override
    public Integer statisticsTodayUserRegister() throws Exception {
        return Integer.valueOf(StringUtils.defaultIfBlank((String)redisTemplate.opsForValue().get(BillingStatisticsEnums.TODAY_USER_REGISTER.getCode()),"0"));
    }

    @Override
    public Integer statisticsUserRegisterCount(String startDate) throws Exception {
        return userAccountDao.getUserRegisterCount(startDate);
    }



}

