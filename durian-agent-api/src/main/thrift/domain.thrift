namespace  java com.durian.user.agent.thrift.api.domain

struct UserRelationTo{
        1:optional i64 id;
        2:optional string deptCode;
        3:optional string deptId;
        4:optional string agentName;
        5:optional string inviterId;
        6:optional string inviteeId;
        7:optional string status;
        8:optional string delTag;
        9:optional string createTime;
       10:optional string updateTime;

}

struct UserLevelRelationTo{
        1:optional string levelCount1;
        2:optional string levelCount2;
        3:optional string levelCount3;
}



struct ResultDataStructTo {
    1: required i32 code;
    2: optional string message;
    3: optional UserLevelRelationTo userLevelRelationTo;
}


struct UserAgentConfigTo{
        1:optional i64 id;
        2:optional string levelAllot1;
        3:optional string levelAllot2;
        4:optional string levelAllot3;
        8:optional string delTag;
        9:optional string createTime;
       10:optional string updateTime;
}

struct ResultDataConfigStructTo {
        1: required i32 code;
        2: optional string message;
        3: optional UserAgentConfigTo userAgentConfigTo;
 }

