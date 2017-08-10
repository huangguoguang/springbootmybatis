namespace java com.durian.user.capital.thrift.api.domain

struct UserCapitalTo {
    1:optional string id;
    2:required string userId;
    3:optional string amount;
    4:optional string status;
}

struct UserBillingTo {
    1:optional string id;
    2:required string userId;
    3:optional string amount;
    4:optional string balance;
    5:optional string operate;
    6:optional string purpose;
    7:optional string correlation;
    8:optional string description;
    9:optional string delTag;
    10:optional i64 createTime;
}