/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50136
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50136
File Encoding         : 65001

Date: 2019-09-03 22:55:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_fileburst`
-- ----------------------------
DROP TABLE IF EXISTS `t_fileburst`;
CREATE TABLE `t_fileburst` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `filename` varchar(50) NOT NULL COMMENT '文件名称',
  `burst_id` int(11) NOT NULL COMMENT '分片id, 相同文件名+burst_id 组合后成为一个文件',
  `url` varchar(50) NOT NULL COMMENT '文件地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fileburst
-- ----------------------------

-- ----------------------------
-- Table structure for `t_filemessage`
-- ----------------------------
DROP TABLE IF EXISTS `t_filemessage`;
CREATE TABLE `t_filemessage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `filename` varchar(50) NOT NULL COMMENT '文件名称',
  `size` decimal(10,0) NOT NULL COMMENT '文件大小',
  `burst_size` int(11) NOT NULL COMMENT '分片数',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `carete_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_filemessage
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(11) NOT NULL DEFAULT '0' COMMENT 'id',
  `role_name` varchar(20) NOT NULL COMMENT '权限:0,管理员1.普通用户,2.vip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('0', '超级管理员');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `email` varchar(20) NOT NULL COMMENT '邮箱',
  `username` varchar(20) DEFAULT NULL COMMENT '登录名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `salt` varchar(20) NOT NULL COMMENT '盐',
  `role_id` bigint(20) NOT NULL COMMENT '从t_role传递id',
  `regist_time` bigint(20) NOT NULL COMMENT '注册时间',
  `vip_outtime` bigint(20) DEFAULT NULL COMMENT 'vip过期时间',
  `vip_opentime` bigint(20) DEFAULT NULL COMMENT 'vip开通时间',
  `lastlanding _time` bigint(20) DEFAULT NULL COMMENT '上次登录时间',
  `user_type` int(11) NOT NULL COMMENT '用户类型:0:管理员;1:vip;2:普通用户',
  `used _content` decimal(10,0) NOT NULL COMMENT '已用文件空间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_index` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
