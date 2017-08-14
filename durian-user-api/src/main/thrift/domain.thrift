namespace java com.durian.user.thrift.api.domain

struct UserAllInfoTo {
    1:optional string id;
    2:required string accountType;
    3:optional string accountLevel;
    4:optional string status;
    5:optional i64 createTime;
    6:optional string delTag;
    7:optional string mobile;
    8:optional string accessToken;
    9:optional string refreshToken;
    10:optional i32 expires;
    11:optional i64 loginTime;
}


####后台用户登录对象
struct LoginUserTo {
    1:optional string type;
    2:required string mobile;
    3:optional string password;
    4:optional string returnUrl;
    5:optional string ip;
    6:optional string name;
}

struct RegisterUserTo {
    1:optional string userId;
    2:optional string nickName;
    3:required string mobile;
    4:optional string password;
    5:optional string mobileCode;
    6:optional i64 inviterId;
    7:optional string deptCode;
    8:optional string deptId;
}

