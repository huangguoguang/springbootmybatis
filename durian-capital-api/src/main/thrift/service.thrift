namespace java com.durian.user.capital.thrift.api.service

include "domain.thrift"

service UserCapitalServiceApi {

   /**
    * 创建用户资金账户
    *
    * @param userId
    * */
   bool createUserCapital(1:required string userId);

   /**
    * 冻结用户资金账户
    *
    * @param userId
    * */
   bool freezeUserCapital(1:required string userId);

   /**
    * 禁用用户资金账户
    *
    * @param userId
    * */
   bool disableUserCapital(1:required string userId);

   /**
    * 取用户资金余额
    *
    * @param userId 用户ID
    * @return balance
    * */
   domain.UserCapitalTo getUserCapital(1:required string userId);

   /**
    * 用户余额变化
    *
    * @param userBilling
    * */
   bool changeUserBalance(1:required domain.UserBillingTo userBillingTo);

   /**
    * 用户余额增加
    *
    * @param userBillings
    * */
   bool addUserBalance(1:required list<domain.UserBillingTo> userBillingTos);

   /**
    * 同步用户余额
    *
    * @param userId
    * */
   bool syncUserCapital(1:required string userId);
}

