package com.durian.user.service.impl;

import com.durian.user.service.UserStatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<String> getRegisterChartInfo(String key) {
        return redisTemplate.opsForList().range(key, 0L, 30L);
    }

    @Override
    public Integer getToDayRegisterCont(String key) {
        return Integer.valueOf(StringUtils.defaultIfBlank(redisTemplate.opsForValue().get(key),"0"));
    }

    @Override
    public Integer getSumRegisterCont(String key) {
        return Integer.valueOf(StringUtils.defaultIfBlank(redisTemplate.opsForValue().get(key),"0"));
    }
}
