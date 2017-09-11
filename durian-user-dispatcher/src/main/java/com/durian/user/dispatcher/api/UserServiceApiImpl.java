package com.durian.user.dispatcher.api;

import com.durian.user.domain.to.FindPwd;
import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.LoginService;
import com.durian.user.service.UserService;
import com.durian.user.thrift.api.domain.*;
import com.durian.user.thrift.api.service.UserServiceApi;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.enums.ExceptionCodeEnums;
import com.platform.common.domain.exception.CustomException;
import com.platform.common.domain.to.PageTo;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hufeng on 2017/8/11.
 */
@EnableThriftService(path = "/api/user")
@Service
public class UserServiceApiImpl implements UserServiceApi.Iface{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceApiImpl.class);

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    @Override
    public UserTokenInfoTo login(LoginUserTo loginUserTo) throws UserThriftException,TException {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(loginUserTo, loginUser);
        try {
            //判断用户登录
            UserAllInfo userAllInfo = loginService.login(loginUser);
            UserTokenInfoTo userAllInfoTo = new UserTokenInfoTo();
            TokenInfoTo tokenInfoTo = new TokenInfoTo();
            tokenInfoTo.setAccessToken(userAllInfo.getAccessToken());
            tokenInfoTo.setRefreshToken(userAllInfo.getRefreshToken());
            tokenInfoTo.setExpires(userAllInfo.getExpires());
            userAllInfoTo.setTokenInfoTo(tokenInfoTo);

            UserInfoTo userInfoTo = new UserInfoTo();
            userInfoTo.setId(userAllInfo.getId());
            userInfoTo.setAccountType(userAllInfo.getAccountType());
            userInfoTo.setCreateTime(userAllInfo.getCreateTime());
            userInfoTo.setStatus(userAllInfo.getStatus().toString());

            userAllInfoTo.setUserInfoTo(userInfoTo);
            return userAllInfoTo ;
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public UserTokenInfoTo refreshToken(String refreshToken, String type) throws UserThriftException,TException {
        try {
            UserAllInfo userAllInfo = loginService.refreshToken(refreshToken,type);
            UserTokenInfoTo userAllInfoTo = new UserTokenInfoTo();
            TokenInfoTo tokenInfoTo = new TokenInfoTo();
            tokenInfoTo.setAccessToken(userAllInfo.getAccessToken());
            tokenInfoTo.setRefreshToken(userAllInfo.getRefreshToken());
            tokenInfoTo.setExpires(userAllInfo.getExpires());
            userAllInfoTo.setTokenInfoTo(tokenInfoTo);

            UserInfoTo userInfoTo = new UserInfoTo();
            userInfoTo.setId(userAllInfo.getId());
            userInfoTo.setAccountType(userAllInfo.getAccountType());
            userInfoTo.setCreateTime(userAllInfo.getCreateTime());
            userInfoTo.setStatus(userAllInfo.getStatus().toString());

            userAllInfoTo.setUserInfoTo(userInfoTo);
            return userAllInfoTo;
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }


    @Override
    public void registerUser(RegisterUserTo registerUserTo) throws UserThriftException,TException {

        try {
            RegisterUser registerUser = new RegisterUser();
            BeanUtils.copyProperties(registerUserTo, registerUser);
            //判断用户登录
            UserAllInfo userAllInfo = userService.registerUser(registerUser);

        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public void registerMobileCode(RegisterUserTo registerUserTo) throws UserThriftException,TException {
        try {
            RegisterUser registerUser = new RegisterUser();
            BeanUtils.copyProperties(registerUserTo, registerUser);
            //判断用户登录
            userService.registerMobileCode(registerUser);
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public void findPwdMobileCode(RegisterUserTo registerUserTo) throws UserThriftException,TException {
        try {
            FindPwd findPwd = new FindPwd();
            BeanUtils.copyProperties(registerUserTo, findPwd);
            userService.findPwdMobileCode(findPwd);
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }

    }

    @Override
    public void resetPwd(RegisterUserTo registerUserTo) throws UserThriftException,TException {
        try {
            FindPwd findPwd = new FindPwd();
            BeanUtils.copyProperties(registerUserTo, findPwd);
            userService.resetPwd(findPwd);
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }


    @Override
    public UserAllInfoTo userInfoByUserId(String userId) throws UserThriftException,TException {
        try {
            UserAllInfoTo userAllInfoTo = new UserAllInfoTo();
            UserAllInfo userAllInfo = userService.userInfo(userId);
            userAllInfoTo.setId(userAllInfo.getId());
            userAllInfoTo.setAccountType(userAllInfo.getAccountType());
            userAllInfoTo.setCreateTime(userAllInfo.getCreateTime());
            userAllInfoTo.setStatus(userAllInfo.getStatus().toString());
            return userAllInfoTo;
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public void modifyPwd(String userId, String oldPwd, String newPwd) throws UserThriftException,TException {
        try {
            userService.modifyPwd(userId,oldPwd,newPwd);
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public void registerAgentUser(String userId, String nickName) throws UserThriftException,TException {
        try {
            userService.registerAgentUser(userId,nickName);
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public UserAllInfoTo userInfoByAccessToken(String accessToken) throws UserThriftException,TException {
        try {
            UserAllInfoTo userAllInfoTo = new UserAllInfoTo();
            UserAllInfo userAllInfo = userService.userInfoByToken(accessToken);
            //BeanUtils.copyProperties(userAllInfo,userAllInfoTo);
            userAllInfoTo.setId(userAllInfo.getId());
            userAllInfoTo.setAccountType(userAllInfo.getAccountType());
            userAllInfoTo.setId(userAllInfo.getId());
            userAllInfoTo.setCreateTime(userAllInfo.getCreateTime());
            userAllInfoTo.setStatus(userAllInfo.getStatus().toString());
            return userAllInfoTo;
        } catch (CustomException e) {
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg());
        } catch (Exception e) {
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public ResultUserInfoPageStructTo getAllUserInfo(PageInfoTo pageInfoTo) throws TException {
        ResultUserInfoPageStructTo structTo = new ResultUserInfoPageStructTo();
        try {
            PageTo pageParam = new PageTo();
            BeanUtils.copyProperties(pageInfoTo, pageParam);
            PageInfo<UserAllInfo> pageUserAllInfo = userService.getUserAllInfo(pageParam);
            BeanUtils.copyProperties(pageUserAllInfo, pageInfoTo);
            List<SyntheticalUserAllInfoTo> syntheticalUserAllInfoToList = pageUserAllInfo.getList()
                    .parallelStream()
                    .map(item -> {
                        SyntheticalUserAllInfoTo temp = new SyntheticalUserAllInfoTo();
                        BeanUtils.copyProperties(item, temp, getNullPropertyNames(item));
                        return temp;
                    }).collect(Collectors.toList());
            structTo.setSyntheticalUserAllInfoToList(syntheticalUserAllInfoToList);
            structTo.setCode(200);
            structTo.setPageInfoTo(pageInfoTo);
            structTo.setMessage("获取用户信息成功");
        } catch (Exception e) {
            LOGGER.error("获取用户信息失败", e);
            structTo.setCode(400);
            structTo.setMessage("获取用户信息失败");
            e.printStackTrace();
        }
        return structTo;
    }
}
