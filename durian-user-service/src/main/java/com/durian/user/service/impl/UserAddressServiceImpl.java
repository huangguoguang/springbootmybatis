package com.durian.user.service.impl;

import java.util.Date;
import java.util.List;

import com.durian.user.dao.UserAccountDao;
import com.durian.user.dao.UserAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durian.user.domain.enums.AddressStatusEnum;
import com.durian.user.domain.enums.UserExceptionEnum;
import com.durian.user.domain.po.UserAddress;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.UserAddressService;
import com.platform.common.domain.exception.CustomException;

/**
 * Created by huangguang on 2017/7/6.
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private UserAddressDao userAddressDao;

    @Override
    public List<UserAddress> userAddressList(String userId) throws Exception {
        UserAllInfo userAllInfo = userAccountDao.getUserInfoById(userId);
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userAllInfo.getId());
        userAddress.setDelTag("1");
        return userAddressDao.getUserAddressList(userAddress);
    }

    @Override
    public String addUserAddress(String userId, UserAddress userAddress) throws Exception {
        UserAllInfo userAllInfo = userAccountDao.getUserInfoById(userId);
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        if (AddressStatusEnum.Default.getCode().equals(userAddress.getStatus())) {
            checkAddressStatus(userAllInfo, userAddress);//当前保存的地址要设为默认，需要先判定收货地址状态
        }
        if (userAddress.getStatus() == null) {//没有选择是否默认时保存为非默认
            userAddress.setStatus(AddressStatusEnum.UnDefault.getCode());
        }
        userAddress.setUserId(userAllInfo.getId());
        userAddress.setCreateTime(new Date().getTime());
        int count = userAddressDao.saveUserAddress(userAddress) ;
        return null ;
    }

    @Override
    public String updateUserAddress(String userId, UserAddress userAddress) throws Exception {
        UserAllInfo userAllInfo = userAccountDao.getUserInfoById(userId);
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        if (AddressStatusEnum.Default.getCode().equals(userAddress.getStatus())) {
            checkAddressStatus(userAllInfo, userAddress);//当前保存的地址要设为默认，需要先判定收货地址状态
        }
        if (userAddress.getStatus() == null) {//没有选择是否默认时保存为非默认
            userAddress.setStatus(AddressStatusEnum.UnDefault.getCode());
        }
        userAddress.setUpdateTime(new Date().getTime());
        int count = userAddressDao.updateUserAddress(userAddress);
        return null;
    }

    @Override
    public UserAddress getUserAddressById(String userId, String id) throws Exception {
        UserAllInfo userAllInfo = userAccountDao.getUserInfoById(userId);
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        return userAddressDao.getUserAddressById(Integer.valueOf(id));
    }

    @Override
    public String deleteUserAddress(String userId, UserAddress userAddress) throws Exception{
        UserAllInfo userAllInfo = userAccountDao.getUserInfoById(userId);
        if(userAllInfo == null ){
            throw new CustomException(UserExceptionEnum.USER_NOT_EXIST);
        }
        //删除收货地址，将删除状态改为0，将默认状态设为非默认
        userAddress.setDelTag("0");
        userAddress.setStatus(AddressStatusEnum.UnDefault.getCode());
        userAddress.setUpdateTime(new Date().getTime());
        int count = userAddressDao.updateUserAddress(userAddress);
        return null;
    }

    /**
     * //目前要保存的地址不是默认则直接保存，如要设为默认，则需要将之前的默认收货地址改为非默认
     * @param userAllInfo
     * @param userAddress
     */
    private void checkAddressStatus(UserAllInfo userAllInfo, UserAddress userAddress) throws Exception{
        //先查询当前用户已经保存的所有未删除收货地址
        UserAddress queryUserAddress = new UserAddress();
        queryUserAddress.setUserId(userAllInfo.getId());
        queryUserAddress.setDelTag("1");//TODO删除状态为 1：未删除，0：已删除
        List<UserAddress> userAddressList = userAddressDao.getUserAddressList(queryUserAddress);
        if (userAddressList.size() >= 20) {
            throw new CustomException(UserExceptionEnum.USER_ADDRESS_MAX);
        }
        //目前要保存的地址不是默认则直接保存，如要设为默认，则需要将之前的默认收货地址改为非默认
        if (userAddress.getStatus() != null || AddressStatusEnum.Default.getCode() == userAddress.getStatus()) {
            //要将新地址设为默认
            for (UserAddress address : userAddressList) {
                if (AddressStatusEnum.Default.getCode().equals(address.getStatus())) {
                    //将查询到列表中的默认地址更新为非默认
                    address.setStatus(AddressStatusEnum.UnDefault.getCode());
                    address.setUpdateTime(new Date().getTime());
                    userAddressDao.updateUserAddress(address);
                }
            }
        }
    }
}
