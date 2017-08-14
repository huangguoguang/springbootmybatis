package com.durian.user.agent.domain.to;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/15.
 */
public class UserLevelRelation implements Serializable{


    private String levelCount1;     //一级代理总数

    private String levelCount2;     //二级代理总数

    private String levelCount3;     //三级代理总数

    public String getLevelCount1() {
        return levelCount1;
    }

    public void setLevelCount1(String levelCount1) {
        this.levelCount1 = levelCount1;
    }

    public String getLevelCount2() {
        return levelCount2;
    }

    public void setLevelCount2(String levelCount2) {
        this.levelCount2 = levelCount2;
    }

    public String getLevelCount3() {
        return levelCount3;
    }

    public void setLevelCount3(String levelCount3) {
        this.levelCount3 = levelCount3;
    }



}
