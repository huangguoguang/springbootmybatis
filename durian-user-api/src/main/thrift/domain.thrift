namespace java com.durian.user.thrift.api.domain

exception ThriftException {
  1: i32 code,
  2: string msg
}
// 公共返回消息
struct ResultMessageStructTo {
    1: optional i32 code;
    2: optional string data;
    3: optional string message;
}

struct UserInfoTo {
    1:optional string id;
    2:required string accountType;
    3:optional string accountLevel;
    4:optional string status;
    5:optional i64 createTime;
    6:optional string delTag;
    7:optional string mobile;
    8:optional i64 loginTime;
}


struct TokenInfoTo {
    1:optional string accessToken;
    2:optional string refreshToken;
    3:optional i32 expires;
}

struct UserTokenInfoTo {
    1: required UserInfoTo userInfoTo;
    2: optional TokenInfoTo tokenInfoTo;
}

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
    2:required string mobile;
    3:optional string password;
    4:optional string mobileCode;
    5:optional string inviterId;
    6:optional string deptCode;
    7:optional string deptId;
}

struct PageInfoTo {
    1: i32 pageNum;
    2: i32 pageSize;
    3: i32 size;
    4: i32 startRow;
    5: i32 endRow;
    6: i64 total;
    7: i32 pages;
    8: i32 prePage;
    9: i32 nextPage;
    10: bool isFirstPage;
    11: bool isLastPage;
    12: string condition;
}

struct SyntheticalUserAllInfoTo{

    1: string id;
    2: string accountType;
    3: i32 accountLevel;
    4: i32 status;
    5: i64 createTime;
    6: string delTag;
    7: string nickIcon;
    8: string nickName;
    9: i32 gender;
    10: string province;
    11: string city;
    12: string area;
    13: string street;
    14: string address;
    15: string mobile;
    16: string name;
    17: string idCard;
    18: string email;
    19: string password;
    20: string birthday;
    21: string education;
}

struct ResultUserInfoPageStructTo {
    1: required i32 code;
    2: optional string message;
    3: optional PageInfoTo pageInfoTo;
    4: optional list<SyntheticalUserAllInfoTo> syntheticalUserAllInfoToList;
}

