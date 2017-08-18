CREATE TABLE `t_user_account` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `account_type` varchar(10) DEFAULT NULL COMMENT '�˺�����',
  `account_level` int(6) DEFAULT NULL COMMENT '�ȼ�',
  `consum_amount` decimal(10,2) DEFAULT NULL COMMENT '���ѽ��',
  `risk_level` int(6) DEFAULT NULL COMMENT '���յȼ�',
  `status` int(2) DEFAULT NULL COMMENT '�û�״̬(1����,2,����,3ע��)',
  `create_time` bigint(13) NOT NULL COMMENT '����ʱ��',
  `update_time` bigint(13) DEFAULT NULL COMMENT '����ʱ��',
  `del_tag` char(1) DEFAULT NULL COMMENT 'ɾ��״̬',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_business` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '����',
  `mobile` varchar(20) DEFAULT NULL COMMENT '�ֻ�����',
  `id_card` varchar(20) DEFAULT NULL COMMENT '���֤',
  `email` varchar(100) DEFAULT NULL COMMENT '����',
  `password` varchar(255) DEFAULT NULL COMMENT '�û�����(MD5)',
  `birthday` date DEFAULT NULL,
  `education` varchar(100) DEFAULT NULL COMMENT '�����̶�',
  `create_time` bigint(13) NOT NULL COMMENT '����ʱ��',
  `update_time` bigint(13) DEFAULT NULL COMMENT '����ʱ��',
  `del_tag` char(1) DEFAULT NULL COMMENT 'ɾ��״̬',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;


CREATE TABLE `t_user_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT NULL COMMENT '�û�ID',
  `nick_icon` varchar(255) DEFAULT NULL COMMENT 'ͼ��',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '�ǳ�',
  `gender` int(1) DEFAULT NULL COMMENT '�Ա�',
  `province` varchar(100) DEFAULT NULL COMMENT 'ʡ',
  `city` varchar(100) DEFAULT NULL COMMENT '��',
  `area` varchar(255) DEFAULT NULL COMMENT '����',
  `street` varchar(255) DEFAULT NULL COMMENT '�ֵ�',
  `address` varchar(255) DEFAULT NULL COMMENT '��ַ',
  `create_time` bigint(13) NOT NULL COMMENT '����ʱ��',
  `update_time` bigint(13) DEFAULT NULL COMMENT '����ʱ��',
  `del_tag` char(1) DEFAULT NULL COMMENT 'ɾ��״̬',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;



CREATE TABLE `t_user_login` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT NULL COMMENT '�û�ID',
  `type` varchar(10) DEFAULT NULL COMMENT '��¼����',
  `status` int(2) DEFAULT NULL COMMENT '��¼״̬',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `address` varchar(255) DEFAULT NULL COMMENT '��ַ',
  `create_time` bigint(13) NOT NULL COMMENT '����ʱ��',
  `update_time` bigint(13) DEFAULT NULL COMMENT '����ʱ��',
  `del_tag` char(1) DEFAULT NULL COMMENT 'ɾ��״̬',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=966 DEFAULT CHARSET=utf8;





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




CREATE TABLE `t_user_capital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '�ʽ�ID',
  `user_id` varchar(50) DEFAULT NULL COMMENT '�û�ID',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '���',
  `status` varchar(10) DEFAULT NULL COMMENT '�˻�״̬',
  `create_time` mediumtext,
  `update_time` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;




CREATE TABLE `t_user_capital_billing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL COMMENT '�û�ID',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '���',
  `balance` decimal(10,0) DEFAULT NULL COMMENT '���',
  `operate` varchar(5) DEFAULT NULL COMMENT '��/��',
  `purpose` varchar(5) DEFAULT NULL COMMENT 'Ŀ��',
  `correlation` varchar(50) DEFAULT NULL COMMENT '����',
  `description` varchar(100) DEFAULT NULL COMMENT '����',
  `del_tag` char(1) DEFAULT '0',
  `createTime` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

