-- MySQL dump 10.13  Distrib 5.6.15, for Win32 (x86)
--
-- Host: localhost    Database: askisi9
-- ------------------------------------------------------
-- Server version	5.6.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anafores`
--

DROP TABLE IF EXISTS `anafores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anafores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provlima` varchar(100) DEFAULT NULL,
  `sxolia` text,
  `odigos_id` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anafores`
--

LOCK TABLES `anafores` WRITE;
/*!40000 ALTER TABLE `anafores` DISABLE KEYS */;
INSERT INTO `anafores` VALUES (3,'Λάστιχο','epatha lastixo',45,'2013-12-22 14:16:24'),(4,'Χρειάζεται Service','to leoforeio xreiazetai service',45,'2013-12-22 14:16:35'),(5,'Τρακάρισμα','τράκαρα',45,'2013-12-22 14:16:41');
/*!40000 ALTER TABLE `anafores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anefodiasmoi`
--

DROP TABLE IF EXISTS `anefodiasmoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anefodiasmoi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `litra` smallint(6) DEFAULT NULL,
  `oxima_id` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anefodiasmoi`
--

LOCK TABLES `anefodiasmoi` WRITE;
/*!40000 ALTER TABLE `anefodiasmoi` DISABLE KEYS */;
INSERT INTO `anefodiasmoi` VALUES (1,70,703,'2013-12-21 10:05:53'),(2,27,1,'2013-12-21 10:05:53'),(3,1,1,'2013-12-21 10:05:53'),(4,123,432,'2013-12-21 18:46:53'),(5,11,703,'2013-12-21 18:46:59'),(6,20,703,'2013-12-22 14:09:43');
/*!40000 ALTER TABLE `anefodiasmoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `odigoi`
--

DROP TABLE IF EXISTS `odigoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `odigoi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `onoma` varchar(40) DEFAULT NULL,
  `eponimo` varchar(40) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `oxima` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `odigoi`
--

LOCK TABLES `odigoi` WRITE;
/*!40000 ALTER TABLE `odigoi` DISABLE KEYS */;
INSERT INTO `odigoi` VALUES (45,'odigos','odigos','odigos','odigos',432),(46,'kostas','kostas','kostas','kostas',703),(47,'panos','atha','panos','panos',432),(48,'aris','aris','aris','arisa',704),(51,'lewnidas','lew','lew','lewlew',800);
/*!40000 ALTER TABLE `odigoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oximata`
--

DROP TABLE IF EXISTS `oximata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oximata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `diadromi` varchar(100) DEFAULT NULL,
  `lat` varchar(30) DEFAULT NULL,
  `lon` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=801 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oximata`
--

LOCK TABLES `oximata` WRITE;
/*!40000 ALTER TABLE `oximata` DISABLE KEYS */;
INSERT INTO `oximata` VALUES (432,'asdasxa','37.973263','23.740768'),(703,'nikaia-peiraias','37.973263','23.740768'),(704,'nikaia-peiraias','37.973263','23.740768'),(800,'athina','37.973263','23.740768');
/*!40000 ALTER TABLE `oximata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `privilages` varchar(40) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'ipalilos','ipalilos','ipalilos'),(3,'diaxiristis','diaxiristis','diaxiristis');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-22 16:26:49
