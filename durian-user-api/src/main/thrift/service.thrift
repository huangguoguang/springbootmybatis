namespace java com.durian.user.thrift.api.service

include "domain.thrift"

service UserServiceApi {

    /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserTokenInfoTo login(1: optional domain.LoginUserTo loginUserTo) throws (1: domain.UserThriftException ex);

    /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserTokenInfoTo refreshToken(1: required string refreshToken,2: required string type) throws (1: domain.UserThriftException ex);



    void  registerUser(1: optional domain.RegisterUserTo registerUserTo) throws (1: domain.UserThriftException ex);

    void registerMobileCode(1: optional domain.RegisterUserTo registerUserTo) throws (1: domain.UserThriftException ex);


    void  findPwdMobileCode(1: optional domain.RegisterUserTo registerUserTo) throws (1: domain.UserThriftException ex);

    void resetPwd(1: optional domain.RegisterUserTo registerUserTo) throws (1: domain.UserThriftException ex);


  /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserAllInfoTo userInfoByUserId(1: required string userId) throws (1: domain.UserThriftException ex);


  /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    void modifyPwd(1: required string userId,2: required string oldPwd,3: required string newPwd) throws (1: domain.UserThriftException ex);

     /**
       * 获取用户列表
       *
       * @return
       * @throws Exception
       */
      void registerAgentUser(1: required string userId,2: required string nickName) throws (1: domain.UserThriftException ex);



  /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserAllInfoTo userInfoByAccessToken(1: required string accessToken) throws (1: domain.UserThriftException ex);

    domain.ResultUserInfoPageStructTo getAllUserInfo( 1: required domain.PageInfoTo pageInfoTo, 2: string id,3:i32 status ,4:string mobile);


    /**
    * 查询制定天数注册量
    **/
    i32 statisticsUserRegisterCount( 1: required string startDate) throws (1: domain.UserThriftException ex);


    /**
    * 查询注册量
    **/
    i32 statisticsTodayUserRegister() throws (1: domain.UserThriftException ex);


/**
* 退出登录
**/
    void  loginout(1: optional string userId) throws (1: domain.UserThriftException ex);
//查询意见反馈二维码列表
  domain.ResultFeedbackPageStructTo getUserFeedbackList( 1: required domain.PageInfoTo pageInfoTo,2: optional domain.UserFeedbackTo userFeedbackTo);
//查询单个意见反馈二维码详细信息
   domain.UserFeedbackTo userFeedbackInfo(1: required string id) throws (1: domain.UserThriftException ex);
//新增意见反馈二维码信息
   void  insertUserFeedback(1: optional domain.UserFeedbackTo userFeedback) throws (1: domain.UserThriftException ex);
//更新意见反馈二维码信息
   void  updateUserFeedback(1: optional domain.UserFeedbackTo userFeedback) throws (1: domain.UserThriftException ex);
//查询二维码列表
   list<domain.UserFeedbackTo> getCodeList()throws (1: domain.UserThriftException ex);

}



service BackendUserServiceApi {

    /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserAllInfoTo login(1: optional domain.LoginUserTo loginUserTo) throws (1: domain.UserThriftException ex);

    domain.ResultBackendUserInfoPageStructTo getUserList( 1: required domain.PageInfoTo pageInfoTo);


    /**
    * 启用用户
    **/
    string enableUser( 1: required string userId) throws (1: domain.UserThriftException ex);

        /**
        * 禁用用户
        **/
     string disableUser( 1: required string userId) throws (1: domain.UserThriftException ex);

}

service BackendStatisticsServiceApi {

    map<string, string> getMainPageStatisticsInfo();

    list<string> getBrokerageChartInfo();

    list<string> getRegisterChartInfo();
}


