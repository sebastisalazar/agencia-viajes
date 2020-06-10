-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: agencia_viajes
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Current Database: `agencia_viajes`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `agencia_viajes` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `agencia_viajes`;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `pais` int(11) NOT NULL,
  `continente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ciudad_UN` (`nombre`),
  KEY `ciudad_FK` (`pais`),
  KEY `ciudad_FK_1` (`continente`),
  CONSTRAINT `ciudad_FK` FOREIGN KEY (`pais`) REFERENCES `pais` (`id`),
  CONSTRAINT `ciudad_FK_1` FOREIGN KEY (`continente`) REFERENCES `continente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Berlin',5,5),(2,'Madrid',1,5),(3,'Praga',8,5),(4,'Cracovia',7,5),(5,'Viena',6,5),(7,'Paris',3,5),(8,'Lisboa',2,5),(9,'Milan',4,5);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `continente`
--

DROP TABLE IF EXISTS `continente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `continente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `continente_UN` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `continente`
--

LOCK TABLES `continente` WRITE;
/*!40000 ALTER TABLE `continente` DISABLE KEYS */;
INSERT INTO `continente` VALUES (1,'Africa'),(2,'América'),(3,'Antártida'),(4,'Asia'),(5,'Europa'),(6,'Oceanía');
/*!40000 ALTER TABLE `continente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `bandera` varchar(100) COLLATE latin1_spanish_ci DEFAULT 'https://picsum.photos/60/40',
  `continente` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pais_UN` (`nombre`),
  KEY `pais_FK` (`continente`),
  CONSTRAINT `pais_FK` FOREIGN KEY (`continente`) REFERENCES `continente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'España','https://picsum.photos/60/40',5),(2,'Portugal','https://picsum.photos/60/40',5),(3,'Francia','https://picsum.photos/60/40',5),(4,'Italia','https://picsum.photos/60/40',5),(5,'Alemania','https://picsum.photos/60/40',5),(6,'Austria','https://picsum.photos/60/40',5),(7,'Polonia','https://picsum.photos/60/40',5),(8,'Chequia','https://picsum.photos/60/40',5);
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-10 18:28:07
