package com.durian.user.dispatcher.api;

import com.durian.common.domain.enums.BillingStatisticsEnums;
import com.durian.user.capital.statistics.service.BillingStatisticsService;
import com.durian.user.thrift.api.service.BackendStatisticsServiceApi;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public Map<String, String> getMainPageStatisticsInfo() throws TException {
        Map<String, String> billingStatistics = billingStatisticsService.getBillingStatisticsInfo();
        // TODO
//				Map<String, String>
        return null;
    }

    @Override
    public List<String> getBrokerageChartInfo() throws TException {
        return billingStatisticsService.getBrokerageChartInfo();
    }

    @Override
    public List<String> getRegisterChartInfo() throws TException {
        return null;
    }
}
