package com.durian.user.agent.domain.po;

/**
 * Created by wangzhe on 2017/8/3.
 */
public class UserAgentConfig {

    private String id;         //ID

    private String deptCode;    //部门code

    private String levelAllot1;     //一级分佣比例

    private String levelAllot2;     //二级分佣比例

    private String levelAllot3;     //三级分佣比例

    private String delTag;    //删除标识

    private Long createTime;  //创建时间

    private Long updateTime;  //更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getLevelAllot1() {
        return levelAllot1;
    }

    public void setLevelAllot1(String levelAllot1) {
        this.levelAllot1 = levelAllot1;
    }

    public String getLevelAllot2() {
        return levelAllot2;
    }

    public void setLevelAllot2(String levelAllot2) {
        this.levelAllot2 = levelAllot2;
    }

    public String getLevelAllot3() {
        return levelAllot3;
    }

    public void setLevelAllot3(String levelAllot3) {
        this.levelAllot3 = levelAllot3;
    }

    public String getDelTag() {
        return delTag;
    }

    public void setDelTag(String delTag) {
        this.delTag = delTag;
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
