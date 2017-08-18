namespace  java com.durian.user.agent.thrift.api.service

include "domain.thrift"

service UserAgentServiceApi {

    /**
    * 用户代理统计
    *
    * @param userId
    **/
   domain.ResultDataStructTo userAgentCount(1:required string userId);

   /**
    * 注册代理用户
    *
    * @param userId
    * @param userId
    **/
   bool registerUserAgent(1:required string userId,2:required string agentName);


   /**
    * 用户手续费分成
    *
    * @param userId
    **/
   bool userAgentBrokerage(1:required string userId,2:required double amount);

   /**
    * 新增关系
    *
    * @param userRelation
    *
    */
   bool saveUserRelation(1: required domain.UserRelationTo userRelation);


    /**
     * 新增代理比例信息
     *
     * @param UserAgentConfig
     *
     */
   bool saveUserAgentConfig(1:required domain.UserAgentConfigTo UserAgentConfig);


    /**
     * 修改代理比例信息
     *
     * @param UserAgentConfig
     *
     */
   bool updateUserAgentConfig(1:required domain.UserAgentConfigTo UserAgentConfig);

    /**
     * 获取代理比例信息
     *
     * @param UserAgentConfig
     *
     */
   domain.ResultDataConfigStructTo getUserAgentConfig(1:required string id);




}
