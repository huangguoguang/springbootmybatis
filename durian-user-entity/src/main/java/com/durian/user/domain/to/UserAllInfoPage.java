package com.durian.user.domain.to;

import com.platform.common.domain.to.PageTo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public class UserAllInfoPage implements Serializable{

    private int code;

    private String message;

    private PageTo pageTo;

    private List<UserAllInfo> userAllInfoLList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageTo getPageTo() {
        return pageTo;
    }

    public void setPageTo(PageTo pageTo) {
        this.pageTo = pageTo;
    }

    public List<UserAllInfo> getUserAllInfoLList() {
        return userAllInfoLList;
    }

    public void setUserAllInfoLList(List<UserAllInfo> userAllInfoLList) {
        this.userAllInfoLList = userAllInfoLList;
    }
}
