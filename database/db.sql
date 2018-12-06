/*
SQLyog Professional v12.5.1 (64 bit)
MySQL - 10.1.35-MariaDB : Database - pulsa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pulsa` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `pulsa`;

/*Table structure for table `mst_paket_data` */

DROP TABLE IF EXISTS `mst_paket_data`;

CREATE TABLE `mst_paket_data` (
  `C_ID` char(10) NOT NULL,
  `V_PROVIDER` varchar(10) DEFAULT NULL,
  `V_DESC` varchar(255) DEFAULT NULL,
  `N_SELL` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mst_paket_data` */

insert  into `mst_paket_data`(`C_ID`,`V_PROVIDER`,`V_DESC`,`N_SELL`) values 
('FRM_L','Indosat','Indosat Freedom Combo L 26GB',90000.00);

/*Table structure for table `mst_pulsa` */

DROP TABLE IF EXISTS `mst_pulsa`;

CREATE TABLE `mst_pulsa` (
  `C_ID` char(10) NOT NULL,
  `V_PROVIDER` varchar(10) DEFAULT NULL,
  `N_PULSA` decimal(12,2) DEFAULT NULL,
  `N_SELL` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mst_pulsa` */

insert  into `mst_pulsa`(`C_ID`,`V_PROVIDER`,`N_PULSA`,`N_SELL`) values 
('PLSAX10','AXIS',10000.00,9900.00);

/*Table structure for table `mst_token` */

DROP TABLE IF EXISTS `mst_token`;

CREATE TABLE `mst_token` (
  `C_ID` char(10) DEFAULT NULL,
  `N_PULSA` decimal(12,2) DEFAULT NULL,
  `N_SELL` decimal(12,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mst_token` */

insert  into `mst_token`(`C_ID`,`N_PULSA`,`N_SELL`) values 
('PLSTKN20',20000.00,20000.00);

/*Table structure for table `mst_user` */

DROP TABLE IF EXISTS `mst_user`;

CREATE TABLE `mst_user` (
  `C_USERNAME` char(10) NOT NULL,
  `V_FULLNAME` varchar(120) DEFAULT NULL,
  `V_NOHP` varchar(15) DEFAULT NULL,
  `V_MAIL` varchar(50) DEFAULT NULL,
  `V_PASSWORD` varchar(10) DEFAULT NULL,
  `N_SALDO` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`C_USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mst_user` */

insert  into `mst_user`(`C_USERNAME`,`V_FULLNAME`,`V_NOHP`,`V_MAIL`,`V_PASSWORD`,`N_SALDO`) values 
('eka','Eka Kartika','087885804933','Kar_tik52@gmail.com','eka123',1000000.00);

/*Table structure for table `tbl_cart` */

DROP TABLE IF EXISTS `tbl_cart`;

CREATE TABLE `tbl_cart` (
  `C_ID` char(10) NOT NULL,
  `C_USERNAME` char(10) NOT NULL,
  `V_PHONE` varchar(15) NOT NULL,
  `V_TOKEN_ID` varchar(15) DEFAULT NULL,
  `C_TYPE` tinyint(1) DEFAULT NULL COMMENT '1 = paket, 2 = pulsa, 3 = token',
  PRIMARY KEY (`C_ID`,`C_USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_cart` */

insert  into `tbl_cart`(`C_ID`,`C_USERNAME`,`V_PHONE`,`V_TOKEN_ID`,`C_TYPE`) values 
('PLSIND10','eka','085774056480',NULL,2);

/*Table structure for table `tbl_trans` */

DROP TABLE IF EXISTS `tbl_trans`;

CREATE TABLE `tbl_trans` (
  `N_TRANSAKSI` int(11) NOT NULL AUTO_INCREMENT,
  `C_ID` char(10) DEFAULT NULL,
  `V_PHONE` varchar(15) DEFAULT NULL,
  `D_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `N_SELL` decimal(12,2) DEFAULT NULL,
  `C_STATUS` tinyint(1) DEFAULT '0',
  `V_TOKEN_ID` varchar(15) DEFAULT NULL,
  `C_USERNAME` char(10) NOT NULL,
  PRIMARY KEY (`N_TRANSAKSI`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_trans` */

insert  into `tbl_trans`(`N_TRANSAKSI`,`C_ID`,`V_PHONE`,`D_TIME`,`N_SELL`,`C_STATUS`,`V_TOKEN_ID`,`C_USERNAME`) values 
(1,'PLSTKN20','087885804933','2018-12-06 21:13:39',20000.00,1,'14220972831','eka'),
(2,'PLSTKN20',NULL,'2018-12-07 00:56:55',20000.00,1,NULL,'eka');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
