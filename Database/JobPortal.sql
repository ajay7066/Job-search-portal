/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - jobportal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jobportal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jobportal`;

/*Table structure for table `p_applay` */

DROP TABLE IF EXISTS `p_applay`;

CREATE TABLE `p_applay` (
  `ID` bigint(20) NOT NULL,
  `recruiterId` bigint(20) DEFAULT NULL,
  `jobId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `applayDate` timestamp NULL DEFAULT NULL,
  `userName` varchar(225) DEFAULT NULL,
  `resumeFile` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p_applay` */

insert  into `p_applay`(`ID`,`recruiterId`,`jobId`,`userId`,`applayDate`,`userName`,`resumeFile`) values (1,5,2,4,'2020-07-05 15:43:37','Aiko Waller','Nelle Odonnell.pdf');

/*Table structure for table `p_job` */

DROP TABLE IF EXISTS `p_job`;

CREATE TABLE `p_job` (
  `ID` bigint(20) NOT NULL,
  `companyName` varchar(225) DEFAULT NULL,
  `language` varchar(225) DEFAULT NULL,
  `Description` varchar(225) DEFAULT NULL,
  `postDate` date DEFAULT NULL,
  `postByRecId` varchar(225) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p_job` */

insert  into `p_job`(`ID`,`companyName`,`language`,`Description`,`postDate`,`postByRecId`,`address`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (2,'DDDD','FF','GHIGHhqbovbt48vyboq  w hvtia  hwui4tb','2018-01-12','Hariomukati741@gmail.com','fff','Hariomukati741@gmail.com','Hariomukati741@gmail.com','2019-01-29 21:14:23','2019-01-30 13:35:10'),(3,'FFF','DD','fbb','2018-01-12','Hariomukati741@gmail.com','DD','Hariomukati741@gmail.com','Hariomukati741@gmail.com','2019-01-30 14:56:42','2019-01-30 14:56:42');

/*Table structure for table `p_resume` */

DROP TABLE IF EXISTS `p_resume`;

CREATE TABLE `p_resume` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `email` varchar(225) DEFAULT NULL,
  `mobile` varchar(225) DEFAULT NULL,
  `gCourceName` varchar(225) DEFAULT NULL,
  `gInsName` varchar(225) DEFAULT NULL,
  `gPercentage` varchar(225) DEFAULT NULL,
  `hCourceName` varchar(225) DEFAULT NULL,
  `hInsName` varchar(225) DEFAULT NULL,
  `hPercentage` varchar(225) DEFAULT NULL,
  `sCourceName` varchar(225) DEFAULT NULL,
  `sInsName` varchar(225) DEFAULT NULL,
  `sPercentage` varchar(225) DEFAULT NULL,
  `skill` varchar(1000) DEFAULT NULL,
  `hobbies` varchar(1000) DEFAULT NULL,
  `pDetail` varchar(1000) DEFAULT NULL,
  `declaration` varchar(1000) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `place` varchar(225) DEFAULT NULL,
  `objective` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p_resume` */

insert  into `p_resume`(`ID`,`name`,`email`,`mobile`,`gCourceName`,`gInsName`,`gPercentage`,`hCourceName`,`hInsName`,`hPercentage`,`sCourceName`,`sInsName`,`sPercentage`,`skill`,`hobbies`,`pDetail`,`declaration`,`date`,`place`,`objective`) values (1,'Nelle Odonnell','viwewum@mailinator.com','9665856545','Nola Cannon','Cora Vaughan','86','Ross Green','Abraham Odonnell','68','Alana Bauer','Alden Hess','78','Do anim anim ad ipsu','Eligendi in sed fuga','Deserunt modi quis c','Incididunt ullam con','2020-07-05','Placeat ipsam sint','Repellendus Volupta'),(2,'Kyra Atkinson','jatyqavepy@mailinator.com','9685658565','Neville Daniels','Uriah Short','Ea illum quidem qui','Ella Ware','Quintessa Waller','Hic explicabo Volup','Noah Carpenter','Shaine Kramer','Minim est proident','Qui consectetur dolo','Placeat ea est quis','Sit officia eos mol','Consequat Consectet','2020-07-04','Voluptates blanditii','Officia dolore magni');

/*Table structure for table `p_role` */

DROP TABLE IF EXISTS `p_role`;

CREATE TABLE `p_role` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p_role` */

insert  into `p_role`(`ID`,`name`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Admin','Administration',NULL,NULL,'2019-01-28 13:09:06','2019-01-28 13:07:40'),(2,'Recruiter','Recruiter',NULL,NULL,'2019-01-28 13:07:43','2019-01-28 13:07:47'),(3,'Candidate','Candidate',NULL,NULL,'2019-01-28 13:07:49','2019-01-28 13:07:53');

/*Table structure for table `p_user` */

DROP TABLE IF EXISTS `p_user`;

CREATE TABLE `p_user` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `gender` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p_user` */

insert  into `p_user`(`ID`,`firstName`,`lastName`,`login`,`password`,`dob`,`mobileNo`,`roleId`,`gender`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Brian','Buchanan','Hariom@gmail.com','123','1997-10-06','9165415598',1,'Male','root','root','2019-01-28 17:23:22','2019-01-28 16:57:33'),(3,'Vijay','Bhayre','Vijay@gmail.com','123','1998-10-06','9865475655',2,'Male','Hariom@gmail.com','Hariom@gmail.com','2019-01-28 17:23:04','2019-01-29 12:37:49'),(4,'Aiko','Waller','savanmukati739@gmail.com','123','1998-10-06','9926884704',3,'Male','root','root','2019-01-30 17:05:22','2019-01-29 20:27:07'),(5,'Karleigh','Hurst','Hariomukati741@gmail.com','Hari@om!97m','1997-10-06','9165415598',2,'Male','Hariom@gmail.com','Hariom@gmail.com','2019-01-30 17:08:32','2019-01-29 21:13:33'),(6,'Maite','Lee','Allistair@gmail.com','123','1997-10-06','Charlotte',3,'Male','root','root','2019-01-30 22:54:12','2019-01-30 22:54:12');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
