/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftjun16g2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftjun16g2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftjun16g2`;

/*Table structure for table `dezurstvo` */

DROP TABLE IF EXISTS `dezurstvo`;

CREATE TABLE `dezurstvo` (
  `DezurstvoID` int(11) NOT NULL AUTO_INCREMENT,
  `Datum` datetime DEFAULT NULL,
  `IspitniRokID` int(11) DEFAULT NULL,
  `NastavnikID` int(11) DEFAULT NULL,
  `PredmetID` int(11) DEFAULT NULL,
  PRIMARY KEY (`DezurstvoID`),
  KEY `IspitniRokID` (`IspitniRokID`),
  KEY `NastavnikID` (`NastavnikID`),
  KEY `PredmetID` (`PredmetID`),
  CONSTRAINT `dezurstvo_ibfk_1` FOREIGN KEY (`IspitniRokID`) REFERENCES `ispitnirok` (`IspitniRokID`),
  CONSTRAINT `dezurstvo_ibfk_2` FOREIGN KEY (`NastavnikID`) REFERENCES `nastavnik` (`NastavnikID`),
  CONSTRAINT `dezurstvo_ibfk_3` FOREIGN KEY (`PredmetID`) REFERENCES `predmet` (`PredmetID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dezurstvo` */

/*Table structure for table `ispitnirok` */

DROP TABLE IF EXISTS `ispitnirok`;

CREATE TABLE `ispitnirok` (
  `IspitniRokID` int(11) NOT NULL,
  `Naziv` varchar(100) DEFAULT NULL,
  `DatumOd` date DEFAULT NULL,
  `DatumDo` date DEFAULT NULL,
  PRIMARY KEY (`IspitniRokID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ispitnirok` */

insert  into `ispitnirok`(`IspitniRokID`,`Naziv`,`DatumOd`,`DatumDo`) values (1,'Jun 2016','2016-06-01','2016-06-30'),(2,'Jul 2016','2016-07-01','2016-07-31');

/*Table structure for table `nastavnik` */

DROP TABLE IF EXISTS `nastavnik`;

CREATE TABLE `nastavnik` (
  `NastavnikID` int(11) NOT NULL,
  `Ime` varchar(100) DEFAULT NULL,
  `Prezime` varchar(100) DEFAULT NULL,
  `Zvanje` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NastavnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nastavnik` */

insert  into `nastavnik`(`NastavnikID`,`Ime`,`Prezime`,`Zvanje`) values (1,'Marko','Markovic','asistent'),(2,'Ivana','Ivanovic','saradnik u nastavi'),(3,'Dragan','Markovic','docent'),(4,'Ana','Stevic','asistent-pripravnik');

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `PredmetID` int(11) NOT NULL,
  `Naziv` varchar(100) DEFAULT NULL,
  `TrajanjeIspita` int(11) DEFAULT NULL,
  PRIMARY KEY (`PredmetID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `predmet` */

insert  into `predmet`(`PredmetID`,`Naziv`,`TrajanjeIspita`) values (1,'Matematika 1',180),(2,'Engleski jezik struke 1',60),(3,'Francuski jezik struke 1',60),(4,'Operaciona istrazivanja',120),(5,'Projektovanje softvera',120);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
