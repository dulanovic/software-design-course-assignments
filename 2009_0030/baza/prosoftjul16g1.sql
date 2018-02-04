/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftjul16g1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftjul16g1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftjul16g1`;

/*Table structure for table `reprezentacija` */

DROP TABLE IF EXISTS `reprezentacija`;

CREATE TABLE `reprezentacija` (
  `ReprezentacijaID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ReprezentacijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reprezentacija` */

insert  into `reprezentacija`(`ReprezentacijaID`,`Naziv`) values (1,'Italija'),(2,'Belgija'),(3,'Irska'),(4,'Svedska'),(5,'Madjarska'),(6,'Island'),(7,'Portugal'),(8,'Austrija');

/*Table structure for table `utakmica` */

DROP TABLE IF EXISTS `utakmica`;

CREATE TABLE `utakmica` (
  `UtakmicaID` int(11) NOT NULL,
  `Grupa` varchar(1) DEFAULT NULL,
  `DomacinID` int(11) DEFAULT NULL,
  `GostID` int(11) DEFAULT NULL,
  `GolovaDomacin` int(11) DEFAULT NULL,
  `GolovaGost` int(11) DEFAULT NULL,
  PRIMARY KEY (`UtakmicaID`),
  KEY `DomacinID` (`DomacinID`),
  KEY `GostID` (`GostID`),
  CONSTRAINT `utakmica_ibfk_1` FOREIGN KEY (`DomacinID`) REFERENCES `reprezentacija` (`ReprezentacijaID`),
  CONSTRAINT `utakmica_ibfk_2` FOREIGN KEY (`GostID`) REFERENCES `reprezentacija` (`ReprezentacijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `utakmica` */

insert  into `utakmica`(`UtakmicaID`,`Grupa`,`DomacinID`,`GostID`,`GolovaDomacin`,`GolovaGost`) values (1,'E',3,4,NULL,NULL),(2,'E',2,1,NULL,NULL),(3,'E',1,4,NULL,NULL),(4,'E',2,3,NULL,NULL),(5,'E',1,3,NULL,NULL),(6,'E',4,2,NULL,NULL),(7,'F',8,5,NULL,NULL),(8,'F',7,6,NULL,NULL),(9,'F',6,5,NULL,NULL),(10,'F',7,8,NULL,NULL),(11,'F',6,8,NULL,NULL),(12,'F',5,7,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
