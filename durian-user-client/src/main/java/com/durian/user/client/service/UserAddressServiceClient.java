package com.durian.user.client.service;

import com.durian.user.domain.po.UserAddress;
import com.platform.common.domain.result.ResultDataStruct;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 用户收货地址服务API
 * Created by huangguang on 2017/7/7.
 */
@FeignClient("durian-user")
@Service
public interface UserAddressServiceClient {

    /**
     * 收货地址详细信息
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/address", method = RequestMethod.GET)
    UserAddress userAddress(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 收货地址列表
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/address/list", method = RequestMethod.GET)
    ResultDataStruct<List<UserAddress>> userAddressList(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 增加收货地址
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/address", method = RequestMethod.POST)
    String addUserAddress(@RequestParam Map<String, Object> paramMap) throws Exception;


    /**
     * 设置默认收货地址
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/address/default", method = RequestMethod.PUT)
    String defUserAddress(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 修改收货地址
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/address", method = RequestMethod.PUT)
    String updateUserAddress(@RequestParam Map<String, Object> paramMap) throws Exception;

    /**
     * 删除收货地址
     * @param paramMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/address", method = RequestMethod.DELETE)
    String deleteUserAddress(@RequestParam Map<String, Object> paramMap) throws Exception;
}
