package com.durian.user.domain.to;

import java.io.Serializable;

/**
 * Created by daixibiao on 2017/7/7.
 */
public class UserInfoDetail implements Serializable{

    //------------用户基本信息---------------
    //用户ID
    private String userId;

    //姓名
    private String  name;

    //性别
    private Integer gender;

    //手机号
    private String mobile;

    //生日
    private String birthday;

    //地域
    private String area;

    //备注
    private String remark;

    //----------------订单信息------------
    //下单总数
    private Integer totalOrder;

    //最后一次下单时间
    private Long lastOrderTime;

    //默认收获地址
    private String address;

    //--------------账号绑定信息------------
    //微信账号
    private String weixinAccount;

    //qq账号
    private String qqAccount;

    //微博账号
    private String weiboAccont;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Long getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(Long lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeixinAccount() {
        return weixinAccount;
    }

    public void setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
    }

    public String getQqAccount() {
        return qqAccount;
    }

    public void setQqAccount(String qqAccount) {
        this.qqAccount = qqAccount;
    }

    public String getWeiboAccont() {
        return weiboAccont;
    }

    public void setWeiboAccont(String weiboAccont) {
        this.weiboAccont = weiboAccont;
    }
}
