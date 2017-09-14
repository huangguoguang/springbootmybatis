package com.durian.user.domain.to;

import java.io.Serializable;

/**
 * 用户实体
 * <p>
 * Created by wangyang on 2017/5/11.
 */
public class LoginUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//登录类型  pc,app,weix
    private String type;

    //手机号码
    private String mobile;

    //密码
    private String password;

    //返回地址
    private String returnUrl ;

    //ip
	private String ip ;
	
	//后台名字
	private String name ;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
