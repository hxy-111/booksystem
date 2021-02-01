/*
Navicat MySQL Data Transfer

Source Server         : java
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : pblog

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2021-02-01 22:57:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) DEFAULT NULL,
  `admin_pwd` varchar(20) DEFAULT NULL,
  `admin_phone` varchar(22) DEFAULT NULL,
  `admin_email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'root', '17933634200', 'for@163.com');

-- ----------------------------
-- Table structure for adminmenu
-- ----------------------------
DROP TABLE IF EXISTS `adminmenu`;
CREATE TABLE `adminmenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of adminmenu
-- ----------------------------
INSERT INTO `adminmenu` VALUES ('1', '-1', '控制面板', 'fa-home', '');
INSERT INTO `adminmenu` VALUES ('2', '1', '后台主页', null, '/manager/index');
INSERT INTO `adminmenu` VALUES ('3', '-1', '书籍管理', 'fa-book', '');
INSERT INTO `adminmenu` VALUES ('4', '3', '书籍列表', null, '/manager/book');
INSERT INTO `adminmenu` VALUES ('5', '3', '类别列表', null, '/manager/category');
INSERT INTO `adminmenu` VALUES ('6', '3', '借阅记录', null, '/manager/records');
INSERT INTO `adminmenu` VALUES ('7', '-1', '用户管理', 'fa-pencil', '');
INSERT INTO `adminmenu` VALUES ('8', '7', '用户列表', null, '/manager/user');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(20) DEFAULT NULL,
  `book_author` varchar(20) DEFAULT NULL,
  `book_publish` varchar(20) DEFAULT NULL,
  `book_category` int(11) DEFAULT NULL,
  `book_total` int(11) DEFAULT NULL,
  `book_price` double DEFAULT NULL,
  `book_introduction` varchar(100) DEFAULT NULL,
  `book_state` int(2) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `book_category` (`book_category`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '巨人的陨落', '肯.福莱特', '江苏凤凰文艺出版社', '1', '10', '129', '在第一次世界大战中发生的故事', '0');
INSERT INTO `book` VALUES ('2', '三体', '刘慈欣', '南京大学出版社', '1', '20', '68', '科幻小说', '0');
INSERT INTO `book` VALUES ('3', '复活', '列夫.托尔斯泰', '上海译文出版社', '1', '50', '19', '俄国小说', '0');
INSERT INTO `book` VALUES ('6', '平凡的世界', '路遥', '上海文艺出版社', '1', '50', '88', '孙少平和孙少安两兄弟...', '0');
INSERT INTO `book` VALUES ('15', '白鹿原', '陈忠实', '南京出版社', '1', '10', '36', '当代小说', '0');
INSERT INTO `book` VALUES ('16', '计算机网络', '谢希仁', '电子工业出版社', '3', '30', '49', '计算机专业书籍', '1');
INSERT INTO `book` VALUES ('17', '霍乱时期的爱情', '加西亚·马尔克斯', '译林出版社', '9', '20', '39', '外国小说', '1');
INSERT INTO `book` VALUES ('18', '天才在左疯子在右', '高铭', '北京联合出版公司', '1', '40', '39.8', '心理学', '1');
INSERT INTO `book` VALUES ('19', '废都', '贾平凹', '商务印书馆', '8', '10', '29', '当代小说', '1');
INSERT INTO `book` VALUES ('20', 'jQuery', 'Ryan', '中国电力出版社', '3', '50', '78', 'js库', '1');

-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES ('1', '小说');
INSERT INTO `book_category` VALUES ('2', '历史');
INSERT INTO `book_category` VALUES ('3', '计算机');
INSERT INTO `book_category` VALUES ('4', '哲学');
INSERT INTO `book_category` VALUES ('5', '社会科学');
INSERT INTO `book_category` VALUES ('6', '政治法律');
INSERT INTO `book_category` VALUES ('7', '军事科学');
INSERT INTO `book_category` VALUES ('8', '中国文学');
INSERT INTO `book_category` VALUES ('9', '外国文学');
INSERT INTO `book_category` VALUES ('10', '外国传记');
INSERT INTO `book_category` VALUES ('11', '英语');
INSERT INTO `book_category` VALUES ('12', '俄国小说');
INSERT INTO `book_category` VALUES ('13', '心理学');
INSERT INTO `book_category` VALUES ('14', '言情小说');
INSERT INTO `book_category` VALUES ('15', '武侠小说');
INSERT INTO `book_category` VALUES ('16', '环境科学');
INSERT INTO `book_category` VALUES ('17', '纪实文学');

-- ----------------------------
-- Table structure for borrowbook
-- ----------------------------
DROP TABLE IF EXISTS `borrowbook`;
CREATE TABLE `borrowbook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `borrow_date` varchar(50) DEFAULT NULL,
  `return_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowbook
-- ----------------------------
INSERT INTO `borrowbook` VALUES ('73', '1', '1', '2020-08-10 23:29:37', '2020-08-10 23:30:21');
INSERT INTO `borrowbook` VALUES ('74', '1', '2', '2020-08-10 23:30:01', '2020-08-10 23:41:37');
INSERT INTO `borrowbook` VALUES ('75', '1', '3', '2020-08-10 23:42:14', '2020-08-10 23:42:22');
INSERT INTO `borrowbook` VALUES ('79', '1', '3', '2020-08-11 00:01:26', null);
INSERT INTO `borrowbook` VALUES ('80', '2', '6', '2020-08-11 00:02:01', null);
INSERT INTO `borrowbook` VALUES ('81', '1', '16', '2020-09-20 21:45:40', '2020-09-20 21:46:07');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `user_pwd` varchar(20) DEFAULT NULL,
  `user_phone` varchar(22) DEFAULT NULL,
  `user_email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hxy', '123456', '17933634212', 'for@163.com');
INSERT INTO `user` VALUES ('2', 'zhangfei', '123', '13356453634', 'forzf@163.com');
INSERT INTO `user` VALUES ('5', '李四', '123', '13733643423', null);
INSERT INTO `user` VALUES ('6', 'LeBronJames', '123', '15933634524', '1111@qq.com');
INSERT INTO `user` VALUES ('7', '科比', '123', '', '');
INSERT INTO `user` VALUES ('8', '柏拉图', '123', null, null);
INSERT INTO `user` VALUES ('9', '拿破仑', '123', null, null);
INSERT INTO `user` VALUES ('10', '欧文', '123', null, null);
INSERT INTO `user` VALUES ('11', '库兹马', '123', null, null);
INSERT INTO `user` VALUES ('12', '球哥', '123', null, null);
INSERT INTO `user` VALUES ('13', '魔术师', '123', null, null);
INSERT INTO `user` VALUES ('16', '周杰伦', '123', '', '');

-- ----------------------------
-- Table structure for usermenu
-- ----------------------------
DROP TABLE IF EXISTS `usermenu`;
CREATE TABLE `usermenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of usermenu
-- ----------------------------
INSERT INTO `usermenu` VALUES ('1', '-1', '控制面板', 'fa-home', '');
INSERT INTO `usermenu` VALUES ('2', '1', '前台主页', null, '/manager/index');
INSERT INTO `usermenu` VALUES ('3', '-1', '书籍管理', 'fa-book', '');
INSERT INTO `usermenu` VALUES ('4', '3', '书籍列表', null, '/manager/borrow');
INSERT INTO `usermenu` VALUES ('5', '-1', '借阅管理', 'fa-pencil', '');
INSERT INTO `usermenu` VALUES ('6', '5', '借阅情况', null, '/manager/return');
INSERT INTO `usermenu` VALUES ('7', '5', '借阅记录', null, '/manager/record');
