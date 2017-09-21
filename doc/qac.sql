-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: qac
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '缂栧彿',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '瀵嗙爜',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '鐢ㄦ埛鍚',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199620 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (199619,'199619','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '绛旀?缂栧彿',
  `question_id` int(11) NOT NULL COMMENT '闂??缂栧彿',
  `author_id` int(11) NOT NULL COMMENT '浣滆?缂栧彿',
  `content` varchar(10000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '绛旀?鍐呭?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  `up` int(11) DEFAULT '0' COMMENT '琚?禐鏁',
  `down` int(11) DEFAULT '0' COMMENT '琚?俯鏁',
  `status` smallint(6) DEFAULT '0' COMMENT '鐘舵?',
  `status_remarks` int(11) DEFAULT '-1' COMMENT '锛',
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,1,1,'2334 Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue ','2017-09-20 12:13:52',0,0,0,-1),(2,2,1,'02Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus','2017-09-20 13:12:02',0,0,0,-1),(3,3,1,'03Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus','2017-09-20 13:12:35',0,0,0,-1);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_collect`
--

DROP TABLE IF EXISTS `answer_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_collect` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `answer_id` int(11) NOT NULL COMMENT '绛旀?缂栧彿',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鏀惰棌鏃堕棿',
  PRIMARY KEY (`user_id`,`answer_id`),
  KEY `answer_id` (`answer_id`),
  CONSTRAINT `answer_collect_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_collect_ibfk_2` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_collect`
--

LOCK TABLES `answer_collect` WRITE;
/*!40000 ALTER TABLE `answer_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_comment`
--

DROP TABLE IF EXISTS `answer_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '绛旀?璇勮?缂栧彿',
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `answer_id` int(11) NOT NULL COMMENT '绛旀?缂栧彿',
  `content` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鍐呭?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  `up` int(11) DEFAULT '0' COMMENT '琚?禐鏁',
  `down` int(11) DEFAULT '0' COMMENT '琚?俯鏁',
  `reply_comment_id` int(11) DEFAULT NULL COMMENT '鍥炲?璇勮?缂栧彿',
  `status` int(11) DEFAULT '0' COMMENT '璇勮?鐘舵?锛岄粯璁や负0锛?1涓哄垹闄',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `user_id` (`user_id`),
  KEY `answer_id` (`answer_id`),
  KEY `reply_comment_id` (`reply_comment_id`),
  CONSTRAINT `answer_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_comment_ibfk_2` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_comment_ibfk_3` FOREIGN KEY (`reply_comment_id`) REFERENCES `answer_comment` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_comment`
--

LOCK TABLES `answer_comment` WRITE;
/*!40000 ALTER TABLE `answer_comment` DISABLE KEYS */;
INSERT INTO `answer_comment` VALUES (5,1,1,'new comment 01','2017-09-21 07:50:51',0,0,NULL,0),(6,1,1,'new comment 02','2017-09-21 07:51:48',0,0,NULL,0),(8,1,1,'new comment 03','2017-09-21 10:10:21',0,0,NULL,0);
/*!40000 ALTER TABLE `answer_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_report`
--

DROP TABLE IF EXISTS `answer_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_report` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `answer_id` int(11) NOT NULL COMMENT '绛旀?缂栧彿',
  `report_reason_id` int(11) NOT NULL COMMENT '涓炬姤鍘熷洜缂栧彿',
  `remarks` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '鍐呭?',
  `status` tinyint(1) DEFAULT '0' COMMENT '鐘舵?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '涓炬姤鏃堕棿',
  PRIMARY KEY (`user_id`,`answer_id`),
  KEY `answer_id` (`answer_id`),
  KEY `report_reason_id` (`report_reason_id`),
  CONSTRAINT `answer_report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_report_ibfk_2` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_report_ibfk_3` FOREIGN KEY (`report_reason_id`) REFERENCES `report_reason` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_report`
--

LOCK TABLES `answer_report` WRITE;
/*!40000 ALTER TABLE `answer_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_up_down`
--

DROP TABLE IF EXISTS `answer_up_down`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_up_down` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `answer_id` int(11) NOT NULL COMMENT '绛旀?缂栧彿',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  `is_up` int(11) DEFAULT '0' COMMENT '鏄?惁琚?禐鎴栬?韪?1涓鸿俯锛?涓洪粯璁わ紝1涓鸿禐',
  PRIMARY KEY (`user_id`,`answer_id`),
  KEY `answer_id` (`answer_id`),
  CONSTRAINT `answer_up_down_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_up_down_ibfk_2` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_up_down`
--

LOCK TABLES `answer_up_down` WRITE;
/*!40000 ALTER TABLE `answer_up_down` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_up_down` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鏂囩珷缂栧彿',
  `author_id` int(11) NOT NULL COMMENT '浣滆?缂栧彿',
  `title` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鏍囬?',
  `content` varchar(10000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鍐呭?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍒涘缓鏃堕棿',
  `browse_num` int(11) DEFAULT '0' COMMENT '琚?祻瑙堟暟',
  `up` int(11) DEFAULT '0' COMMENT '琚?禐鏁',
  `down` int(11) DEFAULT '0' COMMENT '琚?俯鏁',
  `status` smallint(6) DEFAULT '0' COMMENT '鏂囩珷鐘舵?',
  `status_remarks` int(11) DEFAULT '-1' COMMENT '榛樿?涓?1',
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (2,1,'Article01_m','### Intro\nGo ahead, play around with the editor! Be sure to check out **bold** and *italic* styling, or even [links](https://google.com). You can type the Markdown syntax, use the toolbar, or use shortcuts like `cmd-b` or `ctrl-b`.\n\n### Lists\nUnordered lists can be started using the toolbar or by typing `* `, `- `, or `+ `. Ordered lists can be started by typing `1. `.\n\n#### Unordered\n* Lists are a piece of cake\n* They even auto continue as you type\n* A double enter will end them\n* Tabs and shift-tabs work too\n\n#### Ordered\n1. Numbered lists...\n2. ...work too!\n\n### What about images?\n![Yes](https://i.imgur.com/sZlktY7.png)','2017-09-20 06:41:41',0,0,0,0,-1),(3,1,'Test : add labels','Test: add labels.\n\nWe did a lot of work in laying a foundation for the app in the previous steps, so now we\'ll do something simple; we will add full-text search (yes, it will be simple!). We will also write an end-to-end (E2E) test, because a good E2E test is a good friend. It stays with your app, keeps an eye on it, and quickly detects regressions.\n\nWe did a lot of work in laying a foundation for the app in the previous steps, so now we\'ll do something simple; we will add full-text search (yes, it will be simple!). We will also write an end-to-end (E2E) test, because a good E2E test is a good friend. It stays with your app, keeps an eye on it, and quickly detects regressions.\n\nWe did a lot of work in laying a foundation for the app in the previous steps, so now we\'ll do something simple; we will add full-text search (yes, it will be simple!). We will also write an end-to-end (E2E) test, because a good E2E test is a good friend. It stays with your app, keeps an eye on it, and quickly detects regressions.','2017-09-21 06:14:27',0,0,0,0,-1),(9,1,'add label test 03','labelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearch\n\nlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearchlabelInputSearch','2017-09-21 07:02:23',0,0,0,0,-1),(10,1,'fsdfdsfdsf','fdsfsdfafsdf','2017-09-21 07:03:16',0,0,0,0,-1);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_collect`
--

DROP TABLE IF EXISTS `article_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_collect` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `article_id` int(11) NOT NULL COMMENT '鏂囩珷缂栧彿',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  PRIMARY KEY (`user_id`,`article_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `article_collect_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_collect_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_collect`
--

LOCK TABLES `article_collect` WRITE;
/*!40000 ALTER TABLE `article_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_comment`
--

DROP TABLE IF EXISTS `article_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鏂囩珷璇勮?缂栧彿',
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `article_id` int(11) NOT NULL COMMENT '绛旀?缂栧彿',
  `content` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '璇勮?鍐呭?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  `up` int(11) DEFAULT '0' COMMENT '琚?禐鏁',
  `down` int(11) DEFAULT '0' COMMENT '琚?俯鏁',
  `reply_comment_id` int(11) DEFAULT NULL COMMENT '鍥炲?缂栧彿',
  `status` int(11) DEFAULT '0' COMMENT '璇勮?鐘舵?锛岄粯璁や负0锛?1涓哄垹闄',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `article_id` (`article_id`),
  KEY `reply_comment_id` (`reply_comment_id`),
  CONSTRAINT `article_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_comment_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_comment_ibfk_3` FOREIGN KEY (`reply_comment_id`) REFERENCES `article_comment` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comment`
--

LOCK TABLES `article_comment` WRITE;
/*!40000 ALTER TABLE `article_comment` DISABLE KEYS */;
INSERT INTO `article_comment` VALUES (1,1,2,'new comment 01','2017-09-21 08:32:05',0,0,NULL,0),(2,1,2,'new comment 02','2017-09-21 08:44:22',0,0,NULL,0),(3,1,2,'new comment 03','2017-09-21 08:45:55',0,0,NULL,0);
/*!40000 ALTER TABLE `article_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_label`
--

DROP TABLE IF EXISTS `article_label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_label` (
  `article_id` int(11) NOT NULL COMMENT '鏂囩珷缂栧彿',
  `label_id` int(11) NOT NULL COMMENT '鏍囩?缂栧彿',
  PRIMARY KEY (`label_id`,`article_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `article_label_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_label_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_label`
--

LOCK TABLES `article_label` WRITE;
/*!40000 ALTER TABLE `article_label` DISABLE KEYS */;
INSERT INTO `article_label` VALUES (2,1),(2,5),(2,16),(9,2),(9,5),(9,7),(9,8),(9,9),(9,11),(9,15),(10,4),(10,15);
/*!40000 ALTER TABLE `article_label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_report`
--

DROP TABLE IF EXISTS `article_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_report` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `article_id` int(11) NOT NULL COMMENT '鏂囩珷缂栧彿',
  `report_reason_id` int(11) NOT NULL COMMENT '涓炬姤鍘熷洜缂栧彿',
  `remarks` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '琛ュ厖鍐呭?',
  `status` tinyint(1) DEFAULT '0' COMMENT '鐘舵?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  PRIMARY KEY (`user_id`,`article_id`),
  KEY `article_id` (`article_id`),
  KEY `report_reason_id` (`report_reason_id`),
  CONSTRAINT `article_report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_report_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_report_ibfk_3` FOREIGN KEY (`report_reason_id`) REFERENCES `report_reason` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_report`
--

LOCK TABLES `article_report` WRITE;
/*!40000 ALTER TABLE `article_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_up_down`
--

DROP TABLE IF EXISTS `article_up_down`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_up_down` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `article_id` int(11) NOT NULL COMMENT '鏂囩珷缂栧彿',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  `is_up` int(11) DEFAULT '0' COMMENT '鏄?惁琚?禐鎴栬?韪?1涓鸿俯锛?涓洪粯璁わ紝1涓鸿禐',
  PRIMARY KEY (`user_id`,`article_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `article_up_down_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_up_down_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_up_down`
--

LOCK TABLES `article_up_down` WRITE;
/*!40000 ALTER TABLE `article_up_down` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_up_down` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `label` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鏍囩?缂栧彿锛岃嚜鍔ㄧ敓鎴',
  `title` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鏍囩?鍐呭?',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (1,'java'),(2,'node.js'),(3,'php'),(4,'c++'),(5,'angular.js'),(6,'github'),(7,'bootstrap'),(8,'javascript'),(9,'typescript'),(10,'c#'),(11,'ubuntu'),(12,'windows'),(13,'mac os'),(14,'python'),(15,'shell'),(16,'c/c++'),(17,'matlab'),(18,'git');
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '閫氱煡缂栧彿',
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `content` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鍐呭?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '闂??缂栧彿',
  `author_id` int(11) NOT NULL COMMENT '浣滆?缂栧彿',
  `title` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鏂囩珷鏍囬?',
  `content` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鏂囩珷鍐呭?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  `browse_num` int(11) DEFAULT '0' COMMENT '琚?祻瑙堟暟',
  `up` int(11) DEFAULT '0' COMMENT '琚?禐鏁',
  `down` int(11) DEFAULT '0' COMMENT '琚?俯鏁',
  `status` smallint(6) DEFAULT '0' COMMENT '鐘舵?锛氳〃绀鸿?闂??鐩?墠鐨勭姸鎬侊紝濡傗?姝ｅ父鈥濄?鈥滆?鍏抽棴鈥溿?鈥濊?鍒犻櫎鈥滅瓑銆',
  `status_remarks` int(11) DEFAULT '-1' COMMENT '锛',
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,1,'question_title_m','233 Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus','2017-09-20 06:25:55',0,0,0,0,-1),(2,1,'Question02','Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus','2017-09-20 06:30:14',0,0,0,0,-1),(3,1,'Question03','Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Vivamus sagittis lacus vel augue laoreet rutrum faucibus','2017-09-20 06:30:45',0,0,0,0,-1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_follow`
--

DROP TABLE IF EXISTS `question_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_follow` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `question_id` int(11) NOT NULL COMMENT '闂??缂栧彿',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  PRIMARY KEY (`user_id`,`question_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `question_follow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_follow_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_follow`
--

LOCK TABLES `question_follow` WRITE;
/*!40000 ALTER TABLE `question_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_label`
--

DROP TABLE IF EXISTS `question_label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_label` (
  `question_id` int(11) NOT NULL COMMENT '闂??缂栧彿',
  `label_id` int(11) NOT NULL COMMENT '鏍囩?缂栧彿',
  PRIMARY KEY (`question_id`,`label_id`),
  KEY `label_id` (`label_id`),
  CONSTRAINT `question_label_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_label_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_label`
--

LOCK TABLES `question_label` WRITE;
/*!40000 ALTER TABLE `question_label` DISABLE KEYS */;
INSERT INTO `question_label` VALUES (1,1),(1,2),(1,5),(1,7),(1,8),(1,9),(1,14);
/*!40000 ALTER TABLE `question_label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_report`
--

DROP TABLE IF EXISTS `question_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_report` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `question_id` int(11) NOT NULL COMMENT '闂??缂栧彿',
  `report_reason_id` int(11) NOT NULL COMMENT '涓炬姤鍘熷洜缂栧彿',
  `remarks` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '鐘舵?',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  PRIMARY KEY (`user_id`,`question_id`),
  KEY `question_id` (`question_id`),
  KEY `report_reason_id` (`report_reason_id`),
  CONSTRAINT `question_report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_report_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_report_ibfk_3` FOREIGN KEY (`report_reason_id`) REFERENCES `report_reason` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_report`
--

LOCK TABLES `question_report` WRITE;
/*!40000 ALTER TABLE `question_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_up_down`
--

DROP TABLE IF EXISTS `question_up_down`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_up_down` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `question_id` int(11) NOT NULL COMMENT '闂??缂栧彿',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  `is_up` int(11) DEFAULT '0' COMMENT '鏄?惁琚?禐鎴栬?韪?1涓鸿俯锛?涓洪粯璁わ紝1涓鸿禐',
  PRIMARY KEY (`user_id`,`question_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `question_up_down_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_up_down_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_up_down`
--

LOCK TABLES `question_up_down` WRITE;
/*!40000 ALTER TABLE `question_up_down` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_up_down` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_reason`
--

DROP TABLE IF EXISTS `report_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '涓炬姤鍘熷洜缂栧彿',
  `title` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '鏍囬?',
  `content` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '涓炬姤鍐呭?',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_reason`
--

LOCK TABLES `report_reason` WRITE;
/*!40000 ALTER TABLE `report_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鐢ㄦ埛缂栧彿锛岃嚜鍔ㄧ敓鎴',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '濮撳悕',
  `email` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '閭??',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '瀵嗙爜',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍒涘缓鏃堕棿,鑷?姩鑾峰彇',
  `sex` tinyint(1) DEFAULT NULL COMMENT '鎬у埆',
  `short_intro` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '涓?汉绛惧悕',
  `intro` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '涓?汉浠嬬粛',
  `college` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '澶у?',
  `major` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '涓撲笟',
  `avatar` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '澶村儚锛屽彲浠ヨ?缃?竴涓?粯璁ゅご鍍',
  `prestige` int(11) DEFAULT '0' COMMENT '澹版湜鍊硷紝榛樿?涓?',
  `browse_num` int(11) DEFAULT '0' COMMENT '琚?祻瑙堟暟',
  `status` smallint(6) DEFAULT '0' COMMENT '鏄?惁琚?皝绂佺姸鎬侊紝0涓烘病鏈夊皝绂侊紝1涓鸿?灏佺?',
  `unban_datetime` datetime DEFAULT NULL COMMENT '瑙ｅ皝鏃堕棿',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hugohoo','hughoo@outlook.com','123456','2017-09-19 16:00:00',0,'Intergrated delay cancer.','Intergrated delay cancer..','郑州大学','软件工程','khalil.png',0,16,0,NULL),(2,'hugohoo2','hughoo2@outlook.com','pply419zz!','2017-09-19 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL),(4,'hugohoo3','hughoo3@outlook.com','123456','2017-09-19 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL),(10,'hugohoo4','hughoo4@outlook.com','123456','2017-09-19 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL),(11,'ooho','hughoo5@outlook.com','123456','2017-09-20 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL),(12,'ooho','hughoo6@outlook.com','123456','2017-09-20 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL),(13,'ooho02','hugho@qq.com','123456','2017-09-20 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL),(14,'oho03','hfljsadlkf@fjsdl.com','123456','2017-09-20 16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_follow`
--

DROP TABLE IF EXISTS `user_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_follow` (
  `user_id` int(11) NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `follow_id` int(11) NOT NULL COMMENT '琚?叧娉ㄧ敤鎴风紪鍙',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '鍙戣〃鏃堕棿',
  PRIMARY KEY (`user_id`,`follow_id`),
  KEY `follow_id` (`follow_id`),
  CONSTRAINT `user_follow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_follow_ibfk_2` FOREIGN KEY (`follow_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_follow`
--

LOCK TABLES `user_follow` WRITE;
/*!40000 ALTER TABLE `user_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_follow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-21 21:21:41
