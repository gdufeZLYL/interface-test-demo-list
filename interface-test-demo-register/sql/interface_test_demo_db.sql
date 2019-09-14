/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : interface_test_demo_db

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-09-14 13:18:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) DEFAULT NULL COMMENT '昵称',
  `user_id` varchar(63) DEFAULT NULL COMMENT '用户ID',
  `password` varchar(63) DEFAULT NULL COMMENT '密码',
  `phone` varchar(63) DEFAULT NULL COMMENT '手机',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
