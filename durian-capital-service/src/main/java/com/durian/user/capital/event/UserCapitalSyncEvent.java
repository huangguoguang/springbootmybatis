package com.durian.user.capital.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by lj on 2017/8/10.
 */
public class UserCapitalSyncEvent extends ApplicationEvent {

		public UserCapitalSyncEvent(String userId){
				super(userId);
		}

}
