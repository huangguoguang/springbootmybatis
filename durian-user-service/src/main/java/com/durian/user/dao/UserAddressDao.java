package com.durian.user.dao;

import com.durian.user.domain.po.UserAddress;

import java.util.List;

/**
 * 用户收货地址操作接口
 * Created by huangguang on 2017/7/6.
 */
public interface UserAddressDao {
    /**
     * 查询指定userId的所有收货地址
     * @param userAddress
     * @return
     * @throws Exception
     */
    List<UserAddress> getUserAddressList(UserAddress userAddress) throws Exception;

    /**
     * 保存收货地址
     * @param userAddress
     * @return
     * @throws Exception
     */
    int saveUserAddress(UserAddress userAddress) throws Exception ;

    /**
     * 更新收货地址
     * @param userAddress
     * @return
     * @throws Exception
     */
    int updateUserAddress(UserAddress userAddress) throws Exception;


    /**
     * 查询指定id的收货地址
     * @param id
     * @return
     */
    UserAddress getUserAddressById(Integer id);


}
