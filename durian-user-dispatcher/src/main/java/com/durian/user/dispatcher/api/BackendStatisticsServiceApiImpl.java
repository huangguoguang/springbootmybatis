package com.durian.user.dispatcher.api;

import com.durian.common.domain.enums.BillingStatisticsEnums;
import com.durian.user.capital.statistics.service.BillingStatisticsService;
import com.durian.user.service.UserStatisticsService;
import com.durian.user.thrift.api.service.BackendStatisticsServiceApi;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lj on 2017/9/13.
 */
@EnableThriftService(path = "/api/backendStatistics")
@Service
public class BackendStatisticsServiceApiImpl implements BackendStatisticsServiceApi.Iface {
    @Resource
    private BillingStatisticsService billingStatisticsService;

    @Autowired
    private UserStatisticsService userStatisticsService;

    @Override
    public Map<String, String> getMainPageStatisticsInfo() throws TException {
        Map<String, String> billingStatistics = billingStatisticsService.getBillingStatisticsInfo();
        // TODO
//				Map<String, String>
        Map resultMap = new HashMap<String,String>();
        int todayBrokerage = userStatisticsService.getToDayRegisterCont(BillingStatisticsEnums.TODAY_USER_REGISTER.getCode()) ;
        int totalBrokerage = todayBrokerage + userStatisticsService.getToDayRegisterCont(BillingStatisticsEnums.PAST_USER_REGISTER.getCode());
        resultMap.put("todayBrokerage", todayBrokerage);
        resultMap.put("totalBrokerage", totalBrokerage);
        return resultMap;
    }

    @Override
    public List<String> getBrokerageChartInfo() throws TException {
        return billingStatisticsService.getBrokerageChartInfo();
    }

    @Override
    public List<String> getRegisterChartInfo() throws TException {
        return userStatisticsService.getRegisterChartInfo(BillingStatisticsEnums.DAILY_USER_REGISTER.getCode());
    }
}
