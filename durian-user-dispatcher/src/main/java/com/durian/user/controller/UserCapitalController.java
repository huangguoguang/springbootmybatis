package com.durian.user.controller;

import com.durian.user.capital.domain.enums.CapitalOperateEnums;
import com.durian.user.capital.domain.enums.CapitalUseTypeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.durian.user.capital.service.UserCapitalService;

import java.math.BigDecimal;

/**
 * Created by wangzhe on 2017/8/2.
 */

@RestController
@RequestMapping("/user")
public class UserCapitalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserCapitalService userCapitalService;

    /**
     * 获取用户金额信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getUserCapital", method = RequestMethod.POST)
    public String getUserCapitalInfo(@RequestParam("userId") String userId) throws Exception{
        BigDecimal amount =  userCapitalService.getUserCapitalAccount(userId);
         return amount.toString();
    }

    /**
     * 获取用户金额信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updUserCapital", method = RequestMethod.POST)
    public Boolean getUserCapitalInfo(@RequestParam("userId") String userId,@RequestParam("amount") BigDecimal amount,@RequestParam("operate") String operate) throws Exception{
        boolean b = false;
        if(operate.equals("sub")){
            b  =  userCapitalService.changeUserCapital(userId,amount,CapitalOperateEnums.SUBTRACT, CapitalUseTypeEnums.SHOPPING,"123",true);
        }
        if(operate.equals("add")){
            b=userCapitalService.changeUserCapital(userId,amount,CapitalOperateEnums.ADD, CapitalUseTypeEnums.REFUND,"123",true);
        }
        return b;
    }
}
