-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: agencia_viajes
-- ------------------------------------------------------
-- Server version	8.0.20-0ubuntu0.19.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aerolinea`
--

DROP TABLE IF EXISTS `aerolinea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aerolinea` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `aerolinea_UN` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aerolinea`
--

LOCK TABLES `aerolinea` WRITE;
/*!40000 ALTER TABLE `aerolinea` DISABLE KEYS */;
INSERT INTO `aerolinea` VALUES (4,'Air Europa'),(5,'Iberia'),(3,'Rayanair'),(1,'Volotea'),(2,'Vueling');
/*!40000 ALTER TABLE `aerolinea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_ciudad` int NOT NULL,
  `id_aerolinea` int NOT NULL,
  `fecha_booking` datetime NOT NULL,
  `fecha_partida` datetime NOT NULL,
  `cancelado` tinyint(1) NOT NULL DEFAULT '0',
  `fecha_vuelta` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `booking_UN` (`id_usuario`,`fecha_partida`,`id_aerolinea`,`id_ciudad`),
  KEY `booking_FK_1` (`id_ciudad`),
  KEY `booking_FK_2` (`id_aerolinea`),
  CONSTRAINT `booking_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `booking_FK_1` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudad` (`id`),
  CONSTRAINT `booking_FK_2` FOREIGN KEY (`id_aerolinea`) REFERENCES `aerolinea` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,2,59,5,'2020-10-04 18:32:08','2020-12-21 00:00:00',0,'2021-01-21 00:00:00'),(2,2,3,3,'2020-02-01 00:00:00','2020-09-01 00:00:00',0,'2020-09-30 00:00:00'),(3,2,1,1,'2020-02-01 00:00:00','2020-10-01 00:00:00',1,'2020-11-30 00:00:00');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `pais` int NOT NULL,
  `continente` int NOT NULL,
  `portada` varchar(100) NOT NULL DEFAULT 'https://picsum.photos/200/200',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ciudad_UN` (`nombre`,`pais`),
  KEY `ciudad_FK` (`pais`),
  KEY `ciudad_FK_1` (`continente`),
  CONSTRAINT `ciudad_FK` FOREIGN KEY (`pais`) REFERENCES `pais` (`id`),
  CONSTRAINT `ciudad_FK_1` FOREIGN KEY (`continente`) REFERENCES `continente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Berlin',5,5,'berlin.jpg'),(3,'Praga',8,5,'praga.jpg'),(4,'Cracovia',7,5,'cracovia.jpg'),(5,'Viena',6,5,'viena.jpeg'),(7,'Paris',3,5,'paris.webp'),(8,'Lisboa',2,5,'lisboa.jpg'),(10,'Bilbao',1,5,'bilbao.jpg'),(50,'Madrid',1,5,'madrid.jpg'),(55,'Barcelona',1,5,'barcelona.jpg'),(56,'Pekí­n',29,4,'pekin.jpg'),(57,'Shanghai',29,4,'shanghai.jpg'),(58,'Roma',4,5,'roma.jpg'),(59,'Milan',4,5,'milan.jpg');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `continente`
--

DROP TABLE IF EXISTS `continente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `continente` (
  `id` int NOT NULL AUTO_INCREMENT,
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
-- Table structure for table `datos_personales`
--

DROP TABLE IF EXISTS `datos_personales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_personales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `ape1` varchar(100) NOT NULL,
  `ape2` varchar(100) NOT NULL,
  `DNI_NIE` varchar(9) NOT NULL,
  `nacionalidad` int NOT NULL,
  `residencia` varchar(100) NOT NULL,
  `id_tarjeta` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `datos_personales_UN` (`DNI_NIE`),
  KEY `datos_personales_FK` (`id_tarjeta`),
  KEY `datos_personales_FK_1` (`nacionalidad`),
  CONSTRAINT `datos_personales_FK` FOREIGN KEY (`id_tarjeta`) REFERENCES `tarjetas_credito` (`id`),
  CONSTRAINT `datos_personales_FK_1` FOREIGN KEY (`nacionalidad`) REFERENCES `pais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_personales`
--

LOCK TABLES `datos_personales` WRITE;
/*!40000 ALTER TABLE `datos_personales` DISABLE KEYS */;
INSERT INTO `datos_personales` VALUES (1,'Admin','Test1','Test2','12345678T',1,'calle indautxu 23, 4 derecha',1),(2,'Sebastian','Salazar','Taraune','12345679T',1,'calle Balejo 23, 5 derecha',2),(8,'invitado','test','test','testtestR',1,'test test tes',10),(9,'Ander','test','test','56473847T',1,'Moyua 23 1izq',11);
/*!40000 ALTER TABLE `datos_personales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `bandera` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT 'https://picsum.photos/60/40',
  `continente` int NOT NULL DEFAULT '1',
  `nombre_corto` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pais_UN1` (`nombre`,`continente`),
  KEY `pais_FK` (`continente`),
  CONSTRAINT `pais_FK` FOREIGN KEY (`continente`) REFERENCES `continente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'España','https://picsum.photos/200/200',5,'es'),(2,'Portugal','https://picsum.photos/200/200',5,'pt'),(3,'Francia','https://picsum.photos/200/200',5,'fr'),(4,'Italia','https://picsum.photos/200/200',5,'it'),(5,'Alemania','https://picsum.photos/200/200',5,'de'),(6,'Austria','https://picsum.photos/200/200',5,'at'),(7,'Polonia','https://picsum.photos/200/200',5,'pl'),(8,'Chequia','https://picsum.photos/200/200',5,'cz'),(9,'Belgica','https://picsum.photos/200/200',5,'be'),(10,'Bulgaria','https://picsum.photos/200/200',5,'bg'),(11,'Chipre','https://picsum.photos/200/200',5,'cy'),(12,'Crocacia','https://picsum.photos/200/200',5,'hr'),(13,'Dinamarca','https://picsum.photos/200/200',5,'dk'),(14,'Eslovaquia','https://picsum.photos/200/200',5,'sk'),(15,'Eslovenia','https://picsum.photos/200/200',5,'si'),(16,'Estonia','https://picsum.photos/200/200',5,'ee'),(17,'Finlandia','https://picsum.photos/200/200',5,'fi'),(18,'Grecia','https://picsum.photos/200/200',5,'gr'),(19,'hungria','https://picsum.photos/200/200',5,'hu'),(20,'Irlanda','https://picsum.photos/200/200',5,'ie'),(21,'Letonia','https://picsum.photos/200/200',5,'lv'),(22,'Lituania','https://picsum.photos/200/200',5,'lt'),(23,'Luxemburgo','https://picsum.photos/200/200',5,'lu'),(24,'Malta','https://picsum.photos/200/200',5,'mt'),(25,'Paises Bajos','https://picsum.photos/200/200',5,'nl'),(26,'Rumania','https://picsum.photos/200/200',5,'ro'),(27,'Suecia','https://picsum.photos/200/200',5,'se'),(28,'Reino Unido','https://picsum.photos/200/200',5,'gb'),(29,'China','https://picsum.photos/60/40',4,'cn'),(30,'Japon','https://picsum.photos/60/40',4,'jp');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '1: Usuario normal   2: Administrador',
  `nombre` varchar(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (2,'administrador'),(1,'usuario');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjetas_credito`
--

DROP TABLE IF EXISTS `tarjetas_credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjetas_credito` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '11111111111',
  `caducidad` date NOT NULL,
  `num_seguridad` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1234',
  `titular` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N/A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjetas_credito`
--

LOCK TABLES `tarjetas_credito` WRITE;
/*!40000 ALTER TABLE `tarjetas_credito` DISABLE KEYS */;
INSERT INTO `tarjetas_credito` VALUES (1,'12345678912','2022-10-04','714','Admin'),(2,'21987654321','2020-10-04','123','Sebastian'),(10,'11111111111','2020-10-05','1234','N/A'),(11,'11111111111','2020-10-05','1234','N/A');
/*!40000 ALTER TABLE `tarjetas_credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET latin1 NOT NULL,
  `password` varchar(100) CHARACTER SET latin1 NOT NULL,
  `imagen` varchar(100) CHARACTER SET latin1 DEFAULT 'https://picsum.photos/200',
  `id_rol` int NOT NULL DEFAULT '1',
  `id_datos` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuarios_UN` (`nombre`),
  KEY `usuarios_FK` (`id_rol`),
  KEY `usuarios_FK_1` (`id_datos`),
  CONSTRAINT `usuarios_FK` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`),
  CONSTRAINT `usuarios_FK_1` FOREIGN KEY (`id_datos`) REFERENCES `datos_personales` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin@agencia-viajes.es','admin','https://picsum.photos/200',2,1),(2,'sebastian@agencia-viajes.es','sebastian','https://picsum.photos/200',1,2),(3,'invitado@agencia-viajes.es','invitado','https://picsum.photos/200',1,8),(4,'ander@agencia-viajes.es','ander','https://picsum.photos/200',1,9);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-07 19:41:55
