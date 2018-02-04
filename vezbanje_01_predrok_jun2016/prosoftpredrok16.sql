/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftpredrok16
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftpredrok16` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftpredrok16`;

/*Table structure for table `kurs` */

DROP TABLE IF EXISTS `kurs`;

CREATE TABLE `kurs` (
  `KursID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(255) DEFAULT NULL,
  `MaxBrojPolaznika` int(11) DEFAULT NULL,
  `DatumOd` date DEFAULT NULL,
  `DatumDo` date DEFAULT NULL,
  `VrstaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`KursID`),
  KEY `VrstaID` (`VrstaID`),
  CONSTRAINT `kurs_ibfk_1` FOREIGN KEY (`VrstaID`) REFERENCES `vrstakursa` (`VrstaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `kurs` */

insert  into `kurs`(`KursID`,`Naziv`,`MaxBrojPolaznika`,`DatumOd`,`DatumDo`,`VrstaID`) values (1,'Osnovni kurs - grupa 1',2,'2016-05-04','2016-05-31',1),(2,'Osnovni kurs - grupa 2',2,'2016-05-11','2016-05-31',1),(3,'Francuski osnovni kurs - Grupa A',20,'2016-05-01','2016-05-31',2);

/*Table structure for table `polaznik` */

DROP TABLE IF EXISTS `polaznik`;

CREATE TABLE `polaznik` (
  `PolaznikID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(255) DEFAULT NULL,
  `Prezime` varchar(255) DEFAULT NULL,
  `JMBG` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PolaznikID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `polaznik` */

insert  into `polaznik`(`PolaznikID`,`Ime`,`Prezime`,`JMBG`) values (1,'Marija','Markovic','1111111111111'),(2,'Ivan','Ivanovic','2222222222222'),(3,'Dragana','Mitrovic','3333333333333'),(4,'Petar','Savic','4444444444444');

/*Table structure for table `polaznikkurs` */

DROP TABLE IF EXISTS `polaznikkurs`;

CREATE TABLE `polaznikkurs` (
  `PolaznikKursID` int(11) NOT NULL AUTO_INCREMENT,
  `PolaznikID` int(11) DEFAULT NULL,
  `KursID` int(11) DEFAULT NULL,
  `DatumPrijave` date DEFAULT NULL,
  PRIMARY KEY (`PolaznikKursID`),
  KEY `PolaznikID` (`PolaznikID`),
  KEY `KursID` (`KursID`),
  CONSTRAINT `polaznikkurs_ibfk_1` FOREIGN KEY (`PolaznikID`) REFERENCES `polaznik` (`PolaznikID`),
  CONSTRAINT `polaznikkurs_ibfk_2` FOREIGN KEY (`KursID`) REFERENCES `kurs` (`KursID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `polaznikkurs` */

insert  into `polaznikkurs`(`PolaznikKursID`,`PolaznikID`,`KursID`,`DatumPrijave`) values (1,1,3,'2016-04-01');

/*Table structure for table `vrstakursa` */

DROP TABLE IF EXISTS `vrstakursa`;

CREATE TABLE `vrstakursa` (
  `VrstaID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VrstaID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `vrstakursa` */

insert  into `vrstakursa`(`VrstaID`,`Naziv`) values (1,'Engleski'),(2,'Francuski'),(3,'Nemacki'),(4,'Italijanski'),(5,'Ruski');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
