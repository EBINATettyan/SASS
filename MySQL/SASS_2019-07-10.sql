# ************************************************************
# Sequel Pro SQL dump
# バージョン 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# ホスト: 127.0.0.1 (MySQL 5.7.10)
# データベース: SASS
# 作成時刻: 2019-07-10 08:31:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# テーブルのダンプ date
# ------------------------------------------------------------

DROP TABLE IF EXISTS `date`;

CREATE TABLE `date` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `time` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `date` WRITE;
/*!40000 ALTER TABLE `date` DISABLE KEYS */;

INSERT INTO `date` (`id`, `title`, `time`)
VALUES
	(1,'test1','111111\n'),
	(2,'test2','222222'),
	(3,'test3','333333'),
	(4,'test4','444444'),
	(5,'test5','5555555'),
	(6,'test6','666666'),
	(7,'test7','7777777'),
	(8,'test8','888888');

/*!40000 ALTER TABLE `date` ENABLE KEYS */;
UNLOCK TABLES;


# テーブルのダンプ files
# ------------------------------------------------------------

DROP TABLE IF EXISTS `files`;

CREATE TABLE `files` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` text,
  `file_directory` text,
  `file_name` text,
  `date_id` int(11) DEFAULT NULL,
  `upload_date` text,
  `kind_id` int(11) DEFAULT NULL,
  `comp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;

INSERT INTO `files` (`id`, `student_id`, `file_directory`, `file_name`, `date_id`, `upload_date`, `kind_id`, `comp_id`)
VALUES
	(1,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/1','2019071015553030ss.png',1,'2019/07/10/15:55',1,1),
	(2,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/1','2019071015553737ss.png',1,'2019/07/10/15:55',1,2),
	(3,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/2','2019071015555353ss.png',2,'2019/07/10/15:55',1,1),
	(4,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/2','2019071015560000ss.png',2,'2019/07/10/15:56',1,2),
	(5,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/3','2019071015563737ss.png',3,'2019/07/10/15:56',1,1),
	(6,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/3','2019071015564444ss.png',3,'2019/07/10/15:56',1,2),
	(7,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/4','2019071015570707ss.png',4,'2019/07/10/15:57',1,1),
	(8,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/4','2019071015571616ss.png',4,'2019/07/10/15:57',1,2),
	(10,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/5','2019071016021111ss.png',5,'2019/07/10/16:02',1,1),
	(11,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/5','2019071016025757dd.png',5,'2019/07/10/16:02',1,2),
	(12,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/6','2019071016120000dd.png',6,'2019/07/10/16:12',1,1),
	(13,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/6','2019071016122727dd.png',6,'2019/07/10/16:12',1,1),
	(14,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/6','2019071016124646dd.png',6,'2019/07/10/16:12',1,2),
	(15,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/7','2019071016142525',7,'2019/07/10/16:14',0,1),
	(16,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/7','2019071016144646',7,'2019/07/10/16:14',0,2),
	(18,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/8','2019071017093636',8,'2019/07/10/17:09',0,1),
	(19,'m183302s','/Users/csuser/Desktop/sassFile/m183302s/8','2019071017100404dd.png',8,'2019/07/10/17:10',1,2);

/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;


# テーブルのダンプ self_assessment_data
# ------------------------------------------------------------

DROP TABLE IF EXISTS `self_assessment_data`;

CREATE TABLE `self_assessment_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` text NOT NULL,
  `date_id` int(11) NOT NULL,
  `comp1` int(11) NOT NULL,
  `comp2` int(11) NOT NULL,
  `comp1_comment` text COMMENT '異常値とされたとき入力するコメント',
  `comp2_comment` text COMMENT '異常値とされたとき入力するコメント',
  `comp1_check` int(11) DEFAULT NULL COMMENT '異常値（下）と認識されたら1,異常値（上）と認識されたら2,それ以外は0',
  `comp2_check` int(11) DEFAULT NULL COMMENT '異常値（上）と認識されたら1,異常値（下）と認識されたら2,それ以外は0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `self_assessment_data` WRITE;
/*!40000 ALTER TABLE `self_assessment_data` DISABLE KEYS */;

INSERT INTO `self_assessment_data` (`id`, `student_id`, `date_id`, `comp1`, `comp2`, `comp1_comment`, `comp2_comment`, `comp1_check`, `comp2_check`)
VALUES
	(1,'m183302s',1,2,1,NULL,NULL,0,0),
	(2,'m183302s',2,3,3,NULL,NULL,0,0),
	(3,'m183302s',3,1,4,NULL,NULL,0,0),
	(4,'m183302s',4,2,2,NULL,NULL,0,0),
	(5,'m183302s',5,3,5,'〜〜と考えたから','ｃｄｃｄｃｄｃｄｃｄ',2,2),
	(6,'m183302s',6,1,3,'','ｙ７ｙ７７ｙ７ｙ７',0,2),
	(7,'m183302s',7,2,3,'','cdcdcd',0,2),
	(8,'m183302s',8,8,10,'','kkkkk',2,2);

/*!40000 ALTER TABLE `self_assessment_data` ENABLE KEYS */;
UNLOCK TABLES;


# テーブルのダンプ students
# ------------------------------------------------------------

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;

INSERT INTO `students` (`id`, `student_id`, `password`)
VALUES
	(1,'m183302s','tetsuya1016');

/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
