/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : localhost:3308
 Source Schema         : sys-meet-data

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 02/11/2024 15:48:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dts_admin
-- ----------------------------
DROP TABLE IF EXISTS `dts_admin`;
CREATE TABLE `dts_admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '\'' COMMENT '头像图片',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `role_ids` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '[]' COMMENT '角色列表',
  `desc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户描述',
  `tel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of dts_admin
-- ----------------------------
INSERT INTO `dts_admin` VALUES (1, 'dtsadmin', '$2a$10$lHs59iD3Yt8qBbGmJcopo.LwOJwB0AzF6JE/zVcEUIYJlvSw1raQ.', NULL, NULL, 'https://juhuixing-public.oss-cn-shenzhen.aliyuncs.com/luq950dq4wzhsn54eq1y.jpeg', '2018-02-01 00:00:00', '2019-12-21 13:21:18', 0, '[1]', NULL, NULL, NULL);
INSERT INTO `dts_admin` VALUES (4, 'promotion123', '$2a$10$wDZLOLLnzZ1EFZ3ldZ1XFOUWDEX6TnQCUFdJz4g.PoMaLTzS8TjWq', '', NULL, '\'', '2019-01-07 15:16:59', '2019-01-07 15:17:34', 1, '[3]', NULL, NULL, NULL);
INSERT INTO `dts_admin` VALUES (5, 'dtsdemo', '$2a$10$zhzZI1jKYFSE/uLfKC0Mo.V0F1EhYFEJqx4UAvDkrFFK3zf69K08K', '', NULL, 'https://juhuixing-dts.oss-cn-shenzhen.aliyuncs.com/lb4z86x7mr735sy91ys9.jpeg', '2019-01-07 15:17:25', '2019-12-21 13:21:43', 0, '[4]', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for dts_permission
-- ----------------------------
DROP TABLE IF EXISTS `dts_permission`;
CREATE TABLE `dts_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `permission` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of dts_permission
-- ----------------------------
INSERT INTO `dts_permission` VALUES (1, 1, '*', '2019-01-01 00:00:00', '2019-01-01 00:00:00', 0);
INSERT INTO `dts_permission` VALUES (2, 2, 'admin:category:read', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (3, 2, 'admin:category:update', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (4, 2, 'admin:category:delete', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (5, 2, 'admin:category:create', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (6, 2, 'admin:category:list', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (7, 2, 'admin:brand:create', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (8, 2, 'admin:brand:list', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (9, 2, 'admin:brand:delete', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (10, 2, 'admin:brand:read', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (11, 2, 'admin:brand:update', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `dts_permission` VALUES (12, 3, 'admin:ad:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (13, 3, 'admin:ad:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (14, 3, 'admin:ad:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (15, 3, 'admin:ad:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (16, 3, 'admin:ad:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (17, 3, 'admin:groupon:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (18, 3, 'admin:groupon:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (19, 3, 'admin:groupon:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (20, 3, 'admin:groupon:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (21, 3, 'admin:groupon:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (22, 3, 'admin:topic:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (23, 3, 'admin:topic:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (24, 3, 'admin:topic:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (25, 3, 'admin:topic:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (26, 3, 'admin:topic:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (27, 3, 'admin:coupon:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (28, 3, 'admin:coupon:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (29, 3, 'admin:coupon:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (30, 3, 'admin:coupon:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (31, 3, 'admin:coupon:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `dts_permission` VALUES (32, 4, 'admin:groupon:update', '2019-12-15 12:30:30', '2019-12-15 12:30:30', 0);
INSERT INTO `dts_permission` VALUES (33, 4, 'admin:groupon:delete', '2019-12-15 12:30:30', '2019-12-15 12:30:30', 0);
INSERT INTO `dts_permission` VALUES (34, 4, 'admin:groupon:create', '2019-12-15 12:30:30', '2019-12-15 12:30:30', 0);
INSERT INTO `dts_permission` VALUES (35, 4, 'admin:groupon:list', '2019-12-15 12:30:30', '2019-12-15 12:30:30', 0);
INSERT INTO `dts_permission` VALUES (36, 4, 'admin:groupon:read', '2019-12-15 12:30:30', '2019-12-15 12:30:30', 0);
INSERT INTO `dts_permission` VALUES (37, 4, 'admin:order:list', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (38, 4, 'admin:order:reply', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (39, 4, 'admin:order:ship', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (40, 4, 'admin:order:refund', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (41, 4, 'admin:order:listShip', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (42, 4, 'admin:order:read', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (43, 4, 'admin:comment:list', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (44, 4, 'admin:goods:update', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (45, 4, 'admin:goods:delete', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (46, 4, 'admin:goods:create', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (47, 4, 'admin:goods:list', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (48, 4, 'admin:goods:read', '2019-12-15 12:30:31', '2019-12-15 12:30:31', 0);
INSERT INTO `dts_permission` VALUES (49, 4, 'admin:stat:user', '2019-12-15 12:30:32', '2019-12-15 12:30:32', 0);
INSERT INTO `dts_permission` VALUES (50, 4, 'admin:stat:order', '2019-12-15 12:30:32', '2019-12-15 12:30:32', 0);
INSERT INTO `dts_permission` VALUES (51, 4, 'admin:stat:goods', '2019-12-15 12:30:32', '2019-12-15 12:30:32', 0);

-- ----------------------------
-- Table structure for dts_role
-- ----------------------------
DROP TABLE IF EXISTS `dts_role`;
CREATE TABLE `dts_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `desc` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of dts_role
-- ----------------------------
INSERT INTO `dts_role` VALUES (1, '超级管理员', '所有模块的权限', 1, '2019-01-01 00:00:00', '2019-01-01 00:00:00', 0);
INSERT INTO `dts_role` VALUES (2, '商场管理员', '只有商场模块的操作权限', 1, '2019-01-01 00:00:00', '2019-01-07 15:15:12', 0);
INSERT INTO `dts_role` VALUES (3, '推广管理员', '只有推广模块的操作权限', 1, '2019-01-01 00:00:00', '2019-01-07 15:15:24', 0);
INSERT INTO `dts_role` VALUES (4, '品牌制造商', '普通商户', 1, '2019-06-22 21:28:19', '2019-06-22 21:54:45', 0);

-- ----------------------------
-- Table structure for sys_approval
-- ----------------------------
DROP TABLE IF EXISTS `sys_approval`;
CREATE TABLE `sys_approval`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NULL DEFAULT NULL COMMENT '审核人id',
  `meet_id` int NULL DEFAULT NULL COMMENT '会见、预约表id',
  `approval` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核理由',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '审核信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_approval
-- ----------------------------
INSERT INTO `sys_approval` VALUES (1, 1, 5, '不通过', '2024-11-01 13:52:12', '2024-11-01 13:52:16');

-- ----------------------------
-- Table structure for sys_cert
-- ----------------------------
DROP TABLE IF EXISTS `sys_cert`;
CREATE TABLE `sys_cert`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '律师姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '律师手机号码',
  `gender` tinyint(1) NULL DEFAULT 0 COMMENT '性别： 0男， 1 女',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '最新照片',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '身份证号',
  `wk_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '职业证号',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '所在城市',
  `ba_date` datetime NULL DEFAULT NULL COMMENT '备案日期',
  `wk_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '职业证书',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '审核状态；默认0审核中；1通过；2驳回',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '认证信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_cert
-- ----------------------------
INSERT INTO `sys_cert` VALUES (6, 59, '1', '2', 0, 'http://localhost:8080/client/storage/59-1730375849679-最新照片.jpg', '3', '4', '5', '2024-10-31 00:00:00', 'http://localhost:8080/client/storage/59-1730375849682-职业资格证书.jpg', 0, '2024-10-31 19:57:30', '2024-10-31 19:57:30', 0);

-- ----------------------------
-- Table structure for sys_meet
-- ----------------------------
DROP TABLE IF EXISTS `sys_meet`;
CREATE TABLE `sys_meet`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '律师姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '律师手机号码',
  `people` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会见人',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会见地点',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '说明',
  `start_time` datetime NULL DEFAULT NULL COMMENT '会见开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '会见结束时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '审核状态；默认0审核中；1通过；2驳回',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '预约、会见申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_meet
-- ----------------------------
INSERT INTO `sys_meet` VALUES (3, 59, '张三', '15542869574', '李四', 'xx市xx看守所', '456', '2024-11-01 09:00:00', '2024-11-01 10:00:00', 3, '2024-11-01 11:13:35', '2024-11-01 11:13:35');
INSERT INTO `sys_meet` VALUES (4, 59, '李斯特', '14598753695', '王五', 'xx市xx看守所', '2', '2024-11-01 09:00:00', '2024-11-01 10:00:00', 0, '2024-11-01 11:14:42', '2024-11-01 11:14:42');
INSERT INTO `sys_meet` VALUES (5, 59, '李斯特', '14598753695', '王五', 'xx市xx看守所', '2', '2024-11-01 09:00:00', '2024-11-01 10:00:00', 2, '2024-11-01 11:14:42', '2024-11-01 11:14:42');
INSERT INTO `sys_meet` VALUES (6, 59, '李斯特', '14598753695', '王五', 'xx市xx看守所', '2', '2024-11-01 09:00:00', '2024-11-01 10:00:00', 3, '2024-11-01 11:14:42', '2024-11-01 11:14:42');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名称',
  `pass_word` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `gender` tinyint NOT NULL DEFAULT 0 COMMENT '性别：0 未知， 1男， 1 女',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `user_level` tinyint NULL DEFAULT 0 COMMENT '1;普通管理员2;高级管理员',
  `nickname` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户昵称或网络名称',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户手机号码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `weixin_openid` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '微信登录openid',
  `session_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '微信登录会话KEY',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0 可用, 1 禁用, 2 注销',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (59, '123456', '123456', 0, NULL, '2024-10-28 17:01:01', '0:0:0:0:0:0:0:1', 2, '123456', '18842833236', 'https://9ly7904782sq.vicp.fun/wx/storage/用户.png', 'o-TCI7aP3stRiDxRzpM6yIve_eoM', '', 0, '2024-10-25 16:06:50', '2024-10-28 17:01:01', 0);
INSERT INTO `sys_user` VALUES (60, '147258', '147258', 0, NULL, '2024-11-02 14:03:36', '0:0:0:0:0:0:0:1', 0, '147258', '19545236985', 'https://9ly7904782sq.vicp.fun/wx/storage/用户.png', 'ojm_O4ofyqO03J5AY61wCI_UNRNk', 'BUSI8AKojX8SpODPsnJZVA==', 0, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
