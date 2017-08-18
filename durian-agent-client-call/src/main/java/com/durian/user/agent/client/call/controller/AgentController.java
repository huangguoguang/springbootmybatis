package com.durian.user.agent.client.call.controller;


import com.durian.user.agent.thrift.api.domain.ResultDataConfigStructTo;
import com.durian.user.agent.thrift.api.domain.ResultDataStructTo;
import com.durian.user.agent.thrift.api.domain.UserAgentConfigTo;
import com.durian.user.agent.thrift.api.domain.UserRelationTo;
import com.durian.user.agent.thrift.api.service.UserAgentServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * <p>
 * Created by wangyang on 2017/5/9.
 */
@RestController
@RequestMapping("/agent")
public class AgentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);


    @Autowired
    private UserAgentServiceApi.Iface userAgentServiceApi;

    @Value("${spring.application.name}")
    private String appName;


    /**
     * 用户下线总数
     *
     * @throws Exception
     */
    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public ResultDataStructTo getUserAgentCount(@PathVariable("uid") String uid) throws Exception {
        return userAgentServiceApi.userAgentCount(uid);
    }


    /**
     * 用户手续费分成
     *
     * @throws Exception
     */
    @RequestMapping(value = "brokerage/{uid}/{amount}", method = RequestMethod.PUT)
    public Boolean getAgentBrokerage(@PathVariable("uid") String uid,@PathVariable("amount") String amount) throws Exception {
        return userAgentServiceApi.userAgentBrokerage(uid,Double.valueOf(amount));
    }

    /**
     * 用户注册代理
     *
     * @throws Exception
     */
    @RequestMapping(value = "register/{uid}/{username}", method = RequestMethod.PUT)
    public Boolean registerUserAgent(@PathVariable("uid") String uid,@PathVariable("username") String username) throws Exception {
        return userAgentServiceApi.registerUserAgent(uid,username);
    }

    /**
     * 用户关系保存
     *
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Boolean inviteUser(UserRelationTo userRelationTo) throws Exception {
        return userAgentServiceApi.saveUserRelation(userRelationTo);
    }


    /**
     * 保存配置文件
     * @param userAgentConfigTo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/config", method = RequestMethod.POST)
    public Boolean saveUserAgentConfig(UserAgentConfigTo userAgentConfigTo) throws Exception {
        return  userAgentServiceApi.saveUserAgentConfig(userAgentConfigTo);
    }

    /**
     * 更新配置文件
     * @param userAgentConfigTo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/config", method = RequestMethod.PUT)
    public Boolean updateUserAgentConfig(UserAgentConfigTo userAgentConfigTo) throws Exception {
        return  userAgentServiceApi.updateUserAgentConfig(userAgentConfigTo);
    }

    /**
     * 获取配置文件
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public ResultDataConfigStructTo getUserAgentConfig(String id) throws Exception {
        return  userAgentServiceApi.getUserAgentConfig(id);
    }

}
