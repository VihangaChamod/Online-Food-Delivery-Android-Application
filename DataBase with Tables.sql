-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.35-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for yumbunyum
CREATE DATABASE IF NOT EXISTS `yumbunyum` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `yumbunyum`;

-- Dumping structure for table yumbunyum.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `set_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `guest_details_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_catalog1_idx` (`catalog_id`),
  KEY `fk_cart_customer1_idx` (`customer_id`),
  KEY `fk_cart_guest_details1_idx` (`guest_details_id`),
  CONSTRAINT `fk_cart_catalog1` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_guest_details1` FOREIGN KEY (`guest_details_id`) REFERENCES `guest_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.cart: ~28 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`id`, `catalog_id`, `qty`, `set_id`, `customer_id`, `guest_details_id`) VALUES
	(74, 2, 3, 1, 2, 30),
	(75, 3, 2, 1, 2, 30),
	(76, 3, 2, 1, 2, 31),
	(77, 2, 4, 2, 2, 31),
	(78, 2, 2, 3, 2, 32),
	(79, 2, 3, 1, 2, 32),
	(80, 7, 1, 1, 2, 33),
	(81, 7, 2, 4, 2, 33),
	(82, 7, 3, 5, 2, 33),
	(83, 4, 3, 1, 2, 34),
	(84, 4, 1, 6, 2, 34),
	(85, 2, 1, 1, 2, 44),
	(86, 2, 1, 1, 2, 46),
	(87, 3, 2, 1, 2, 46),
	(88, 2, 3, 1, 2, 49),
	(89, 3, 1, 1, 2, 49),
	(90, 2, 2, 1, 2, 52),
	(91, 3, 2, 1, 2, 52),
	(92, 3, 3, 7, 2, 52),
	(93, 2, 1, 1, 2, 54),
	(95, 2, 1, 1, 2, 55),
	(96, 2, 1, 1, 2, 62),
	(97, 2, 1, 1, 2, 64),
	(98, 2, 1, 1, 1, 65),
	(99, 2, 1, 1, 1, 66),
	(100, 3, 2, 1, 2, 76),
	(101, 11, 3, 1, 2, 97),
	(102, 2, 1, 1, 2, 108);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.catalog
CREATE TABLE IF NOT EXISTS `catalog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(45) NOT NULL,
  `food_name` varchar(45) NOT NULL,
  `food_price` varchar(45) NOT NULL,
  `isActive` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.catalog: ~11 rows (approximately)
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` (`id`, `img_url`, `food_name`, `food_price`, `isActive`) VALUES
	(1, 'system', 'system', 'system', 1),
	(2, 'img/ch.png', 'Cheese & Kochchi Chicken bueger', '500', 1),
	(3, 'img/ch1.png', 'Cheesy Chicken burger', '750', 1),
	(4, 'img/ch3.png', 'family burger combo', '1000', 1),
	(5, 'img/ch4.png', 'sub  family combo', '1250', 1),
	(6, 'img/ch5.png', 'sub  family combo rrr', '1500', 1),
	(7, 'img/ch6.png', 'French Fries 2', '1750', 1),
	(8, 'img/ch7.png', 'SubCheesy Sausage burger Bun', '2000', 1),
	(9, 'img/ch8.png', 'Cheesy Potato (Veg) burger', '2250', 1),
	(10, 'img/ch9.png', 'Doble burger combo', '500', 1),
	(11, 'img/ch10.png', 'French Fries', '250', 1);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(45) NOT NULL,
  `customer_email` varchar(45) DEFAULT NULL,
  `customer_password` varchar(45) DEFAULT NULL,
  `customer_mobile` varchar(11) NOT NULL,
  `customer_addres` text,
  `datetime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.customer: ~4 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `customer_name`, `customer_email`, `customer_password`, `customer_mobile`, `customer_addres`, `datetime`) VALUES
	(1, 'system_default', 'system_default', 'system_default', 'system', 'system_default', 'system_default'),
	(2, 'vihanga', 'vihanga@gmail.com', '123', '0768467754', '17,kahanthota road Malabe', 'Fri Mar 12 22:15:53 IST 2021'),
	(11, 'abc', 'abc@gmail.com', '123', '1234567', '17 khjjs sj', 'Fri Mar 12 22:15:53 IST 2021'),
	(12, 'abc', 'abcd@gmail.com', '1234', '877687678', 'uyghjjhvhs', 'Fri Mar 12 22:17:48 IST 2021');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.customized
CREATE TABLE IF NOT EXISTS `customized` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_id` int(11) NOT NULL,
  `qw` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customized_catalog1_idx` (`catalog_id`),
  CONSTRAINT `fk_customized_catalog1` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.customized: ~22 rows (approximately)
/*!40000 ALTER TABLE `customized` DISABLE KEYS */;
INSERT INTO `customized` (`id`, `catalog_id`, `qw`) VALUES
	(1, 1, 'system'),
	(2, 11, 'Add Extra Chiken?'),
	(3, 2, 'Add Extra Beans?'),
	(4, 2, 'Add Extra Chocks?'),
	(5, 3, 'Add Extra Potato?'),
	(6, 3, 'Add Extra Combo?'),
	(7, 4, 'Add Extra Cheese?'),
	(8, 4, 'Add Extra Chiken?'),
	(9, 5, 'Add Extra Beans?'),
	(10, 5, 'Add Extra Chocks?'),
	(11, 6, 'Add Extra Potato?'),
	(12, 6, 'Add Extra Combo?'),
	(13, 7, 'Add Extra Cheese?'),
	(14, 7, 'Add Extra Chiken?'),
	(15, 8, 'Add Extra Beans?'),
	(16, 8, 'Add Extra Chocks?'),
	(17, 9, 'Add Extra Potato?'),
	(18, 9, 'Add Extra Combo?'),
	(19, 10, 'Add Extra Potato?'),
	(20, 10, 'Add Extra Combo?'),
	(21, 1, 'system'),
	(22, 11, 'Add Extra Beans?');
/*!40000 ALTER TABLE `customized` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.customized_select
CREATE TABLE IF NOT EXISTS `customized_select` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `set_id` int(11) DEFAULT NULL,
  `customized_id` int(11) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  `guest_details_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customized_select_customized1_idx` (`customized_id`),
  KEY `fk_customized_select_guest_details1_idx` (`guest_details_id`),
  CONSTRAINT `fk_customized_select_customized1` FOREIGN KEY (`customized_id`) REFERENCES `customized` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_customized_select_guest_details1` FOREIGN KEY (`guest_details_id`) REFERENCES `guest_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.customized_select: ~14 rows (approximately)
/*!40000 ALTER TABLE `customized_select` DISABLE KEYS */;
INSERT INTO `customized_select` (`id`, `set_id`, `customized_id`, `date`, `answer`, `guest_details_id`) VALUES
	(1, 1, 1, '1', 'normal', 1),
	(2, 1, 21, '1', 'normal', 1),
	(53, 2, 3, 'Fri Mar 19 13:23:19 IST 2021', 'put', 31),
	(54, 2, 4, 'Fri Mar 19 13:23:19 IST 2021', 'put', 31),
	(55, 3, 3, 'Fri Mar 19 13:30:36 IST 2021', 'put', 32),
	(56, 3, 4, 'Fri Mar 19 13:30:36 IST 2021', 'dont', 32),
	(57, 4, 13, 'Fri Mar 19 13:32:23 IST 2021', 'put', 33),
	(58, 4, 14, 'Fri Mar 19 13:32:23 IST 2021', 'put', 33),
	(59, 5, 13, 'Fri Mar 19 13:32:32 IST 2021', 'dont', 33),
	(60, 5, 14, 'Fri Mar 19 13:32:32 IST 2021', 'dont', 33),
	(61, 6, 7, 'Fri Mar 19 16:07:06 IST 2021', 'put', 34),
	(62, 6, 8, 'Fri Mar 19 16:07:06 IST 2021', 'put', 34),
	(63, 7, 5, 'Fri Mar 19 22:24:49 IST 2021', 'put', 52),
	(64, 7, 6, 'Fri Mar 19 22:24:49 IST 2021', 'put', 52);
/*!40000 ALTER TABLE `customized_select` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.guest_details
CREATE TABLE IF NOT EXISTS `guest_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.guest_details: ~110 rows (approximately)
/*!40000 ALTER TABLE `guest_details` DISABLE KEYS */;
INSERT INTO `guest_details` (`id`, `guest_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 18),
	(7, 19),
	(8, 20),
	(9, 21),
	(10, 22),
	(11, 23),
	(12, 24),
	(13, 25),
	(14, 26),
	(15, 90),
	(16, 91),
	(17, 92),
	(18, 93),
	(19, 94),
	(20, 95),
	(21, 96),
	(22, 97),
	(23, 98),
	(24, 99),
	(25, 100),
	(26, 101),
	(27, 102),
	(28, 103),
	(29, 104),
	(30, 105),
	(31, 106),
	(32, 107),
	(33, 108),
	(34, 109),
	(35, 110),
	(36, 111),
	(37, 112),
	(38, 113),
	(39, 114),
	(40, 115),
	(41, 116),
	(42, 117),
	(43, 118),
	(44, 119),
	(45, 120),
	(46, 121),
	(47, 122),
	(48, 123),
	(49, 124),
	(50, 125),
	(51, 126),
	(52, 127),
	(53, 128),
	(54, 129),
	(55, 130),
	(56, 131),
	(57, 132),
	(58, 133),
	(59, 134),
	(60, 135),
	(61, 136),
	(62, 137),
	(63, 138),
	(64, 139),
	(65, 140),
	(66, 141),
	(67, 142),
	(68, 143),
	(69, 144),
	(70, 145),
	(71, 146),
	(72, 147),
	(73, 148),
	(74, 149),
	(75, 150),
	(76, 151),
	(77, 152),
	(78, 153),
	(79, 154),
	(80, 155),
	(81, 156),
	(82, 157),
	(83, 158),
	(84, 159),
	(85, 160),
	(86, 161),
	(87, 162),
	(88, 163),
	(89, 164),
	(90, 165),
	(91, 166),
	(92, 167),
	(93, 168),
	(94, 169),
	(95, 170),
	(96, 171),
	(97, 172),
	(98, 173),
	(99, 174),
	(100, 175),
	(101, 176),
	(102, 177),
	(103, 178),
	(104, 179),
	(105, 180),
	(106, 181),
	(107, 182),
	(108, 183),
	(109, 184),
	(110, 185);
/*!40000 ALTER TABLE `guest_details` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temp_id` int(11) DEFAULT NULL,
  `datetime` varchar(45) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `delivary_methord` tinyint(4) NOT NULL,
  `price` varchar(45) NOT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `delivery_addres` text,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_cart1_idx` (`cart_id`),
  CONSTRAINT `fk_orders_cart1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.orders: ~18 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `temp_id`, `datetime`, `cart_id`, `delivary_methord`, `price`, `mobile`, `delivery_addres`, `status`) VALUES
	(47, 105, 'Fri Mar 19 13:22:19 IST 2021', 74, 0, '3000.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(48, 105, 'Fri Mar 19 13:22:19 IST 2021', 75, 0, '3000.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(49, 106, 'Fri Mar 19 13:23:28 IST 2021', 76, 0, '3500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'rider_Accepted'),
	(50, 106, 'Fri Mar 19 13:23:28 IST 2021', 77, 0, '3500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'rider_Accepted'),
	(51, 107, 'Fri Mar 19 13:30:47 IST 2021', 78, 0, '2500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(52, 107, 'Fri Mar 19 13:30:47 IST 2021', 79, 0, '2500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(53, 108, 'Fri Mar 19 13:32:42 IST 2021', 80, 0, '10500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(54, 108, 'Fri Mar 19 13:32:42 IST 2021', 81, 0, '10500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(55, 108, 'Fri Mar 19 13:32:42 IST 2021', 82, 0, '10500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(56, 109, 'Fri Mar 19 16:07:18 IST 2021', 83, 0, '4000.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(57, 109, 'Fri Mar 19 16:07:18 IST 2021', 84, 0, '4000.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(58, 127, 'Fri Mar 19 22:25:01 IST 2021', 90, 0, '4750.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(59, 127, 'Fri Mar 19 22:25:01 IST 2021', 91, 0, '4750.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(60, 127, 'Fri Mar 19 22:25:01 IST 2021', 92, 0, '4750.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing'),
	(61, 129, 'Fri Mar 19 22:28:53 IST 2021', 93, 0, '500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'Complete'),
	(62, 130, 'Fri Mar 19 22:30:01 IST 2021', 95, 0, '500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'Complete'),
	(63, 151, 'Sat Mar 20 16:21:39 IST 2021', 100, 0, '1500.0', '0768467754', 'Sri Jayawardenepura Kotte', 'Complete'),
	(64, 172, 'Sat Mar 20 17:45:31 IST 2021', 101, 0, '750.0', '0768467754', 'Sri Jayawardenepura Kotte', 'procesing');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.rider_confirms
CREATE TABLE IF NOT EXISTS `rider_confirms` (
  `rider_confirms_id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) NOT NULL,
  `rider_informations_rider_id` int(11) NOT NULL,
  PRIMARY KEY (`rider_confirms_id`),
  KEY `fk_rider_confirms_orders1_idx` (`orders_id`),
  KEY `fk_rider_confirms_rider_informations1_idx` (`rider_informations_rider_id`),
  CONSTRAINT `fk_rider_confirms_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rider_confirms_rider_informations1` FOREIGN KEY (`rider_informations_rider_id`) REFERENCES `rider_informations` (`rider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.rider_confirms: ~3 rows (approximately)
/*!40000 ALTER TABLE `rider_confirms` DISABLE KEYS */;
INSERT INTO `rider_confirms` (`rider_confirms_id`, `orders_id`, `rider_informations_rider_id`) VALUES
	(65, 49, 1),
	(66, 50, 1),
	(67, 62, 1);
/*!40000 ALTER TABLE `rider_confirms` ENABLE KEYS */;

-- Dumping structure for table yumbunyum.rider_informations
CREATE TABLE IF NOT EXISTS `rider_informations` (
  `rider_id` int(11) NOT NULL AUTO_INCREMENT,
  `rider_nic` varchar(45) DEFAULT NULL,
  `rider_name` varchar(45) DEFAULT NULL,
  `rider_email` varchar(45) DEFAULT NULL,
  `rider_mobile` varchar(45) DEFAULT NULL,
  `rider_password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rider_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table yumbunyum.rider_informations: ~1 rows (approximately)
/*!40000 ALTER TABLE `rider_informations` DISABLE KEYS */;
INSERT INTO `rider_informations` (`rider_id`, `rider_nic`, `rider_name`, `rider_email`, `rider_mobile`, `rider_password`) VALUES
	(1, '200109003357', 'vihanga', 'v@gmail.com', '0768467754', '123');
/*!40000 ALTER TABLE `rider_informations` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
