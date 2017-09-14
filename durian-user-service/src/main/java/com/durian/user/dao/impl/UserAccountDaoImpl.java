package com.durian.user.dao.impl;


import com.durian.common.domain.enums.BillingStatisticsEnums;
import com.durian.user.agent.domain.po.UserAgent;
import com.durian.user.agent.domain.po.UserRelation;
import com.durian.user.agent.service.UserAgentService;
import com.durian.user.agent.service.UserRelationService;
import com.durian.user.capital.service.UserCapitalService;
import com.durian.user.dao.UserAccountDao;
import com.durian.user.domain.enums.AccountTypeEnum;
import com.durian.user.domain.enums.UserExceptionEnum;
import com.durian.user.domain.enums.UserRedisKeyEnum;
import com.durian.user.domain.enums.UserStatusEnum;
import com.durian.user.domain.po.UserAccount;
import com.durian.user.domain.po.UserBusiness;
import com.durian.user.domain.po.UserInfo;
import com.durian.user.domain.po.UserLogin;
import com.durian.user.domain.to.FindPwd;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.mapper.UserAccountMapper;
import com.durian.user.mapper.UserBusinessMapper;
import com.durian.user.mapper.UserInfoMapper;
import com.durian.user.mapper.UserLoginMapper;
import com.durian.user.utils.date.DateFormatUtil;
import com.durian.user.utils.date.IdentificationUtil;
import com.durian.user.utils.encrypt.MD5Utils;
import com.platform.common.domain.exception.CustomException;
import com.platform.common.service.redis.util.JsonSerializerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by hufeng on 2017/6/9.
 */
@Service("userAccountDao")
@Transactional
public class UserAccountDaoImpl implements UserAccountDao {

    @Autowired
    private UserAccountMapper userAccountMapper ;

    @Autowired
    private UserInfoMapper userInfoMapper ;

    @Autowired
    private UserBusinessMapper userBusinessMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserCapitalService userCapitalService ;

    @Autowired
    private UserRelationService userRelationService ;


    @Autowired
    private UserAgentService userAgentService;

    @Override
    public UserAllInfo saveUser(RegisterUser registerUser) throws Exception {


        UserAccount userAccount = new UserAccount();
        userAccount.setId(IdentificationUtil.generateTimestamp16());
        userAccount.setAccountType(AccountTypeEnum.NORMAL.getCode());
        userAccount.setCreateTime(new Date().getTime());
        userAccount.setStatus(UserStatusEnum.Enable.getCode());
        userAccountMapper.insert(userAccount);


        //用户基础信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userAccount.getId());
        userInfo.setCreateTime(new Date().getTime());
        userInfo.setNickName(registerUser.getNickName());
        userInfoMapper.insert(userInfo);

        //用户敏感信息
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.setMobile(registerUser.getMobile());
        //userBusiness.setPassword(MD5Utils.sign(registerUser.getPassword(), MD5Utils.PWD_KEY, MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET));
        userBusiness.setPassword(MD5Utils.sign(registerUser.getMobile().substring(5), MD5Utils.PWD_KEY, MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET));
        userBusiness.setUserId(userAccount.getId());
        userBusiness.setCreateTime(new Date().getTime());

        //设置redis内存对象
        UserAllInfo userAllInfo = new UserAllInfo();
        userAllInfo.setId(userAccount.getId());
        userAllInfo.setMobile(registerUser.getMobile());
        userAllInfo.setAccountType(userAccount.getAccountType());
        userAllInfo.setStatus(userAccount.getStatus());
        userBusinessMapper.insert(userBusiness);

        //初始化代理数据
        UserAgent userAgent = new UserAgent();
        userAgent.setUserId(userAllInfo.getId());
        userAgent.setCreateTime(new Date().getTime());
        userAgentService.insertUserAgent(userAgent);


        //
        if(registerUser.getInviterId() !=null){
            // //新增用户关系
            UserRelation userRelation = new UserRelation();
            userRelation.setDeptId(registerUser.getDeptId());
            userRelation.setDeptCode(registerUser.getDeptId());
            userRelation.setInviteeId(userAllInfo.getId());
            userRelation.setInviterId(registerUser.getInviterId());
            userRelationService.inviteUser(userRelation);
        }
        //
        //创建资金账户
        userCapitalService.createUserCapital(userAllInfo.getId());
        //创建代理关系

        //今日注册量,注册成功一个加1
        redisTemplate.opsForValue().increment(BillingStatisticsEnums.TODAY_USER_REGISTER.getCode(),1);
        return userAllInfo;

    }

    @Override
    public UserAllInfo getUserInfoByMoblie(String moblie) {
        UserAllInfo userAllInfo = userAccountMapper.selectByMoblie(moblie);
        return userAllInfo;
    }

    @Override
    public UserAllInfo getUserInfoById(String id) {
        UserAllInfo  userAllInfo  = JsonSerializerUtils.deserialize(redisTemplate.opsForValue().get(MessageFormat.format(UserRedisKeyEnum.USER_ALL_INFO.getKey(), id)),UserAllInfo.class);
        if(userAllInfo ==null ){
            userAllInfo  = userAccountMapper.selectById(id);
            redisTemplate.opsForValue().set(MessageFormat.format(UserRedisKeyEnum.USER_ALL_INFO.getKey(), id),JsonSerializerUtils.seriazile(userAllInfo));
        }
        return userAllInfo ;
    }

    @Override
    public void updatePwd(FindPwd findPwd) {
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.setMobile(findPwd.getMobile());
        userBusiness.setPassword(MD5Utils.sign(findPwd.getPassword(), MD5Utils.PWD_KEY ,MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET));
        userBusiness.setUpdateTime(new Date().getTime());
        userBusinessMapper.updateByMobile(userBusiness);
        //密码更新成功.
    }

    @Override
    public int saveLogin(UserLogin userLogin) {
        return userLoginMapper.insert(userLogin);
    }

	@Override
	public int updateUserStatus(String id,Integer status) throws Exception {
		UserAllInfo userAllInfo = getUserInfoById(id);
		if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
		UserAccount record = new UserAccount();
		record.setStatus(status);
		record.setUpdateTime(new Date().getTime());
		record.setId(id);
		int count = userAccountMapper.updateStatusById(record);
		userAllInfo.setStatus(status);
		redisTemplate.opsForValue().set(MessageFormat.format(UserRedisKeyEnum.USER_ALL_INFO.getKey(), userAllInfo.getId()),JsonSerializerUtils.seriazile(userAllInfo));
		return count;
	}

    @Override
    public int updateUserAllInfo(UserAllInfo userAllInfo) throws Exception {
        //用户基础信息
        UserInfo userInfo = userInfoMapper.selectByUserId(userAllInfo.getId());
        if (userInfo == null) {
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        userInfo.setNickIcon(userAllInfo.getNickIcon());
        userInfo.setNickName(userAllInfo.getNickName());
        userInfo.setGender(userAllInfo.getGender());
        userInfo.setProvince(userAllInfo.getProvince());
        userInfo.setCity(userAllInfo.getCity());
        userInfo.setArea(userAllInfo.getArea());
        userInfo.setStreet(userAllInfo.getStreet());
        userInfo.setAddress(userAllInfo.getAddress());
        userInfo.setUpdateTime(new Date().getTime());
        int userInfoCount = userInfoMapper.updateByPrimaryKeySelective(userInfo);

        //用户敏感信息
        UserBusiness userBusiness = userBusinessMapper.selectByUserId(userAllInfo.getId());
        if (userBusiness == null) {
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        userBusiness.setName(userAllInfo.getName());
        userBusiness.setIdCard(userAllInfo.getIdCard());
        userBusiness.setEducation(userAllInfo.getEducation());
        userBusiness.setUpdateTime(new Date().getTime());
        userBusiness.setBirthday(userAllInfo.getBirthday() == null ? null :DateFormatUtil.convertDate(userAllInfo.getBirthday(), "yyyyMMdd"));
        int userBusinessCount = userBusinessMapper.updateByPrimaryKeySelective(userBusiness);
        return (userInfoCount == 1 && userBusinessCount == 1) ? 1 : 0;
    }

    @Override
    public List<UserAllInfo> selectUserInfoList(UserAllInfo userAllInfo) throws Exception {
        return userAccountMapper.selectUserInfoList(userAllInfo);
    }

    @Override
    public List<UserAllInfo> syntheticalUserAllInfoList() throws Exception {
        return userAccountMapper.syntheticalUserAllInfoList();
    }

    @Override
    public Integer getUserRegisterCount(String startDate) {
        return userAccountMapper.getUserRegisterCount(startDate);
    }



}
