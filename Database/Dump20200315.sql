-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: connectyouth
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `RelatingUserEmail` varchar(50) NOT NULL,
  `RelatedUserEmail` varchar(50) NOT NULL,
  `status` varchar(1) NOT NULL,
  KEY `SenderID_idx` (`RelatingUserEmail`),
  CONSTRAINT `SenderID` FOREIGN KEY (`RelatingUserEmail`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES ('pkeyur888@gmail.com','j@patel','1'),('pkeyur888@gmail.com','karan@gmail.com','0'),('pkeyur888@gmail.com','natasha@gmail.com','1'),('pkeyur888@gmail.com','u@gmail.com','1'),('s@gmail.com','pkeyur888@gmail.com','0'),('kaur@gmail.com','pkeyur888@gmail.com','0'),('m@gmail.com','pkeyur888@gmail.com','0'),('u@gmail.com','j@patel','0'),('u@gmail.com','karan@gmail.com','0');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `postID` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`postID`,`email`),
  KEY `likes_ibfk_2` (`email`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_ibfk_3` FOREIGN KEY (`postID`) REFERENCES `posts` (`postID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `fromUser` varchar(50) NOT NULL,
  `toUser` varchar(50) NOT NULL,
  `message` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `seen` tinyint(1) NOT NULL,
  PRIMARY KEY (`fromUser`,`toUser`,`date`),
  KEY `toUser` (`toUser`),
  KEY `fromUser` (`fromUser`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`fromUser`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`toUser`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('j@patel','pkeyur888@gmail.com','Hello','2020-03-15 17:38:27',0),('natasha@gmail.com','pkeyur888@gmail.com','Hello','2020-03-15 17:38:08',0),('pkeyur888@gmail.com','j@patel','hii','2020-03-15 17:37:23',0),('pkeyur888@gmail.com','natasha@gmail.com','hiii','2020-03-15 17:37:33',0),('pkeyur888@gmail.com','u@gmail.com','Hii','2020-03-15 16:17:53',0),('pkeyur888@gmail.com','u@gmail.com','Nothing Buddy','2020-03-15 16:18:14',0),('pkeyur888@gmail.com','u@gmail.com','where r u?','2020-03-15 16:42:57',0),('pkeyur888@gmail.com','u@gmail.com','hii','2020-03-15 16:50:57',0),('u@gmail.com','pkeyur888@gmail.com','Hii?','2020-03-15 16:17:11',0),('u@gmail.com','pkeyur888@gmail.com','What are u Doing?','2020-03-15 16:17:24',0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `email` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`email`,`type`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `postID` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`postID`),
  KEY `posts_ibfk_1` (`email`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES ('0259602','pkeyur888@gmail.com','Hii Friends','2020-03-15 00:00:00'),('0283003','pkeyur888@gmail.com',' Hello Friends Chai Pilo','2020-03-15 00:00:00'),('067219','u@gmail.com','Vaction in school','2020-03-15 00:00:00'),('0993202','s@gmail.com','Stay Home','2020-03-15 00:00:00');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savedposts`
--

DROP TABLE IF EXISTS `savedposts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savedposts` (
  `postID` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`postID`,`email`),
  KEY `email` (`email`),
  KEY `postID` (`postID`),
  CONSTRAINT `savedposts_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `savedposts_ibfk_2` FOREIGN KEY (`postID`) REFERENCES `posts` (`postID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savedposts`
--

LOCK TABLES `savedposts` WRITE;
/*!40000 ALTER TABLE `savedposts` DISABLE KEYS */;
/*!40000 ALTER TABLE `savedposts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('jay','patel','j@patel','789'),('karan','patel','karan@gmail.com','456'),('rajvir','kaur','kaur@gmail.com','456'),('malav','patel','m@gmail.com','123'),('nisarg','patel','n@gmail.com','123'),('natasha','kumari','natasha@gmail.com','123'),('Keyur','Patel','pkeyur888@gmail.com','123'),('samar','satvir','s@gmail.com','456'),('urvish','Patel','u@gmail.com','456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-15 17:44:04
