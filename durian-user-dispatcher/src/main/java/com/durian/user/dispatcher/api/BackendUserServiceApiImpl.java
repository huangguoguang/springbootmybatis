package com.durian.user.dispatcher.api;

import com.durian.user.domain.po.BackendUser;
import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.BackendUserService;
import com.durian.user.service.UserService;
import com.durian.user.thrift.api.domain.*;
import com.durian.user.thrift.api.service.BackendUserServiceApi;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.enums.ExceptionCodeEnums;
import com.platform.common.domain.exception.CustomException;
import com.platform.common.domain.to.PageTo;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hufeng on 2017/8/11.
 */
@EnableThriftService(path = "/api/backenduser")
@Service
public class BackendUserServiceApiImpl implements BackendUserServiceApi.Iface{

    private static final Logger LOGGER = LoggerFactory.getLogger(BackendUserServiceApiImpl.class);

    @Autowired
    private BackendUserService backendUserService;


    @Autowired
    private UserService userService;

    @Override
    public UserAllInfoTo login(LoginUserTo loginUserTo) throws UserThriftException,TException {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(loginUserTo, loginUser);
        try {
            //判断用户登录
            UserAllInfo userAllInfo = backendUserService.login(loginUser);
            UserAllInfoTo userAllInfoTo = new UserAllInfoTo();
            userAllInfoTo.setAccessToken(userAllInfo.getAccessToken());
            userAllInfoTo.setRefreshToken(userAllInfo.getRefreshToken());
            userAllInfoTo.setExpires(userAllInfo.getExpires());

            userAllInfoTo.setId(userAllInfo.getId());
            userAllInfoTo.setAccountType(userAllInfo.getAccountType());
            userAllInfoTo.setCreateTime(userAllInfo.getCreateTime());
            userAllInfoTo.setStatus(String.valueOf(userAllInfo.getStatus()));

             return userAllInfoTo ;
        } catch (CustomException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg(),e.getCode().getHttpCode());
        } catch (Exception e) {
             LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg(),ExceptionCodeEnums.SYSTEM_ERROR.getHttpCode());
        }
    }

    @Override
    public ResultBackendUserInfoPageStructTo getUserList(PageInfoTo pageInfoTo) throws UserThriftException,TException{
        try {
            //判断用户登录
            PageTo pageParam = new PageTo();
            BeanUtils.copyProperties(pageInfoTo, pageParam);
            PageInfo<BackendUser> userAllInfoPage = backendUserService.getUserList(pageParam);
            ResultBackendUserInfoPageStructTo resultBackendUserInfoPageStructTo = new ResultBackendUserInfoPageStructTo();
            BeanUtils.copyProperties(userAllInfoPage, pageInfoTo);

            List<BackendUserTo> guessGoodsList = userAllInfoPage.getList()
                    .parallelStream()
                    .map(item -> {
                        BackendUserTo temp = new BackendUserTo();
                        temp.setId(item.getId());
                        temp.setStatus(item.getStatus());
                        temp.setCreateTime(item.getCreateTime());
                        temp.setName(item.getName());
                        temp.setPassword(item.getPassword());
                        //JSONObject.parseObject()
                        //BackendUserTo jb = (BackendUserTo) JSONObject.toJavaObject(,BackendUserTo.class);
;                        //BeanUtils.copyProperties(item, temp);
                        return temp;
                    }).collect(Collectors.toList());

            resultBackendUserInfoPageStructTo.setBackendUserToList(guessGoodsList);
            resultBackendUserInfoPageStructTo.setPageInfoTo(pageInfoTo);
            return resultBackendUserInfoPageStructTo ;
        } catch (CustomException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg(),e.getCode().getHttpCode());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg(),ExceptionCodeEnums.SYSTEM_ERROR.getHttpCode());
        }
    }

    @Override
    public String enableUser(String userId) throws UserThriftException, TException {
        try {
            userService.enableUser(userId);
            return "启用成功";
        } catch (CustomException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg(),e.getCode().getHttpCode());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg(),ExceptionCodeEnums.SYSTEM_ERROR.getHttpCode());
        }
    }

    @Override
    public String disableUser(String userId) throws UserThriftException, TException {
        try {
            userService.disableUser(userId);
            return "禁用成功";
        } catch (CustomException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(e.getCode().getCode(),e.getCode().getMsg(),e.getCode().getHttpCode());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw new UserThriftException(ExceptionCodeEnums.SYSTEM_ERROR.getCode(),ExceptionCodeEnums.SYSTEM_ERROR.getMsg(),ExceptionCodeEnums.SYSTEM_ERROR.getHttpCode());
        }
    }


}
