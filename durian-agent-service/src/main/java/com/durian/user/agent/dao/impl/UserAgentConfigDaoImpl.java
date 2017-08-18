package com.durian.user.agent.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.durian.user.agent.dao.UserAgentConfigDao;
import com.durian.user.agent.domain.enums.AgentRedisKeyEnums;
import com.durian.user.agent.domain.po.UserAgentConfig;
import com.durian.user.agent.mapper.UserAgentConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * Created by wangzhe on 2017/8/3.
 */
@Service("userAgentConfigDao")
public class UserAgentConfigDaoImpl implements UserAgentConfigDao {

    @Autowired
    private UserAgentConfigMapper userAgentConfigMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Boolean saveUserAgentConfig(UserAgentConfig userAgentConfig) {

        userAgentConfig.setId(UUID.randomUUID().toString());

        //todo 部门后续处理
        userAgentConfig.setDeptCode("9999");

        userAgentConfig.setCreateTime(new Date().getTime());
        int i =  userAgentConfigMapper.insert(userAgentConfig);
        if(i ==1){
            redisTemplate.opsForValue().set(AgentRedisKeyEnums.AGENT_ALLOT.getCode()+userAgentConfig.getId().toString(), JSONObject.toJSONString(userAgentConfig).toString());

            return  true;
        }else {
            return false;
        }
        //return userAgentConfigMapper.insert(userAgentConfig);
    }

    @Override
    public Boolean updateUserAgentConfig(UserAgentConfig userAgentConfig) {
        userAgentConfig.setUpdateTime(new Date().getTime());
        int i = userAgentConfigMapper.updateByPrimaryKey(userAgentConfig);
        if(i==1){
            if(redisTemplate.opsForValue().get(AgentRedisKeyEnums.AGENT_ALLOT.getCode()+userAgentConfig.getId().toString())!=null) {
                redisTemplate.opsForValue().set(AgentRedisKeyEnums.AGENT_ALLOT.getCode()+userAgentConfig.getId().toString(), JSONObject.toJSONString(userAgentConfig).toString());
            }
            return  true;
        }else {
            return false;
        }
        //return userAgentConfigMapper.updateRelationInfo(userAgentConfig);
    }

    @Override
    public UserAgentConfig getUserAgentConfig(String id) {

        UserAgentConfig userAgentConfig = new UserAgentConfig();
        if(redisTemplate.opsForValue().get(AgentRedisKeyEnums.AGENT_ALLOT.getCode()+id)!=null){

            String jasonObject = redisTemplate.opsForValue().get(AgentRedisKeyEnums.AGENT_ALLOT.getCode()+id);
            Map<String,Object> map = JSONObject.parseObject(jasonObject);
            if(map.size()>0)
            {
                userAgentConfig.setLevelAllot1(map.get("levelAllot1").toString());
                userAgentConfig.setLevelAllot2(map.get("levelAllot2").toString());
                userAgentConfig.setLevelAllot3(map.get("levelAllot3").toString());
            }
        }else{
            userAgentConfig = userAgentConfigMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(AgentRedisKeyEnums.AGENT_ALLOT.getCode()+id, JSONObject.toJSONString(userAgentConfig).toString());
        }
        return userAgentConfig;
        //return userAgentConfigMapper.selectByPrimaryKey(id);
    }
}
