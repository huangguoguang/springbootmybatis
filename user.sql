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
-- model  `capital`   table `t_user_capital`
-- -----------------------------------------------------
CREATE TABLE `t_user_capital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资金ID',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `status` varchar(10) DEFAULT NULL COMMENT '账户状态',
  `create_time` mediumtext,
  `update_time` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;




-- -----------------------------------------------------
-- model  `capital`   table `t_user_capital_billing`
-- -----------------------------------------------------
CREATE TABLE `t_user_capital_billing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `balance` decimal(10,0) DEFAULT NULL COMMENT '余额',
  `operate` varchar(5) DEFAULT NULL COMMENT '加/减',
  `purpose` varchar(5) DEFAULT NULL COMMENT '目的',
  `correlation` varchar(50) DEFAULT NULL COMMENT '关联',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `del_tag` char(1) DEFAULT '0',
  `createTime` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




-- -----------------------------------------------------
-- model  `agent`   table `t_user_agent_allot`
-- -----------------------------------------------------
CREATE TABLE `t_user_agent_allot` (
  `id` varchar(50) NOT NULL,
  `dept_code` varchar(20) DEFAULT NULL,
  `level_allot_1` double(10,3) DEFAULT NULL,
  `level_allot_2` double(10,3) DEFAULT NULL,
  `level_allot_3` double(10,3) DEFAULT NULL,
  `del_tag` char(1) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




-- -----------------------------------------------------
-- model  `agent`   table `t_user_agent_relation`
-- -----------------------------------------------------
CREATE TABLE `t_user_agent_relation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(10) DEFAULT NULL,
  `dept_id` varchar(10) DEFAULT NULL,
  `agent_name` varchar(20) DEFAULT NULL,
  `inviter_id` varchar(20) DEFAULT NULL,
  `invitee_id` varchar(20) DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `del_tag` char(1) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;



