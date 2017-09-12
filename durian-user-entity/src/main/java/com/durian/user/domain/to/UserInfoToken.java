package com.durian.user.domain.to;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/12.
 */
public class UserInfoToken  implements Serializable{
    private TokenInfo tokenInfo ;
    private  UserAllInfo userAllInfo ;


    public TokenInfo getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public UserAllInfo getUserAllInfo() {
        return userAllInfo;
    }

    public void setUserAllInfo(UserAllInfo userAllInfo) {
        this.userAllInfo = userAllInfo;
    }
}
