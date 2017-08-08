package com.durian.user.service;

import com.durian.user.domain.po.UserAddress;

import java.util.List;

/**
 * 用户收货地址管理接口
 * Created by huangguang on 2017/7/6.
 */
public interface UserAddressService {

    /**
     * 收货地址列表
     * @param userId
     * @return
     */
    List<UserAddress> userAddressList(String userId) throws Exception ;

    /**
     * 添加收货地址
     * @param userId
     * @param userAddress
     * @return
     * @throws Exception
     */
    String addUserAddress(String userId, UserAddress userAddress) throws Exception ;

    /**
     * 更新收货地址(包括更改地址内容，更新默认状态)
     * @param userId
     * @param userAddress
     * @return
     * @throws Exception
     */
    String updateUserAddress(String userId, UserAddress userAddress) throws Exception ;

    /**
     * 查询指定id的收货地址
     * @param userId
     * @param id
     * @return
     */
    UserAddress getUserAddressById(String userId, String id) throws Exception;


    /**
     * 删除收货地址
     * @param userId
     * @param userAddress
     * @return
     */
    String deleteUserAddress(String userId, UserAddress userAddress) throws Exception;
}
