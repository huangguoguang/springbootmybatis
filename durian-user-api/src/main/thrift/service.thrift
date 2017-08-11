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

}

