namespace java com.durian.user.thrift.api.service

include "domain.thrift"

service UserServiceApi {

    /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserAllInfoTo login(1: optional domain.LoginUserTo loginUserTo);

    /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserAllInfoTo refreshToken(1: required string refreshToken,2: required string type);



    void  registerUser(1: optional domain.RegisterUserTo registerUserTo);

    void registerMobileCode(1: optional domain.RegisterUserTo registerUserTo);


    void  findPwdMobileCode(1: optional domain.RegisterUserTo registerUserTo);

    void resetPwd(1: optional domain.RegisterUserTo registerUserTo);


  /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    domain.UserAllInfoTo userInfoByUserId(1: required string userId);


  /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
    void modifyPwd(1: required string userId,2: required string oldPwd,3: required string newPwd);

     /**
       * 获取用户列表
       *
       * @return
       * @throws Exception
       */
      void registerAgentUser(1: required string userId,2: required string nickName);

}

