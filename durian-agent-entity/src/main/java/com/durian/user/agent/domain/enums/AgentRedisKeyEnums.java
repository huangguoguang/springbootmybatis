package com.durian.user.agent.domain.enums;

/**
 * Created by xx on 2017/4/12.
 */
public enum AgentRedisKeyEnums {
    AGENT_INFO("agent:", "用户代理信息"),
    AGENT_ALLOT("allot:", "用户代理比例信息");
    private String code;
    private String desc;

    AgentRedisKeyEnums(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
