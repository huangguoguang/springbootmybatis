package com.durian.user.utils.token;

/**
 *
 */
public class Token {
    private String owmuser; // 颁发者,issuer的缩写
    private Integer expires; // 生命周期,expires的缩写
    private String mobile; //用户中心id
    private String userId; //用户中心id
    private long createDate ;
    private String type ; //pc /Android/iPhone/iPad

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}


    public String getOwmuser() {
        return owmuser;
    }

    public void setOwmuser(String owmuser) {
        this.owmuser = owmuser;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    
    
}
