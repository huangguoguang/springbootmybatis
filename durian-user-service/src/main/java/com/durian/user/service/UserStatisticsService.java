package com.durian.user.service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface UserStatisticsService {

    /**
     * 查询注册人数
     * @return
     */
    List<String> getRegisterChartInfo(String key);

    /**
     * 今日注册量
     * @return
     */
    Integer getToDayRegisterCont(String key);

    /**
     * 总注册量
     * @return
     */
    Integer getSumRegisterCont(String key);
}
