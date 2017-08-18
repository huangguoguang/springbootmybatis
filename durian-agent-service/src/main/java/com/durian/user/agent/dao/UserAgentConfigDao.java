package com.durian.user.agent.dao;

import com.durian.user.agent.domain.po.UserAgentConfig;


/**
 * Created by wangzhe on 2017/8/3.
 */
public interface UserAgentConfigDao {

    /**
     * 保存提成比例
     * @param userAgentConfig
     */
    Boolean saveUserAgentConfig(UserAgentConfig userAgentConfig) ;


    /**
     * 更新提成比例
     * @param userAgentConfig
     */
    Boolean updateUserAgentConfig(UserAgentConfig userAgentConfig) ;


    /**
     * 获取代理商比例
     * @param id
     * @return
     */
    UserAgentConfig getUserAgentConfig(String id);


}
