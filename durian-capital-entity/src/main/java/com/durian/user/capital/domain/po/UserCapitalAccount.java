package com.durian.user.capital.domain.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户资金账号
 *
 * Created by lj on 2017/4/11.
 */
public class UserCapitalAccount {
    private String id; // 资金帐号ID
    private String userId; // 用户ID
    private String mobile; // 手机号
    private BigDecimal balance; // 账户余额
    private String isUse; // 状态
    private Date createDate; // 创建时间
    private Date modifyDate; // 修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean getIsUse() {
        return "1".equals(isUse);
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "UserCapitalAccount{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", balance=" + balance +
                ", isUse='" + isUse + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
