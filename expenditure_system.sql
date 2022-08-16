/*
 Navicat Premium Data Transfer

 Source Server         : old_phpstudy_3307
 Source Server Type    : MySQL
 Source Server Version : 50096
 Source Host           : localhost:3307
 Source Schema         : expenditure_system

 Target Server Type    : MySQL
 Target Server Version : 50096
 File Encoding         : 65001

 Date: 16/08/2022 13:57:08
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `type_id` int(11) NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `price` double(7, 2) NULL DEFAULT NULL,
  `time` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (5, NULL, 2, 3, '跟朋友一起吃中餐', 300.00, '2022-08-16');
INSERT INTO `record` VALUES (6, NULL, 1, 4, '张妈结婚', 600.00, '2022-08-16');
INSERT INTO `record` VALUES (7, NULL, 7, 1, '一套沙发', 3200.00, '2022-08-16');
INSERT INTO `record` VALUES (8, NULL, 1, 5, '生病住院', 4000.00, '2022-08-16');
INSERT INTO `record` VALUES (9, NULL, 2, 5, '感冒药一盒', 60.00, '2022-08-16');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '购买生活用品');
INSERT INTO `type` VALUES (2, '购买学习用品');
INSERT INTO `type` VALUES (3, '餐饮支出');
INSERT INTO `type` VALUES (4, '人情支出');
INSERT INTO `type` VALUES (5, '医疗支出');
INSERT INTO `type` VALUES (6, '其他');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123456');
INSERT INTO `user` VALUES (2, '李老四', '123456');
INSERT INTO `user` VALUES (3, '王五', '123456');
INSERT INTO `user` VALUES (7, '西米', '123456');

SET FOREIGN_KEY_CHECKS = 1;
