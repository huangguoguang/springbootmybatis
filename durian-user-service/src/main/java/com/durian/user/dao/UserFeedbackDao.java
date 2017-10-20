package com.durian.user.dao;

import com.durian.user.domain.po.UserFeedback;

import java.util.List;

/**
 * Created by wangli on 2017/10/19.
 */
public interface UserFeedbackDao {
    /**
     * 根据id查询feedback信息
     * @param id
     * @return
     */
    UserFeedback getUserFeedBackById(Integer id);

    /**
     * 新增userFeedback信息
     * @param userFeedback
     * @return
     */
    int saveUserFeedback(UserFeedback userFeedback);

    /**
     * 更新userFeedback信息
     * @param userFeedback
     * @return
     */
    int updateUserFeedback(UserFeedback userFeedback);

    /**
     * 删除userFeedback信息
     * @param id
     * @return
     */
    int deleteUserFeedback(Integer id);
    /**
     * 根据type查询意见反馈或者微信二维码
     * @param record
     * @return
     */
    List<UserFeedback> getUserFeedbackList (UserFeedback record);

    /**
     * 根据type查询微信二维码
     * @param type
     * @return
     */
    List<UserFeedback> queryCode (String type);
}
