-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: microblogging
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `b_l`
--

DROP TABLE IF EXISTS `b_l`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `b_l` (
  `BID` int(11) DEFAULT NULL,
  `LID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_l`
--

LOCK TABLES `b_l` WRITE;
/*!40000 ALTER TABLE `b_l` DISABLE KEYS */;
INSERT INTO `b_l` VALUES (1,9),(2,9),(3,9),(4,9),(5,9),(6,9),(7,9),(8,9),(9,9),(10,9),(11,1),(12,1),(13,1),(14,3),(15,3),(16,3),(17,3),(18,3),(19,4),(20,4),(21,6),(22,6),(23,6),(25,6),(26,6),(27,7),(28,7),(29,7),(30,11),(31,11),(32,11),(33,8),(34,8),(35,8),(36,10),(37,10),(38,5),(39,5),(40,4),(41,4),(42,2),(43,2);
/*!40000 ALTER TABLE `b_l` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fans_3`
--

DROP TABLE IF EXISTS `fans_3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fans_3` (
  `UID` int(11) NOT NULL,
  `NAME` char(40) DEFAULT NULL,
  `SEX` char(4) DEFAULT NULL,
  `BYEAR` int(11) DEFAULT NULL,
  `CITY` char(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fans_3`
--

LOCK TABLES `fans_3` WRITE;
/*!40000 ALTER TABLE `fans_3` DISABLE KEYS */;
INSERT INTO `fans_3` VALUES (1,'诸十二','女',2012,'北京'),(11,'陈十一','男',2011,'武汉'),(9,'郑九','男',2003,'南京');
/*!40000 ALTER TABLE `fans_3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `follow` (
  `UID` int(11) DEFAULT NULL,
  `UIDFLED` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,2),(2,1),(3,1),(3,2),(3,4),(3,11),(4,3),(5,1),(6,1),(7,1),(8,1),(9,2),(10,3),(11,3),(5,2),(6,3),(7,2),(8,3),(9,1),(10,2),(11,4),(12,1),(13,1),(14,1);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `friends` (
  `UID` int(11) DEFAULT NULL,
  `UIDFLED` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,14),(2,13),(3,12),(4,11),(5,10),(6,9),(7,8),(8,7),(9,6),(10,5),(11,4),(12,3),(13,2),(14,1),(1,13),(2,12),(3,11),(4,10),(5,9),(6,8),(7,7),(8,6),(9,5),(10,4),(11,3),(12,2),(13,1),(14,12),(1,11),(2,10),(3,9),(4,8),(5,7),(6,6),(7,5),(8,4),(9,3),(10,2),(11,1),(12,10),(13,9),(14,8),(1,7),(2,6),(3,5),(4,2),(5,1);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `label` (
  `LID` int(11) NOT NULL,
  `LNAME` char(40) DEFAULT NULL,
  PRIMARY KEY (`LID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (1,'文学'),(2,'艺术'),(3,'军事'),(4,'历史'),(5,'地理'),(6,'自然科学'),(7,'工程技术'),(8,'经济'),(9,'教育'),(10,'哲学'),(11,'音乐');
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mblog`
--

DROP TABLE IF EXISTS `mblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mblog` (
  `BID` int(11) NOT NULL,
  `TITLE` char(40) DEFAULT NULL,
  `UID` int(11) DEFAULT NULL,
  `PYEAR` int(11) DEFAULT NULL,
  `PMONTH` int(11) DEFAULT NULL,
  `PDAY` int(11) DEFAULT NULL,
  `CONT` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mblog`
--

LOCK TABLES `mblog` WRITE;
/*!40000 ALTER TABLE `mblog` DISABLE KEYS */;
INSERT INTO `mblog` VALUES (1,'微博1',1,2019,4,18,'内容中包含了“最多地铁站”和“_华中科技大学”两个词		'),(2,'微博2',2,2019,4,18,'华中科技大学是国家教育部直属重点综合性大学，由原华中理工大学、同济医科大学、武汉城市建设学院于2000年5月26日合并成立建设和“985工程”建设高校之一，是首批“双一流”建设高校		'),(3,'微博3',3,2019,4,18,'内容中包含了“最多地铁站”和“_华中科技大学”两个词		'),(4,'微博4',4,2019,4,19,'学校校园占地7000余亩，园内树木葱茏，碧草如茵，环境优雅，景色秀丽，绿化覆盖率72%				'),(5,'微博5',5,2019,4,19,'校学科齐全、结构合理，基本构建起综合性、研究型大学的学科体系	'),(6,'微博6',6,2019,4,19,'学校实施“人才兴校”战略，师资力量雄厚。现有专任教师3400余人	'),(7,'微博7',7,2019,4,19,'学校贯彻建设“学生、学者与学术的大学”的教育思想			'),(8,'微博8',8,2019,4,19,'按照“应用领先、基础突破、协调发展”的科技发展方略		'),(9,'微博9',9,2019,4,19,'学校坚持“服务乃宗旨，贡献即发展”的办学思路			'),(10,'微博10',10,2019,4,19,'学校坚持开放式办学理念，积极开展全方位、多层次的国际交流与合作'),(11,'微博11',11,2019,4,19,'《巢》：“东欧文学的良心”流亡美国的坎坷人生			'),(12,'微博12',12,2019,4,19,'派特·巴克代表作《重生三部曲》：写给激荡时代的悲悯史诗		'),(13,'微博13',13,2019,4,19,'文学巨匠翻译的经典名著：全新汇编《巴金译文集》出版		'),(14,'微博14',14,2019,4,19,'中国某新型核潜艇接受检阅				'),(15,'微博15',1,2019,4,20,'中国核电装机容量升至全球第三				'),(16,'微博16',3,2019,4,20,'海上阅兵活动开始 习近平检阅海上编队				'),(17,'微博17',5,2019,4,20,'斯里兰卡警方：爆炸袭击案死亡人数升至321人			'),(18,'微博18',7,2019,4,20,'辽宁舰升级改造 崭新亮相				'),(19,'微博19',9,2019,4,20,'若是干掉慈禧荣禄，当年康党可赢				'),(20,'微博20',11,2019,4,20,'三省制：唐代中书门下尚书职权之争				'),(21,'微博21',13,2019,4,20,'华人团队突破CAR-T疗法瓶颈，有望变革治疗格局			'),(22,'微博22',2,2019,4,20,'宇宙大爆炸之前究竟发生了什么？				'),(23,'微博23',4,2019,4,20,'“信使”号研究结果表明：水星的确拥有一个固体内核			'),(24,'微博24',6,2019,4,20,'科学家揭秘巨石阵 DNA测序显示当时为父系社会			'),(25,'微博25',8,2019,4,20,'见证人类第一张黑洞照片：宇宙“巨兽”露真容			'),(26,'微博26',10,2019,4,20,'奇妙的天体：“致密致迷”的中子星				'),(27,'微博27',12,2019,4,20,'特斯拉蔚来接连报“火警” 电动车安全问题再成焦点			'),(28,'微博28',14,2019,4,21,'微软疑似改变计划：放弃移除Windows 10“画图”程序			'),(29,'微博29',7,2019,4,21,'工信部：将在超过300个城市部署千兆宽带网				'),(30,'微博30',6,2019,4,21,'中国第一代钢琴家巫漪丽逝世				'),(31,'微博31',5,2019,4,21,'同一首歌10大巨星经典演唱会				'),(32,'微博32',4,2019,4,21,'张杰上海演唱会万人蹦迪				'),(33,'微博33',3,2019,4,21,'中央财经委员会第四次会议释放重要信号				'),(34,'微博34',2,2019,4,21,'人社部回应“2035年养老金将用光”				'),(35,'微博35',1,2019,4,21,'新华社：证券法修订草案设科创板注册制专节				'),(36,'微博36',6,2019,4,21,'十个哲学思想,让思想更有深度				'),(37,'微博37',5,2019,4,21,'黑格尔与精神现象学				'),(38,'微博38',4,2019,4,21,'秘境寻踪·消逝的“天梯”				'),(39,'微博39',3,2019,4,21,'航拍中国第二季福建				'),(40,'微博40',2,2019,4,21,'中国历史朝代公元对照简表				'),(41,'微博41',1,2019,4,21,'西晋王朝短暂的大一统,却又迅速灭亡的原因,更值得我们去探究		'),(42,'微博42',1,2019,4,21,'意大利画家达芬奇(Leonardo da Vinci)介绍及作品欣赏		'),(43,'微博43',2,2019,4,21,'西班牙画家巴勃罗·毕加索介绍及作品欣赏				');
/*!40000 ALTER TABLE `mblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub`
--

DROP TABLE IF EXISTS `sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sub` (
  `UID` int(11) DEFAULT NULL,
  `LID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub`
--

LOCK TABLES `sub` WRITE;
/*!40000 ALTER TABLE `sub` DISABLE KEYS */;
INSERT INTO `sub` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,1),(13,2),(14,3),(1,4),(2,5),(3,6),(4,7),(5,8),(6,9),(7,10),(8,11),(9,1),(10,2),(11,3),(12,4),(13,5),(14,6),(1,7),(2,8),(3,9),(4,10),(5,11),(6,1),(7,2),(8,3),(9,4),(10,5),(11,6),(12,7),(13,8),(14,9);
/*!40000 ALTER TABLE `sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thumb`
--

DROP TABLE IF EXISTS `thumb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `thumb` (
  `UID` int(11) DEFAULT NULL,
  `BID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thumb`
--

LOCK TABLES `thumb` WRITE;
/*!40000 ALTER TABLE `thumb` DISABLE KEYS */;
INSERT INTO `thumb` VALUES (1,7),(2,8),(3,9),(4,10),(5,11),(6,12),(7,13),(8,14),(9,15),(10,16),(11,17),(12,18),(13,19),(14,20),(1,21),(2,22),(3,23),(4,24),(5,25),(6,26),(7,27),(8,28),(9,29),(10,30),(11,31),(12,32),(13,33),(14,34),(1,35),(2,36),(3,37),(4,38),(5,39),(6,40),(7,41),(8,42),(9,43),(10,9),(11,10),(12,11),(13,12),(14,13);
/*!40000 ALTER TABLE `thumb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topday`
--

DROP TABLE IF EXISTS `topday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `topday` (
  `TYEAR` int(11) DEFAULT NULL,
  `TMONTH` int(11) DEFAULT NULL,
  `TDAY` int(11) DEFAULT NULL,
  `BID` int(11) DEFAULT NULL,
  `TNO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topday`
--

LOCK TABLES `topday` WRITE;
/*!40000 ALTER TABLE `topday` DISABLE KEYS */;
INSERT INTO `topday` VALUES (2019,4,18,1,1),(2019,4,18,2,2),(2019,4,18,3,3),(2019,4,19,5,1),(2019,4,19,6,2),(2019,4,19,7,3),(2019,4,19,8,4),(2019,4,19,9,5),(2019,4,19,10,6),(2019,4,19,11,7),(2019,4,19,12,8),(2019,4,19,13,9),(2019,4,19,14,10),(2019,4,20,15,1),(2019,4,20,16,2),(2019,4,20,17,3),(2019,4,20,18,4),(2019,4,20,19,5),(2019,4,20,20,6),(2019,4,20,21,7),(2019,4,20,22,8),(2019,4,20,23,9),(2019,4,20,24,10),(2019,4,21,28,1),(2019,4,21,29,2),(2019,4,21,30,3),(2019,4,21,31,4),(2019,4,21,32,5),(2019,4,21,33,6),(2019,4,21,34,7),(2019,4,21,35,8),(2019,4,21,36,9),(2019,4,21,37,10);
/*!40000 ALTER TABLE `topday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `UID` int(11) NOT NULL,
  `NAME` char(40) DEFAULT NULL,
  `SEX` char(4) DEFAULT NULL,
  `BYEAR` int(11) DEFAULT NULL,
  `CITY` char(40) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'赵一','男',1967,'武汉'),(2,'钱二','女',1968,'上海'),(3,'张三','男',1999,'南京'),(4,'李四','女',2000,'北京'),(5,'王五','男',2001,'武汉'),(6,'孙六','女',2001,'武汉'),(7,'周七','男',2002,'武汉'),(8,'吴八','男',2002,'杭州'),(9,'郑九','男',2003,'南京'),(10,'冯十','女',2004,'上海'),(11,'陈十一','男',2011,'武汉'),(12,'诸十二','女',2012,'北京'),(13,'卫十三','女',2011,'上海'),(14,'蒋十四','女',2011,'武汉');
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

-- Dump completed on 2019-04-30  1:56:29
