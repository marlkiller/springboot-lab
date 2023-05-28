/*
 Navicat Premium Data Transfer

 Source Server         : 99.dev
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Host           : 100.96.33.90:3306
 Source Schema         : db_dev

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 28/05/2023 09:42:41
*/

use db_dev;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for user
-- ----------------------------
-- DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `name`) VALUES (1, 'voidvvvv');
INSERT INTO `user` (`id`, `name`) VALUES (2, 'yslvvvvv');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
