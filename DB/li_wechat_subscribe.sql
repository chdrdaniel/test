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

 Date: 02/06/2022 10:56:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for li_wechat_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `li_wechat_subscribe`;
CREATE TABLE `li_wechat_subscribe`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关注者openId',
  `subscribe_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关注者状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
  `tagid_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户被打上的标签 ID 列表',
  `subscribe_scene` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '返回用户关注的渠道来源',
  `head_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户头像',
  `tagid_name_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户被打上的标签名称列表'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of li_wechat_subscribe
-- ----------------------------
INSERT INTO `li_wechat_subscribe` VALUES ('oUvmc6WeodkutBDGFKSbhw7DYc3k', 'ALREADY_SUBSCRIBE', '', NULL, '2,3', NULL, '', NULL);

SET FOREIGN_KEY_CHECKS = 1;
