package com.durian.user.capital.listener;

import com.durian.user.capital.dao.UserCapitalDao;
import com.durian.user.capital.event.UserCapitalSyncEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lj on 2017/8/10.
 */
@Service
public class UserCapitalListener {
		private final static Logger LOGGER = LoggerFactory.getLogger(UserCapitalListener.class);

		@Resource
		private UserCapitalDao userCapitalDao;

		@Async("userCapitalTaskExecutor")
		@EventListener
		public void userCapitalSyncHandler(UserCapitalSyncEvent event){
				userCapitalDao.syncUserCapital((String) event.getSource());
		}


}
