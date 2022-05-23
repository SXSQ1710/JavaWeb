/*
Navicat MySQL Data Transfer

Source Server         : 教学用数据库
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : webstore

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2022-05-17 22:39:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `products`
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(5) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `stock` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('101', '数码相机', '奥林巴斯', '1330.00', '3');
INSERT INTO `products` VALUES ('102', '平板电脑', '苹果', '1990.50', '5');
INSERT INTO `products` VALUES ('103', '笔记本电脑', 'Levovo', '4900.00', '8');
