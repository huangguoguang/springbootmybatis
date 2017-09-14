package com.durian.user.controller;

import com.durian.user.domain.enums.AddressStatusEnum;
import com.durian.user.domain.po.UserAddress;
import com.durian.user.service.UserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 收货地址
 * Created by huangguang on 2017/7/6.
 */
@RestController
@RequestMapping("/user")
public class UserAddressController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 收货地址详细信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "address", method = RequestMethod.GET)
    public Callable<UserAddress> userAddress(@RequestParam("addressId") String addressId,
                                             @RequestParam("userId") String userId) throws Exception {
        LOGGER.info("收货地址详细信息:"+ userId);
        return () -> userAddressService.getUserAddressById(userId, addressId);
    }

    /**
     * 收货地址列表
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "address/list", method = RequestMethod.GET)
    public Callable<List<UserAddress>> userAddressList(@RequestParam("userId") String userId) throws Exception {
        LOGGER.info("收货地址列表：" + userId);
        return () -> userAddressService.userAddressList(userId);
    }

    /**
     * 增加收货地址
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "address", method = RequestMethod.POST)
    public Callable<String> addUserAddress(@RequestParam("userId") String userId,
                                           @ModelAttribute UserAddress userAddress) throws Exception {
        LOGGER.info("用户登录:"+ userId);
        return () -> userAddressService.addUserAddress(userId,userAddress);
    }


    /**
     * 设置默认收货地址
     * @param userId
     * @param addressId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "address/default", method = RequestMethod.PUT)
    public Callable<String> defUserAddress(@RequestParam("addressId") String addressId,
                                           @RequestParam("userId") String userId) throws Exception {
        LOGGER.info("用户登录:"+ userId);
        UserAddress userAddress = userAddressService.getUserAddressById(userId, addressId);
        userAddress.setStatus(AddressStatusEnum.Default.getCode());
        return () -> userAddressService.updateUserAddress(userId, userAddress);
    }

    /**
     * 修改收货地址
     * @param userId
     * @param userAddress
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "address", method = RequestMethod.PUT)
    public Callable<String> updateUserAddress(@RequestParam("userId") String userId,
                                              @ModelAttribute UserAddress userAddress) throws Exception {
        LOGGER.info("修改收货地址：" + userId);
        return () -> userAddressService.updateUserAddress(userId, userAddress);
    }

    /**
     * 删除收货地址
     * @param userId
     * @param addressId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "address", method = RequestMethod.DELETE)
    public Callable<String> deleteUserAddress(@RequestParam("addressId") String addressId,
                                              @RequestParam("userId") String userId) throws Exception {
        LOGGER.info("删除收货地址：" + userId);
        UserAddress userAddress = userAddressService.getUserAddressById(userId, addressId);
        userAddress.setDelTag("0");
        return () -> userAddressService.deleteUserAddress(userId, userAddress);
    }

}
