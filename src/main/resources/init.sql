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
create database if not exists db_dev;
use db_dev;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;



BEGIN;


CREATE TABLE IF NOT EXISTS `user`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

INSERT INTO `user` (`id`, `name`)
VALUES (1, 'voidvvvv');
INSERT INTO `user` (`id`, `name`)
VALUES (2, 'yslvvvvv');



CREATE TABLE IF NOT EXISTS `test`
(
    `id`   int(11) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    `sn`   int(11)      DEFAULT NULL,
    `uq`   int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uq` (`uq`) USING BTREE,
    KEY `idx_sn` (`sn`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `test` (`id`, `name`, `sn`, `uq`)
VALUES (1, '张三', 3, 3);
INSERT INTO `test` (`id`, `name`, `sn`, `uq`)
VALUES (4, '李四', 5, 5);
INSERT INTO `test` (`id`, `name`, `sn`, `uq`)
VALUES (7, '王五', 7, 7);
INSERT INTO `test` (`id`, `name`, `sn`, `uq`)
VALUES (10, '赵六', 11, 11);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
