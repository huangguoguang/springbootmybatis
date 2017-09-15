-- -----------------------------------------------------
-- model  `user`   table `t_user_info`
-- -----------------------------------------------------
CREATE TABLE `t_user_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `nick_icon` varchar(255) DEFAULT NULL COMMENT '图像',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `gender` int(1) DEFAULT NULL COMMENT '性别',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区域',
  `street` varchar(255) DEFAULT NULL COMMENT '街道',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `del_tag` char(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- model  `user`   table `t_user_business`
-- -----------------------------------------------------

CREATE TABLE `t_user_business` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '名字',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码(MD5)',
  `birthday` date DEFAULT NULL,
  `education` varchar(100) DEFAULT NULL COMMENT '教育程度',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `del_tag` char(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;





-- -----------------------------------------------------
-- model  `user`   table `t_user_account`
-- -----------------------------------------------------
CREATE TABLE `t_user_account` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `account_type` varchar(10) DEFAULT NULL COMMENT '账号类型',
  `account_level` int(6) DEFAULT NULL COMMENT '等级',
  `consum_amount` decimal(10,2) DEFAULT NULL COMMENT '消费金额',
  `risk_level` int(6) DEFAULT NULL COMMENT '风险等级',
  `status` int(2) DEFAULT NULL COMMENT '用户状态(1可用,2,禁用,3注销)',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `del_tag` char(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





-- -----------------------------------------------------
-- model  `user`   table `t_user_login`
-- -----------------------------------------------------
CREATE TABLE `t_user_login` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `type` varchar(10) DEFAULT NULL COMMENT '登录类型',
  `status` int(2) DEFAULT NULL COMMENT '登录状态',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `del_tag` char(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=966 DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- model  `user`   table `t_backend_user`
-- -----------------------------------------------------
CREATE TABLE `t_backend_user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `nick_icon` varchar(255) DEFAULT NULL COMMENT '图像',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `gender` int(1) DEFAULT NULL COMMENT '性别',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区域',
  `street` varchar(255) DEFAULT NULL COMMENT '街道',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `del_tag` char(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户';




-- -----------------------------------------------------
-- model  `user`   table `t_backend_user_login`
-- -----------------------------------------------------

CREATE TABLE `t_backend_user_login` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `type` varchar(10) DEFAULT NULL COMMENT '登录类型',
  `status` int(2) DEFAULT NULL COMMENT '登录状态',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `del_tag` char(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=533 DEFAULT CHARSET=utf8 COMMENT='后台用户登录信息';






-- -----------------------------------------------------
-- model  `capital`   table `t_user_capital`
-- -----------------------------------------------------
CREATE TABLE `t_user_capital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资金ID',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `status` varchar(10) DEFAULT NULL COMMENT '账户状态',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;




-- -----------------------------------------------------
-- model  `capital`   table `t_user_capital_billing`
-- -----------------------------------------------------
CREATE TABLE `t_user_capital_billing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `operate` varchar(10) DEFAULT NULL COMMENT '加/减',
  `purpose` varchar(10) DEFAULT NULL COMMENT '目的',
  `correlation` varchar(50) DEFAULT NULL COMMENT '关联',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `del_tag` char(1) DEFAULT '0',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;





-- ----------------------------
-- Table structure for t_user_agent
-- DROP TABLE IF EXISTS `t_user_agent`;
-- ----------------------------
CREATE TABLE `t_user_agent` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(10) DEFAULT NULL,
  `dept_id` varchar(10) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `invite_code` varchar(10) DEFAULT NULL,
  `del_tag` char(1) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=562 DEFAULT CHARSET=utf8mb4;



-- -----------------------------------------------------
-- model  `agent`   table `t_user_agent_allot`
-- -----------------------------------------------------
CREATE TABLE `t_user_agent_allot` (
  `id` varchar(50) NOT NULL,
  `dept_code` varchar(20) DEFAULT NULL  COMMENT '部门CODE',
  `level_allot_1` double(10,3) DEFAULT NULL  COMMENT '比例1',
  `level_allot_2` double(10,3) DEFAULT NULL  COMMENT '比例2',
  `level_allot_3` double(10,3) DEFAULT NULL  COMMENT '比例3',
  `del_tag` char(1) DEFAULT NULL  COMMENT '删除标识',
  `create_time` bigint(20) DEFAULT NULL  COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




-- -----------------------------------------------------
-- model  `agent`   table `t_user_agent_relation`
-- -----------------------------------------------------
CREATE TABLE `t_user_agent_relation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(10) DEFAULT NULL  COMMENT '部门CODE',
  `dept_id` varchar(10) DEFAULT NULL  COMMENT '部门ID',
  `agent_name` varchar(20) DEFAULT NULL  COMMENT '代理名字',
  `inviter_id` varchar(20) DEFAULT NULL  COMMENT '邀请人ID',
  `invitee_id` varchar(20) DEFAULT NULL  COMMENT '被邀请人ID',
  `status` char(1) DEFAULT '0'  COMMENT '状态',
  `del_tag` char(1) DEFAULT NULL  COMMENT '删除标识',
  `create_time` bigint(20) DEFAULT NULL  COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;



-- ----------------------------
-- Table structure for t_user_dept_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_dept_info`;
CREATE TABLE `t_user_dept_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(100) DEFAULT NULL  COMMENT '部门名称',
  `dept_code` varchar(100) DEFAULT NULL  COMMENT '部门CODE',
  `dept_type` char(2) DEFAULT NULL  COMMENT '部门类型',
  `dept_parent_id` varchar(20) DEFAULT NULL  COMMENT '部门父ID',
  `del_tag` char(1) DEFAULT NULL  COMMENT '删除标识',
  `status` char(1) DEFAULT NULL  COMMENT '状态',
  `create_time` bigint(20) DEFAULT NULL  COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for t_user_agent_allot_ex
-- ----------------------------
DROP TABLE IF EXISTS `t_user_agent_allot_ex`;
CREATE TABLE `t_user_agent_allot_ex` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `allot` double(10,3) DEFAULT NULL COMMENT '比例',
  `rate` int(5) DEFAULT NULL COMMENT '等级',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `del_tag` char(1) DEFAULT NULL COMMENT '删除标识',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;



-- ----------------------------
--查父集合
-- ----------------------------
DROP FUNCTION IF EXISTS getParentList;
CREATE FUNCTION `getParentList`(rootId varchar(100))
RETURNS varchar(1000)
BEGIN
DECLARE fid varchar(100) default '';
DECLARE str varchar(1000) default rootId;

WHILE rootId is not null  do
    SET fid =(SELECT inviter_id FROM t_user_agent_relation WHERE invitee_id = rootId);
    IF fid is not null THEN
        SET str = concat(str, ',', fid);
        SET rootId = fid;
    ELSE
        SET rootId = fid;
    END IF;
END WHILE;
return str;
END

-- ----------------------------
-- 查子集合
-- ----------------------------
DROP FUNCTION IF EXISTS getChildList;
CREATE FUNCTION `getChildList`(rootId varchar(100))
RETURNS varchar(2000)
BEGIN
DECLARE str varchar(2000);
DECLARE cid varchar(100);
SET str = '$';
SET cid = rootId;
WHILE cid is not null DO
    SET str = concat(str, ',', cid);
    SELECT group_concat(invitee_id) INTO cid FROM t_user_agent_relation where FIND_IN_SET(inviter_id, cid) > 0;
END WHILE;
RETURN str;
END