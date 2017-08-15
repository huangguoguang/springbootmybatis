/*
Navicat MySQL Data Transfer

Source Server         : 开发
Source Server Version : 50621
Source Host           : 192.168.1.19:1234
Source Database       : user_pay

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-08-15 14:29:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_agent_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_user_agent_relation`;
CREATE TABLE `t_user_agent_relation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(10) DEFAULT NULL,
  `dept_id` varchar(10) DEFAULT NULL,
  `agent_name` varchar(50) DEFAULT NULL,
  `inviter_id` varchar(20) DEFAULT NULL,
  `invitee_id` varchar(20) DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `del_tag` char(1) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
