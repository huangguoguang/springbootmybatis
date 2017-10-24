package com.durian.user.service.impl;

import java.util.Date;
import java.util.List;

import com.durian.user.dao.UserFeedbackDao;
import com.durian.user.domain.enums.FeedbackTypeEnum;
import com.durian.user.domain.po.UserFeedback;
import com.durian.user.service.UserFeedbackService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.common.domain.to.PageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangli on 2017/10/19.
 */
@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {

    @Autowired
    private UserFeedbackDao userFeedbackDao;


    @Override
    public PageInfo<UserFeedback> userFeedbackList(PageTo pageParam, UserFeedback userFeedback) throws Exception {
        Page<Object> page = PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        userFeedbackDao.getUserFeedbackList(userFeedback);
        PageInfo<UserFeedback> pageInfo = new PageInfo(page);
        return pageInfo;
    }

    @Override
    public String addUserFeedback(UserFeedback userFeedback) throws Exception {
        userFeedback.setCreateTime(new Date().getTime());
        int count = userFeedbackDao.saveUserFeedback(userFeedback) ;
        //如果是修改二维码配置  如果是将deltag设置为0 则其他的需要改为1
        if(userFeedback.getType().equals(FeedbackTypeEnum.CODE.getCode()) && userFeedback.getDelTag().equals("0")){
            UserFeedback newF=new UserFeedback();
            newF.setType(FeedbackTypeEnum.CODE.getCode());
            newF.setDelTag(FeedbackTypeEnum.NOUSE.getCode());
            newF.setId(userFeedback.getId());
            userFeedbackDao.updateCodeStatus(newF);
        }
        return count+"" ;
    }

    @Override
    public String updateUserFeedback(UserFeedback userFeedback) throws Exception {
        userFeedback.setUpdateTime(new Date().getTime());
        int count = userFeedbackDao.updateUserFeedback(userFeedback);
        //如果是修改二维码配置  如果是将deltag设置为0 则其他的需要改为1
        if(userFeedback.getType().equals(FeedbackTypeEnum.CODE.getCode()) && userFeedback.getDelTag().equals("0")){
            UserFeedback newF=new UserFeedback();
            newF.setType(FeedbackTypeEnum.CODE.getCode());
            newF.setDelTag(FeedbackTypeEnum.NOUSE.getCode());
            newF.setId(userFeedback.getId());
            userFeedbackDao.updateCodeStatus(newF);
        }
        return count+"" ;
    }

    @Override
    public UserFeedback getUserFeedbackById(Long id) throws Exception {
         return userFeedbackDao.getUserFeedBackById(id);
    }

    @Override
    public String deleteUserFeedback(UserFeedback userFeedback) throws Exception{
        userFeedback.setDelTag(FeedbackTypeEnum.NOUSE.getCode());
        userFeedback.setUpdateTime(new Date().getTime());
        int count = userFeedbackDao.updateUserFeedback(userFeedback);
        return count+"" ;
    }

    @Override
    public List<UserFeedback> getCodeList(String type)throws Exception {
        return userFeedbackDao.queryCode(type);
    }

    @Override
    public int updateCodeStatus(UserFeedback userFeedback) throws Exception {
        return userFeedbackDao.updateCodeStatus(userFeedback);
    }

}
