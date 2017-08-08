package com.durian.user.domain.enums;

public enum UserStatusEnum {
	
	Enable(1, "启用"),
	Disable(2,"禁用"),
	LoginPasswordLimited(3,"登录密码受限制"),
	;
	
	
	private Integer code;
	private String statusDesc;
	    
	UserStatusEnum(Integer code, String statusDesc){
        this.code = code;
        this.statusDesc = statusDesc;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
}
