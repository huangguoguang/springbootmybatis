package com.durian.user.domain.to;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/12.
 */
public class TokenInfo implements Serializable{

    private  String  accessToken;
    private  String refreshToken ;
    private  Integer expires ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }
}
