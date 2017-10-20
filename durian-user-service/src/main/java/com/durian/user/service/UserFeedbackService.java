package com.durian.user.service;

import com.durian.user.domain.po.UserFeedback;
import com.durian.user.domain.to.UserAllInfo;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.to.PageTo;

import java.util.List;

/**
 * 用户反馈意见接口
 * Created by wangli on 2017/10/19.
 */
public interface UserFeedbackService {
   /**
     * 获取提现订单列表
     * @param pageParam
     * @return
     * */
    PageInfo<UserFeedback> userFeedbackList(PageTo pageParam, UserFeedback userFeedback) throws Exception;

    /**
     * 添加意见反馈
     * @param userFeedback
     * @return
     * @throws Exception
     */
    String addUserFeedback(UserFeedback userFeedback) throws Exception ;

    /**
     * 更新意见反馈信息
     * @param userFeedback
     * @return
     * @throws Exception
     */
    String updateUserFeedback(UserFeedback userFeedback) throws Exception ;

    /**
     * 查询指定id的意见反馈信息
     * @param id
     * @return
     */
    UserFeedback getUserFeedbackById(String id) throws Exception;


    /**
     * 删除意见反馈
     * @param userFeedback
     * @return
     */
    String deleteUserFeedback(UserFeedback userFeedback) throws Exception;

}
