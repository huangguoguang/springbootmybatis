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

    domain.ResultUserInfoPageStructTo getAllUserInfo( 1: required domain.PageInfoTo pageInfoTo);
}

