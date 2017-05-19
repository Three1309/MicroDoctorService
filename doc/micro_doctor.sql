/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.51 : Database - micro_doctor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`micro_doctor` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `micro_doctor`;

/*Table structure for table `appointment_tab` */

DROP TABLE IF EXISTS `appointment_tab`;

CREATE TABLE `appointment_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `patientId` int(11) DEFAULT NULL COMMENT '病人ID',
  `doctorId` int(11) DEFAULT NULL COMMENT '医师ID',
  `seeTime` date DEFAULT NULL COMMENT '就诊日期时间',
  `disease` text CHARACTER SET utf8 COMMENT '病症',
  `dateTime` timestamp NULL DEFAULT NULL COMMENT '预约时间',
  `diagnose` text CHARACTER SET utf8 COMMENT '医生诊断',
  `dNumber` int(11) DEFAULT NULL COMMENT '字段排号（预约医师的序号）',
  `doctorSay` text CHARACTER SET utf8 COMMENT '医师留言（病人预约时）',
  PRIMARY KEY (`id`),
  KEY `patientId` (`patientId`),
  KEY `doctorId` (`doctorId`),
  CONSTRAINT `doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctor_tab` (`doctorId`),
  CONSTRAINT `patientId` FOREIGN KEY (`patientId`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `appointment_tab` */

insert  into `appointment_tab`(`id`,`patientId`,`doctorId`,`seeTime`,`disease`,`dateTime`,`diagnose`,`dNumber`,`doctorSay`) values (1,6,2,'2017-05-09','感冒','2017-05-03 12:01:41','是感冒了，多喝热水',1,'早上来'),(2,6,2,'2017-05-20','鼻子不通','2017-05-18 11:37:41',NULL,1,NULL),(3,6,3,'2017-05-19','你是关羽了告诉我醉的是关羽了告诉我醉的是关羽了告诉我醉的是关羽了告诉我醉的是关羽了告诉我醉的是关羽了告诉我醉的是关羽','2017-05-18 11:50:16',NULL,1,NULL),(4,6,2,'2017-05-19','喉咙有痰，总是核不出来','2017-05-18 12:03:18',NULL,1,NULL),(5,6,2,'2017-05-19','喉咙发炎哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦你是关羽了告诉我醉的是关羽了告诉我醉的是关羽了告诉我醉的是关羽了告诉1','2017-05-18 12:04:01',NULL,2,NULL);

/*Table structure for table `collect_tab` */

DROP TABLE IF EXISTS `collect_tab`;

CREATE TABLE `collect_tab` (
  `collectId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sendId` int(11) DEFAULT NULL COMMENT 'send表id',
  `collectorId` int(11) DEFAULT NULL COMMENT '收藏者id',
  `collectTime` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`collectId`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8;

/*Data for the table `collect_tab` */

insert  into `collect_tab`(`collectId`,`sendId`,`collectorId`,`collectTime`) values (62,6,3,'2017-05-16 19:31:03'),(104,5,2,'2017-05-16 22:20:30'),(105,6,2,'2017-05-16 22:26:03'),(108,5,6,'2017-05-16 22:30:27'),(110,6,6,'2017-05-16 23:36:41'),(121,6,1,'2017-05-17 14:10:37'),(122,5,1,'2017-05-17 14:10:45'),(123,6,7,'2017-05-17 15:09:24'),(124,5,7,'2017-05-17 15:09:31'),(137,6,5,'2017-05-17 20:21:06'),(140,5,5,'2017-05-17 23:38:49'),(141,10,6,'2017-05-18 12:08:04'),(142,6,4,'2017-05-18 17:18:03'),(143,10,4,'2017-05-18 17:18:04'),(144,5,4,'2017-05-18 17:18:06'),(147,12,4,'2017-05-18 23:49:08');

/*Table structure for table `discuss_tab` */

DROP TABLE IF EXISTS `discuss_tab`;

CREATE TABLE `discuss_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `sendId` int(11) DEFAULT NULL COMMENT '被评论的内容ID',
  `discusserId` int(11) DEFAULT NULL COMMENT '评论者ID',
  `discussContent` text CHARACTER SET utf8 COMMENT '评论内容',
  `discussTime` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`),
  KEY `sendId` (`sendId`),
  KEY `FK_discuss_tab` (`discusserId`),
  CONSTRAINT `FK_discuss_tab` FOREIGN KEY (`discusserId`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `sendId` FOREIGN KEY (`sendId`) REFERENCES `send_tab` (`sendId`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `discuss_tab` */

insert  into `discuss_tab`(`id`,`sendId`,`discusserId`,`discussContent`,`discussTime`) values (1,6,2,'多喝热水是有好处的，反正很好，喝过的都说好，我是华佗','2017-05-17 00:50:01'),(2,6,7,'多喝热水是有好处的，反正很好，喝过的都说好，我是田七','2017-05-17 00:50:13'),(5,6,1,'你好像是关羽','2017-05-17 14:20:44'),(6,5,1,'我是管理员，你是关羽吗','2017-05-17 15:06:17'),(7,5,7,'我是我，我自己都不知道','2017-05-17 15:07:34'),(8,6,7,'我是关羽','2017-05-17 15:07:52'),(9,6,7,'你是关羽了告诉你是关羽了告诉你是关羽了告诉你是关羽了告诉你是关羽了告诉你是关羽','2017-05-17 15:08:11'),(14,6,5,'的呃呃呃额额的的是关羽了告诉我醉酒吗？，的是关羽了告诉我醉酒吗？，的是关羽了告诉我醉酒吗？','2017-05-17 22:50:20'),(15,5,5,'咯几回合','2017-05-17 22:53:35'),(36,6,4,'你很棒喔','2017-05-18 23:26:40'),(44,12,4,'墨迹','2017-05-18 23:49:30');

/*Table structure for table `doclikes_tab` */

DROP TABLE IF EXISTS `doclikes_tab`;

CREATE TABLE `doclikes_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apotmId` int(11) DEFAULT NULL COMMENT '预约表ID',
  `doctorId` int(11) DEFAULT NULL COMMENT '医师ID，关联user，是user的id',
  `likesTime` timestamp NULL DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `doclikes_tab` */

/*Table structure for table `doctor_tab` */

DROP TABLE IF EXISTS `doctor_tab`;

CREATE TABLE `doctor_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `doctorId` int(11) DEFAULT NULL COMMENT '用户ID（外键关联用户信息表中的id）',
  `hospital` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '所属医院',
  `office` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '科室',
  `amount` int(11) DEFAULT '0' COMMENT '接诊量',
  `likenum` int(11) DEFAULT '0' COMMENT '好评数',
  PRIMARY KEY (`id`),
  KEY `user_id` (`doctorId`),
  CONSTRAINT `user_id` FOREIGN KEY (`doctorId`) REFERENCES `user_tab` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `doctor_tab` */

insert  into `doctor_tab`(`id`,`doctorId`,`hospital`,`office`,`amount`,`likenum`) values (1,2,'廉江第一医院','内科',1,0),(2,3,'江门医院','骨科',0,0),(3,4,'江门医院','内科',0,0),(4,5,'廉江第一医院','骨科',0,0),(5,6,'江门人民医院','儿科',-1,0);

/*Table structure for table `likes_tab` */

DROP TABLE IF EXISTS `likes_tab`;

CREATE TABLE `likes_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `sendId` int(11) DEFAULT NULL COMMENT '被点赞的内容ID',
  `likeserId` int(11) DEFAULT NULL COMMENT '点赞者ID，关联user',
  `likesTime` datetime DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  KEY `likesId` (`sendId`),
  KEY `likeId` (`likeserId`),
  CONSTRAINT `likesId` FOREIGN KEY (`likeserId`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `like_tab` FOREIGN KEY (`sendId`) REFERENCES `send_tab` (`sendId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `likes_tab` */

insert  into `likes_tab`(`id`,`sendId`,`likeserId`,`likesTime`) values (5,5,1,'2017-05-17 14:10:28'),(6,6,1,'2017-05-17 14:10:32'),(7,5,7,'2017-05-17 15:07:38'),(8,6,7,'2017-05-17 15:07:44'),(11,6,5,'2017-05-17 20:21:09'),(13,5,5,'2017-05-17 20:21:27'),(16,5,6,'2017-05-18 12:06:59'),(17,6,6,'2017-05-18 12:07:04'),(18,10,6,'2017-05-18 12:08:02'),(19,5,4,'2017-05-18 17:17:53'),(20,6,4,'2017-05-18 17:17:55'),(21,10,4,'2017-05-18 17:18:01'),(24,12,4,'2017-05-18 23:49:07');

/*Table structure for table `send_tab` */

DROP TABLE IF EXISTS `send_tab`;

CREATE TABLE `send_tab` (
  `sendId` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖ID',
  `userId` int(11) DEFAULT NULL COMMENT '发帖人ID',
  `sendTitle` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '标题',
  `sendContent` text CHARACTER SET utf8 COMMENT '内容文字',
  `sendTime` datetime DEFAULT NULL COMMENT '时间',
  `likesAmount` int(11) DEFAULT '0' COMMENT '点赞数',
  `discussAmount` int(11) DEFAULT '0' COMMENT '评论数',
  `collectAmount` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`sendId`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `send_tab` */

insert  into `send_tab`(`sendId`,`userId`,`sendTitle`,`sendContent`,`sendTime`,`likesAmount`,`discussAmount`,`collectAmount`) values (5,2,'多喝热水的重要性，王','多喝热水是有好处的，反正很好，喝过的都说好，没有骗人','2017-05-16 15:55:48',5,3,6),(6,6,'多喝热水的重要','多喝热水是有好处的，反正很好，喝过的都说好，没有骗人多喝热水是有好处的，反正很好，喝过的都说好，没有骗人多喝热水是有好处的，反正很好，喝过的都说好，没有骗人多喝热水是有好处的，反正很好，喝过的都说好，没有骗人多喝热水是有好处的，反正很好，喝过的都说好，没有骗人多喝热水是有好处的，反正很好，喝过的都说好，没有骗人多喝热水是有好处的，反正很好，喝过的都说好，没有骗人\r\n我红了~~~~~~~~~~~\r\n\r\n“多喝热水”最早就是有女生在微博上吐槽吧，感冒发烧痛经手凉之类不舒服的时候，男人都只会说“多喝热水”，然后竟然就火了……还火到现在……\r\n\r\n这也不是“在恋人中流行”。很多女生就是讨厌这样的话呢。\r\n再说，这很明显用处大大的啊……\r\n','2017-05-16 15:56:16',5,7,7),(10,6,'测试','鄙视','2017-05-18 12:06:08',2,0,2),(12,4,'爸爸all','考虑我KKK\n\n\n垃圾的别追人头y\n\n廉江的','2017-05-18 23:49:01',1,1,1);

/*Table structure for table `user_tab` */

DROP TABLE IF EXISTS `user_tab`;

CREATE TABLE `user_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长(用户ID)',
  `phone` varchar(225) NOT NULL COMMENT '联系方式(登录账号)',
  `password` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `name` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户昵称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别:0表示男，1表示女',
  `address` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系地址',
  `signature` text CHARACTER SET utf8 COMMENT '个性签名',
  `introduction` text CHARACTER SET utf8 COMMENT '个人简介（医师擅长、病人病历）',
  `type` int(11) DEFAULT NULL COMMENT '用户类型（0表示病人，1表示医师）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneunique` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `user_tab` */

insert  into `user_tab`(`id`,`phone`,`password`,`name`,`nickname`,`age`,`gender`,`address`,`signature`,`introduction`,`type`) values (1,'123456','123456','管理员','管理',23,1,'廉江',NULL,'我是管理员',2),(2,'18219111620','123456','华佗','华佗',0,1,'江门',NULL,'我是华佗',1),(3,'18219111621','123456','扁鹊','扁鹊',0,0,'教练',NULL,'我是扁鹊',1),(4,'18219111622','123456','李时珍','李时珍',0,0,'蓬江',NULL,'我是李时珍',1),(5,'18219111623','123456','姜子牙','姜子牙',18,1,'廉江','好','很好',1),(6,'18219111627','123456','王五','王',0,0,NULL,NULL,'我是田武',0),(7,'18219111628','123456','田七','田',45,1,NULL,NULL,'我是田武',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
