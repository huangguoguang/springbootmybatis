package com.durian.user.domain.to;

import java.io.Serializable;


/**
 * token解析出来的用户信息
 * @author hufeng
 *
 */
public class LoginAuthorInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId ;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}
