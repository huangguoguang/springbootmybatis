package com.durian.user.capital.dispatcher.api;

import com.durian.user.capital.domain.po.UserBilling;
import com.durian.user.capital.service.UserCapitalService;
import com.durian.user.capital.thrift.api.domain.UserBillingTo;
import com.durian.user.capital.thrift.api.service.UserCapitalServiceApi;
import com.platform.common.thrift.service.annotation.EnableThriftService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lj on 2017/8/10.
 */
@EnableThriftService(path = "/api/capital")
@Service
public class UserCapitalServiceApiImpl implements UserCapitalServiceApi.Iface {
		private final static Logger LOGGER = LoggerFactory.getLogger(UserCapitalServiceApiImpl.class);

		@Resource
		private UserCapitalService userCapitalService;


		@Override
		public boolean createUserCapital(String userId) throws TException {
				try{
					userCapitalService.createUserCapital(userId);
				} catch (Exception e){
						throw new TException(e.getMessage());
				}
				return true;
		}

		@Override
		public boolean freezeUserCapital(String userId) throws TException {
				try{
						userCapitalService.freezeUserCapital(userId);
				} catch (Exception e){
						throw new TException(e.getMessage());
				}
				return true;
		}

		@Override
		public boolean disableUserCapital(String userId) throws TException {
				try{
						userCapitalService.disableUserCapital(userId);
				} catch (Exception e){
						throw new TException(e.getMessage());
				}
				return true;
		}

		@Override
		public String getUserBalance(String userId) throws TException {
				try{
					return userCapitalService.getUserBalance(userId).toString();
				} catch (Exception e){
						throw new TException(e.getMessage());
				}
		}

		@Override
		public boolean changeUserBalance(UserBillingTo userBillingTo) throws TException {
				try{
						UserBilling userBilling = new UserBilling();
						BeanUtils.copyProperties(userBillingTo, userBilling);

						userCapitalService.changeUserBalance(userBilling);
				} catch (Exception e){
						throw new TException(e.getMessage());
				}
				return true;
		}

		@Override
		public boolean addUserBalance(List<UserBillingTo> userBillingTos) throws TException {
				try{
						List<UserBilling> userBillingList = new ArrayList<UserBilling>();
						BeanUtils.copyProperties(userBillingTos, userBillingList);

						userCapitalService.addUserBalance(userBillingList);
				} catch (Exception e){
						throw new TException(e.getMessage());
				}
				return true;
		}

		@Override
		public boolean syncUserCapital(String userId) throws TException {
				try{
						userCapitalService.syncUserCapital(userId);
				} catch (Exception e){
						throw new TException(e.getMessage());
				}
				return true;
		}
}
