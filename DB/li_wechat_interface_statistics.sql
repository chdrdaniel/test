/*
 Navicat Premium Data Transfer

 Source Server         : 个人
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 101.43.251.145:3306
 Source Schema         : lilishop

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 02/06/2022 09:30:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for li_wechat_interface_statistics
-- ----------------------------
DROP TABLE IF EXISTS `li_wechat_interface_statistics`;
CREATE TABLE `li_wechat_interface_statistics`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标志 true/false 删除/未删除',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  `refHour` int NULL DEFAULT NULL COMMENT '数据的小时',
  `callbackCount` int NULL DEFAULT NULL COMMENT '通过服务器配置地址获得消息后，被动回复用户消息的次数',
  `failCount` int NULL DEFAULT NULL COMMENT '上述动作的失败次数',
  `totalTimeCost` int NULL DEFAULT NULL COMMENT '总耗时，除以callback_count即为平均耗时',
  `maxTimeCost` int NULL DEFAULT NULL COMMENT '最大耗时',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of li_wechat_interface_statistics
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
