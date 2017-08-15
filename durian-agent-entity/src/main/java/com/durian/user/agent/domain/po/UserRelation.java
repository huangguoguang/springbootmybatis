package com.durian.user.agent.domain.po;

/**
 * Created by wangzhe on 2017/8/3.
 */
public class UserRelation {

    private Integer id;

    private String deptCode;

    private String deptId;

    private String agentName;  //代理商名称

    private String inviterId; //邀请人ID

    private String inviteeId; //被邀请人ID

    private String status;   //代理标识状态

    private String delTag;    //删除标识

    private Long createTime;

    private Long updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDeptCode(String deptCode){ this.deptCode=deptCode;}

    public void setDeptId(String deptId){ this.deptId=deptId;}

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public void setInviteeId(String inviteeId) {
        this.inviteeId = inviteeId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDelTag(String delTag) {
        this.delTag = delTag;
    }

    public Integer getId() {
        return id;
    }

    public String getDeptCode(){ return deptCode;}

    public String getDeptId(){ return deptId;}

    public String getInviterId() {
        return inviterId;
    }

    public String getInviteeId() {
        return inviteeId;
    }

    public String getStatus() {
        return status;
    }

    public String getDelTag() {
        return delTag;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


}
