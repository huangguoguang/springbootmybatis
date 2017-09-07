package com.durian.user.dao;

import com.durian.user.domain.po.UserLogin;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.domain.to.FindPwd;
import com.durian.user.domain.to.RegisterUser;

import java.util.List;

/**
* 数据操作接口
*
* @author: lixuegang
* @version: 1.0
* @datetime: 2017-06-06 17:34:16
*
*/
public interface UserAccountDao {


    /**
     * 保存用户信息
     * @param registerUser
     * @return
     */
    UserAllInfo saveUser(RegisterUser registerUser)throws Exception ;


    /**
     * 通过手机号码查询
     * @param moblie
     * @return
     */
    UserAllInfo getUserInfoByMoblie(String moblie);


    /**
     *
     * @param id
     * @return
     */
    UserAllInfo getUserInfoById(String id);

    /**
     * 更新用户密码
     * @param findPwd
     */
    void updatePwd(FindPwd findPwd);

    /**
     * 保存用户登录信息.
     * @param userLogin
     * @return
     */
    int saveLogin(UserLogin userLogin);

  /**
   * 更新用户状态.
   * @param id
   * @param status
   * @return
   */
    int updateUserStatus(String id,Integer status) throws Exception ;

    /**
     * 设置或修改用户个人信息
     * @param userAllInfo
     * @return
     * @throws Exception
     */
     int updateUserAllInfo(UserAllInfo userAllInfo) throws Exception;

    /**
     * 查询所有已注册的用户
     * @param userAllInfo
     * @return
     * @throws Exception
     */
    List<UserAllInfo> selectUserInfoList(UserAllInfo userAllInfo) throws Exception;

    List<UserAllInfo> syntheticalUserAllInfoList() throws Exception;
}
