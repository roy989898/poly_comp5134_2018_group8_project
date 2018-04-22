-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 服務器版本:                        5.7.19 - MySQL Community Server (GPL)
-- 服務器操作系統:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 導出 c_student 的資料庫結構
DROP DATABASE IF EXISTS `c_student`;
CREATE DATABASE IF NOT EXISTS `c_student` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `c_student`;

-- 導出  表 c_student.choose_table 結構
DROP TABLE IF EXISTS `choose_table`;
CREATE TABLE IF NOT EXISTS `choose_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) NOT NULL,
  `teacherid` int(11) NOT NULL,
  `teachername` varchar(255) NOT NULL,
  `teachercourse` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- 正在導出表  c_student.choose_table 的資料：~11 rows (大約)
DELETE FROM `choose_table`;
/*!40000 ALTER TABLE `choose_table` DISABLE KEYS */;
INSERT INTO `choose_table` (`id`, `studentid`, `teacherid`, `teachername`, `teachercourse`) VALUES
	(23, 1, 4, 'teacher1', 'course1'),
	(24, 2, 4, 'teacher1', 'course1');
/*!40000 ALTER TABLE `choose_table` ENABLE KEYS */;

-- 導出  表 c_student.student_teacher 結構
DROP TABLE IF EXISTS `student_teacher`;
CREATE TABLE IF NOT EXISTS `student_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `chance` varchar(255) NOT NULL,
  `days` varchar(255) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `is_teacher` bit(1) DEFAULT b'0',
  `student_per_course_price` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在導出表  c_student.student_teacher 的資料：~4 rows (大約)
DELETE FROM `student_teacher`;
/*!40000 ALTER TABLE `student_teacher` DISABLE KEYS */;
INSERT INTO `student_teacher` (`id`, `name`, `pwd`, `chance`, `days`, `time`, `is_teacher`, `student_per_course_price`) VALUES
	(1, 'student1', 'student1', '19', '90', '20180422', b'0', 75),
	(2, 'student2', 'student2', '29', '90', '20180422', b'0', 66.6667),
	(3, 'student3', 'student3', '0', '0', NULL, b'0', 0),
	(4, 'teacher1', 'teacher1', '0', '0', NULL, b'1', 0);
/*!40000 ALTER TABLE `student_teacher` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
