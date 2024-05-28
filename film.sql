-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: webfilm
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `badges`
--

DROP TABLE IF EXISTS `badges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `badges` (
  `id` int NOT NULL AUTO_INCREMENT,
  `point` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `alt` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badges`
--

LOCK TABLES `badges` WRITE;
/*!40000 ALTER TABLE `badges` DISABLE KEYS */;
INSERT INTO `badges` VALUES (1,1999,3999,'o-thien-co','https://res.cloudinary.com/dwn20guz0/image/upload/v1712661636/samples/landscapes/o-thien-co.webp','Vĩnh Dạ Tán'),(2,1999,3999,'lam-ngan-thao','https://res.cloudinary.com/dwn20guz0/image/upload/v1712661637/samples/landscapes/lam-ngan-thao.gif','Lam Ngân Thảo'),(3,649,190,'kiem-han-lap','https://res.cloudinary.com/dwn20guz0/image/upload/v1713012427/samples/landscapes/kiem-han-lap.gif','Thanh Trúc Phong Vân Kiếm');
/*!40000 ALTER TABLE `badges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,1,'Phim Mới','phim-moi','Phim Mới'),(2,1,'Phim Lẻ','phim-le','Phim Lẻ'),(3,1,'Phim Thuyết Minh','phim-thuyet-minh','Phim Thuyết Minh'),(4,1,'Phim Hoạt Hình','hoat-hinh','Phim Hoạt Hình'),(5,1,'Phim Bộ','phim-bo','Phim Bộ'),(6,1,'Phim Vietsub','phim-vietsub','Phim Vietsub'),(7,1,'Phim Thuyết Minh','phim-thuyet-minh','Phim Thuyết Minh'),(8,1,'Phim Lồng Tiếng','phim-long-tieng','Phim Lồng Tiếng');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `content_comment` varchar(255) DEFAULT NULL,
  `create_at` varchar(255) DEFAULT NULL,
  `edit_comment` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name_user` varchar(255) DEFAULT NULL,
  `slug_film` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,'test','10:31:54AM 16/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1714118761/avatarUser/618f083c61a7460ce0a6064319af41bd_dthlvw.gif','Tao Là Tiến','nguyet-dao-di-gioi-phan-2'),(2,2,'ok đấy','11:09:05PM 16/4/2024','0','https://i.pinimg.com/564x/a2/2d/1d/a22d1d8a789a904187dba0f5240a5907.jpg','Lý Mộ Uyển','cong-anh-ma-chay'),(3,4,'test nào','09:58:49PM 18/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1713453682/avatarUser/618f083c61a7460ce0a6064319af41bd_xeuhg3.gif','Hàn Lão Ma','tinh-yeu-hai-chien-tuyen'),(4,1,'1','04:13:38PM 25/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1714118761/avatarUser/618f083c61a7460ce0a6064319af41bd_dthlvw.gif','Tao Là Tiến','diem-danh'),(5,1,'2','04:13:57PM 25/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1714118761/avatarUser/618f083c61a7460ce0a6064319af41bd_dthlvw.gif','Tao Là Tiến','tu-bao-cac'),(6,4,'mày var không','10:12:25PM 25/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1713453682/avatarUser/618f083c61a7460ce0a6064319af41bd_xeuhg3.gif','Hàn Lão Ma','diem-danh'),(7,4,'test lỗi','10:12:49PM 25/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1713453682/avatarUser/618f083c61a7460ce0a6064319af41bd_xeuhg3.gif','Hàn Lão Ma','quan-tinh-lap-lanh'),(11,4,'test','10:17:34PM 25/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1713453682/avatarUser/618f083c61a7460ce0a6064319af41bd_xeuhg3.gif','Hàn Lão Ma','quan-tinh-lap-lanh'),(13,4,'123','10:19:57PM 25/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1713453682/avatarUser/618f083c61a7460ce0a6064319af41bd_xeuhg3.gif','Hàn Lão Ma','tu-bao-cac'),(15,2,'thích thì inovar','10:20:55PM 25/4/2024','0','https://i.pinimg.com/564x/a2/2d/1d/a22d1d8a789a904187dba0f5240a5907.jpg','Lý Mộ Uyển','diem-danh'),(16,1,'2 thằng cmx var ko','02:57:35PM 26/4/2024','0','http://res.cloudinary.com/dwn20guz0/image/upload/v1714118761/avatarUser/618f083c61a7460ce0a6064319af41bd_dthlvw.gif','Tao Là Tiến','diem-danh');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favmovie`
--

DROP TABLE IF EXISTS `favmovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favmovie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_fav` int DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `episode` varchar(255) DEFAULT NULL,
  `img_movie` varchar(255) DEFAULT NULL,
  `name_movie` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `view_movie` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favmovie`
--

LOCK TABLES `favmovie` WRITE;
/*!40000 ALTER TABLE `favmovie` DISABLE KEYS */;
INSERT INTO `favmovie` VALUES (4,4,'tinh-cam,co-trang,chinh-kich','Tập 31','https://img.ophim15.cc/uploads/movies/tich-hoa-chi-thumb.jpg','Tích Hoa Chỉ','tich-hoa-chi','1748'),(5,4,'hanh-dong,vien-tuong,hai-huoc','Tập 15','https://img.ophim15.cc/uploads/movies/nguyet-dao-di-gioi-phan-2-thumb.jpg','Nguyệt Đạo Dị Giới (Phần 2)','nguyet-dao-di-gioi-phan-2','484'),(6,2,'chinh-kich','Tập 17','https://img.ophim15.cc/uploads/movies/quan-tinh-lap-lanh-thumb.jpg','Quần Tinh Lấp Lánh','quan-tinh-lap-lanh','670'),(7,2,'hanh-dong,phieu-luu','Full','https://img.ophim15.cc/uploads/movies/sat-thu-john-wick-thumb.jpg','Sát thủ John Wick','sat-thu-john-wick','533'),(8,2,'tinh-cam,co-trang,chinh-kich','Tập 35','https://img.ophim15.cc/uploads/movies/tich-hoa-chi-thumb.jpg','Tích Hoa Chỉ','tich-hoa-chi','2030'),(9,2,'hanh-dong,vien-tuong,phieu-luu,khoa-hoc','Tập 2','https://img.ophim15.cc/uploads/movies/hoc-vien-sieu-anh-hung-memories-thumb.jpg','Học Viện Siêu Anh Hùng: Memories','hoc-vien-sieu-anh-hung-memories','131'),(10,2,'chinh-kich','Tập 16','https://img.ophim15.cc/uploads/movies/ga-cho-chang-thumb.jpg','Gả Cho Chàng','ga-cho-chang','180'),(11,2,'vien-tuong,hai-huoc','Tập 3','https://img.ophim15.cc/uploads/movies/mot-can-phong-day-anh-sang-co-thien-than-thumb.jpg','Một Căn Phòng, Đầy Ánh Sáng, Có Thiên Thần','mot-can-phong-day-anh-sang-co-thien-than','4'),(12,2,'vien-tuong,khoa-hoc','Hoàn Tất (40/40)','https://img.ophim15.cc/uploads/movies/gap-lai-tieu-dao-thumb.jpg','Gặp Lại Tiêu Dao','gap-lai-tieu-dao','807'),(13,2,'hanh-dong,vien-tuong,phieu-luu,khoa-hoc','Trailer','https://img.ophim15.cc/uploads/movies/thanh-guom-diet-quy-dai-truc-dac-huan-thumb.jpg','Thanh Gươm Diệt Quỷ: Đại Trụ Đặc Huấn','thanh-guom-diet-quy-dai-truc-dac-huan','92');
/*!40000 ALTER TABLE `favmovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `id` int NOT NULL AUTO_INCREMENT,
  `view` int DEFAULT NULL,
  `year` int DEFAULT NULL,
  `content` text,
  `country` varchar(255) DEFAULT NULL,
  `created` varchar(255) DEFAULT NULL,
  `episode_current` varchar(255) DEFAULT NULL,
  `episode_total` varchar(255) DEFAULT NULL,
  `id_category` varchar(255) DEFAULT NULL,
  `id_genres` varchar(255) DEFAULT NULL,
  `modified` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `origin_name` varchar(255) DEFAULT NULL,
  `poster_url` varchar(255) DEFAULT NULL,
  `quality` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `thumb_url` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `trailer_url` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,476,2024,'\\u003Cp\\u003EMakoto Misumi chỉ là một học sinh trung học bình thường sống một cuộc sống bình thường, nhưng đột nhiên bị triệu hồi đến thế giới khác để trở thành một “anh hùng”. Tuy nhiên, nữ thần của thế giới đó đã lăng mạ anh ta vì sự khác biệt và tước bỏ danh hiệu “anh hùng” của anh ta, trước khi tống cổ anh ta đến vùng hoang dã ở rìa thế giới. Khi đi lang thang trong vùng hoang dã, Makoto chạm trán với rồng, nhện, Orc, người lùn và đủ loại bộ tộc không phải con người. Bởi vì Makoto đến từ một thế giới khác, anh ta có thể giải phóng sức mạnh ma thuật và kỹ năng chiến đấu ngoài sức tưởng tượng. Nhưng anh ấy sẽ xử lý như thế nào khi gặp nhiều loài sinh vật khác nhau và tồn tại trong một môi trường mới?\\u003C/p\\u003E','Nhật Bản','','Tập 15','25 Tập',NULL,'Hành Động, Hài Hước, Viễn Tưởng','05:05:22PM 16/4/2024','Nguyệt Đạo Dị Giới (Phần 2)','Tsukimichi -Moonlit Fantasy- Season 2 / Tsuki ga Michibiku 2','https://img.ophim15.cc/uploads/movies/nguyet-dao-di-gioi-phan-2-poster.jpg','HD','nguyet-dao-di-gioi-phan-2','ongoing','https://img.ophim15.cc/uploads/movies/nguyet-dao-di-gioi-phan-2-thumb.jpg','24 phút/tập','https://www.youtube.com/watch?v=U8T63kIny7E','hoathinh'),(2,35,2024,'Anime kể về Banaza, anh được triệu hồi đến Vương Quốc Ma Thuật Cryroad với tư cách là ứng cử viên cho chức vị Anh Hùng . Nhưng các chỉ số của Banaza chỉ ở mức ngang với 1 thường dân nên bị mọi người cho là 1 thất bại và đuổi anh khỏi cung điện . Tuy nhiên , khi anh lên Lv.2 thì mọi chỉ số được nâng lên vô cực và mở khóa toàn bộ skills . Câu chuyện phiêu lưu của anh chàng anh hùng bắt đầu từ đây ,...','Nhật Bản','04:54:03PM 16/4/2024','Tập 2','12 Tập',NULL,'Hành Động, Viễn Tưởng, Phiêu Lưu, Khoa Học','05:05:01PM 16/4/2024','Cuộc sống thảnh thơi tại dị giới của cựu ứng viên dũng giả đã gian lận từ cấp độ hai','Chillin\' in Another World with Level 2 Super Cheat Powers','https://img.ophim15.cc/uploads/movies/cuoc-song-thanh-thoi-tai-di-gioi-cua-cuu-ung-vien-dung-gia-da-gian-lan-tu-cap-do-hai-poster.jpg','HD','cuoc-song-thanh-thoi-tai-di-gioi-cua-cuu-ung-vien-dung-gia-da-gian-lan-tu-cap-do-hai','ongoing','https://img.ophim15.cc/uploads/movies/cuoc-song-thanh-thoi-tai-di-gioi-cua-cuu-ung-vien-dung-gia-da-gian-lan-tu-cap-do-hai-thumb.jpg','24 phút/tập','https://www.youtube.com/watch?v=IwxaZRkXdps','hoathinh'),(3,115,2024,'Haruka Sakura wants nothing to do with weaklings—he\'s only interested in the strongest of the strong. He\'s just started at Furin High School, a school of degenerates known only for their brawling strength—strength they use to protect their town from anyone who wishes it ill. But Haruka\'s not interested in being a hero or being part of any sort of team—he just wants to fight his way to the top!','Nhật Bản','04:57:05PM 16/4/2024','Tập 2','12 Tập',NULL,'Hành Động','04:57:05PM 16/4/2024','WIND BREAKER','Wind Breaker','https://img.ophim15.cc/uploads/movies/wind-breaker-poster.jpg','HD','wind-breaker','ongoing','https://img.ophim15.cc/uploads/movies/wind-breaker-thumb.jpg','24 phút/tập','https://www.youtube.com/watch?v=dTMmCSHOSEQ','hoathinh'),(4,211,2024,'Hầm ngục, rồng… và món quái vật hầm thơm ngon!? Các nhà thám hiểm đột nhập vào vương quốc trúng lời nguyền bị chôn vùi để cứu bạn mình và gây náo loạn trên đường đi.','Nhật Bản','05:02:18PM 16/4/2024','Tập 15','24 Tập',NULL,'Hành Động, Hài Hước, Viễn Tưởng, Phiêu Lưu, Khoa Học','05:05:54PM 16/4/2024','Mỹ vị hầm ngục','Delicious in Dungeon','https://img.ophim15.cc/uploads/movies/my-vi-ham-nguc-poster.jpg','HD','my-vi-ham-nguc','ongoing','https://img.ophim15.cc/uploads/movies/my-vi-ham-nguc-thumb.jpg','26 phút/tập','https://www.youtube.com/watch?v=n5JEoD7keVo','hoathinh'),(5,126,2024,'\\u003Cp\\u003ETóm tắt Boku no Hero Academia dẫn đến mùa thứ bảy.\\u003C/p\\u003E','Nhật Bản','05:04:43PM 16/4/2024','Tập 2','4',NULL,'Hành Động, Viễn Tưởng, Phiêu Lưu, Khoa Học','05:04:43PM 16/4/2024','Học Viện Siêu Anh Hùng: Memories','Boku no Hero Academia: Memories','https://img.ophim15.cc/uploads/movies/hoc-vien-sieu-anh-hung-memories-poster.jpg','HD','hoc-vien-sieu-anh-hung-memories','ongoing','https://img.ophim15.cc/uploads/movies/hoc-vien-sieu-anh-hung-memories-thumb.jpg','24 phút/tập','https://www.youtube.com/watch?v=wIb3nnOeves','hoathinh'),(6,75,2024,'Mingfei Lu là một học sinh trung học bình thường như bao người. Tuy nhiên sau lần Mingfei Lu nhận được thư nhập học từ học viện Cassell , nơi mà anh chưa từng đăng ký trước đây, sau khi nhiều lần do dự, Mingfei quyết định tìm hiểu và cuộc đời anh cũng chính thức thay đổi chấn động từ thời điểm này. Học viện đặc biệt của anh ta, với đội ngũ giảng viên lập dị và tập thể sinh viên kỳ quặc, có nhiệm vụ tiêu diệt kẻ thù của loài người, \"những con rồng\", bằng cách tập hợp những con người từ khắp nơi trên thế giới, những người mang trong mình dòng máu rồng. Mingfei nhận ra mình bị cuốn vào cuộc chiến chống lại chúa tể loài rồng - những kẻ đã ngủ yên hàng nghìn năm, khi anh biết được sự thật đằng sau lịch sử ẩn giấu về cuộc chiến của loài người chống lại Dragon Raja.','Nhật Bản','05:07:54PM 16/4/2024','Tập 2','16 Tập',NULL,'Hành Động, Viễn Tưởng, Phiêu Lưu, Khoa Học','05:07:54PM 16/4/2024','Dragon Raja - Bình Minh Rực Lửa','Dragon Raja -The Blazing Dawn-','https://img.ophim15.cc/uploads/movies/dragon-raja-binh-minh-ruc-lua-poster.jpg','HD','dragon-raja-binh-minh-ruc-lua','ongoing','https://img.ophim15.cc/uploads/movies/dragon-raja-binh-minh-ruc-lua-thumb.jpg','23 phút/tập','https://www.youtube.com/watch?v=j3azzxRVU0k','hoathinh'),(7,24,2024,'Câu chuyện bình dị này xoay quanh hai cô gái trẻ: Rin yêu thích cắm trại một mình bên bờ hồ, nơi có thế nhìn ra núi Phú Sĩ hùng vĩ; và Nadeshiko thích những chuyến đi xe một mình đến những nơi mà cô có thể ngắm thấy núi Phú Sĩ. Sau khi gặp nhau. Rin và Nadeshiko đã cùng nhau cắm trại, ăn mì ly và cùng thưởng thức cảnh đẹp.','Nhật Bản','05:10:51PM 16/4/2024','Tập 2','12 Tập',NULL,'Hài Hước, Gia Đình','05:10:51PM 16/4/2024','Dã ngoại thảnh thơi (Phần 3)','Laid-Back Camp Season 3','https://img.ophim15.cc/uploads/movies/da-ngoai-thanh-thoi-phan-3-poster.jpg','HD','da-ngoai-thanh-thoi-phan-3','ongoing','https://img.ophim15.cc/uploads/movies/da-ngoai-thanh-thoi-phan-3-thumb.jpg','24 phút/tập','https://www.youtube.com/watch?v=ZlL01zXTq4c','hoathinh'),(8,49,2024,'Hananoi Và Triệu Chứng Tình Yêu','Nhật Bản','05:14:22PM 16/4/2024','Tập 2','? Tập',NULL,'Tình Cảm','05:14:22PM 16/4/2024','Hananoi Và Triệu Chứng Tình Yêu','花野井くんと恋の病','https://img.ophim15.cc/uploads/movies/hananoi-va-trieu-chung-tinh-yeu-poster.jpg','HD','hananoi-va-trieu-chung-tinh-yeu','ongoing','https://img.ophim15.cc/uploads/movies/hananoi-va-trieu-chung-tinh-yeu-thumb.jpg','24 phút/tập','','hoathinh'),(9,101,2024,'Mushoku Tensei: Jobless Reincarnation Season 2 Cour 2','Nhật Bản','05:16:47PM 16/4/2024','Tập 2','? Tập',NULL,'Phiêu Lưu, Viễn Tưởng','05:19:25PM 16/4/2024','Thất Nghiệp Chuyển Sinh (Phần 2) - Part 2','Mushoku Tensei: Jobless Reincarnation Season 2 Cour 2','https://img.ophim15.cc/uploads/movies/mushoku-tensei-jobless-reincarnation-season-2-poster.jpg','HD','mushoku-tensei-jobless-reincarnation-season-2','ongoing','https://img.ophim15.cc/uploads/movies/mushoku-tensei-jobless-reincarnation-season-2-thumb.jpg','24 phút/tập','','hoathinh'),(10,78,2024,'Prince Lloyd wasn\'t always a prince...in fact, his previous life is one he remembers perfectly: he was a sorcerer, of sorts. So when he was forced to reincarnate, he decided to continue his studies, prince of the realm or no! But his new life has its own sets of challenges...including being a 10-year-old! What\'s the 7th prince/sorcerer to do?!','Nhật Bản','05:32:51PM 16/4/2024','Tập 3','12 Tập',NULL,'Viễn Tưởng, Khoa Học','05:32:51PM 16/4/2024','Chuyển sinh thành đệ thất hoàng tử, tôi quyết định tự do tự tại trau dồi ma thuật','I Was Reincarnated as the 7th Prince So I Can Take My Time Perfecting My Magical Ability','https://img.ophim15.cc/uploads/movies/chuyen-sinh-thanh-de-that-hoang-tu-toi-quyet-dinh-tu-do-tu-tai-trau-doi-ma-thuat-poster.jpg','HD','chuyen-sinh-thanh-de-that-hoang-tu-toi-quyet-dinh-tu-do-tu-tai-trau-doi-ma-thuat','ongoing','https://img.ophim15.cc/uploads/movies/chuyen-sinh-thanh-de-that-hoang-tu-toi-quyet-dinh-tu-do-tu-tai-trau-doi-ma-thuat-thumb.jpg','24 phút/tập','https://www.youtube.com/watch?v=41FDe5_XKec','hoathinh'),(11,1517,2024,'Hoa phủ cao quý hiển hách bất ngờ bị tịch biên và lưu đày, chỉ còn lại phụ nữ cùng trẻ em bơ vơ. Đại tiểu thư Hoa Chỉ không còn giả vờ ngốc nghếch giấu dốt nữa, cô dẫn dắt phụ nữ trong nhà trình diễn \"màn lột xác của phụ nữ quyền quý\", thoát khỏi cảnh đói rét và nguy hiểm trong muôn vàn chông gai. Cô còn biến ty sứ Thất Túc là Cố Yến Tích dũng mãnh vô song thành \"Yến tiên sinh\", người thương mà ai nấy đều yêu mến. Trong suốt hành trình có cãi vã, có mặn nồng, từ một tiểu thư khuê các gặp nạn, Hoa Chỉ cũng trưởng thành và trở thành người chủ gia đình, hướng tới một cuộc sống mới rực rỡ.','Trung Quốc','01:38:33PM 19/4/2024','Tập 28','42 Tập',NULL,'Tình Cảm, Cổ Trang, Chính Kịch','01:38:33PM 19/4/2024','Tích Hoa Chỉ','Blossoms in Adversity','https://img.ophim15.cc/uploads/movies/tich-hoa-chi-poster.jpg','HD','tich-hoa-chi','ongoing','https://img.ophim15.cc/uploads/movies/tich-hoa-chi-thumb.jpg','45 phút/tập','','series'),(12,151,2022,'\\u003Cp\\u003EHoạ sư cung đình Lục Cửu Khanh vô tình dùng bút thần mở ra thế giới trong tranh, gặp gỡ tinh linh thảo mộc Nhan Ca. Nữ tử trong thành liên tiếp bị huỷ hoại gương mặt, để điều tra chân tướng hai người họ đã vào mộ sinh tử. Yêu vương lột đi da mặt của chúng tinh linh thảo mộc, Nhan Ca mất đi gương mặt xinh đẹp trở thành cành cây khô cằn dữ tợn. Một cuộc thử thách giữa vẻ bề ngoài và tấm lòng được đặt ra ngay trước mặt Lục Cửu Khanh.\\u003C/p\\u003E','Trung Quốc','01:42:10PM 19/4/2024','Full','Full',NULL,'Cổ Trang','01:42:10PM 19/4/2024','Chỉ Hoạ Bì','Paper Beauty','https://img.ophim15.cc/uploads/movies/chi-hoa-bi-poster.jpg','HD','chi-hoa-bi','completed','https://img.ophim15.cc/uploads/movies/chi-hoa-bi-thumb.jpg','78 phút','https://www.youtube.com/watch?v=vMhvO6HC0ko','single');
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,1,'Hành Động','hanh-dong','Hành Động'),(2,1,'Tâm Lý','tam-ly','Tâm Lý'),(3,1,'Hài Hước','hai-huoc','Hài Hước'),(4,1,'Cổ Trang','co-trang','Cổ Trang'),(5,1,'Tâm Lý','tam-ly','Tâm Lý'),(6,1,'Hình Sự','hinh-su','Hình Sự'),(7,1,'Thể Thao','theo-thao','Thể Thao'),(8,1,'Chiến Tranh','chien-tranh','Chiến Tranh'),(9,1,'Võ Thuật','vo-thuat','Võ Thuật'),(10,1,'Viễn Tưởng','vien-tuong','Viễn Tưởng'),(11,1,'Phiêu Lưu','phieu-luu','Phiêu Lưu'),(12,1,'Khoa Học','khoa-hoc','Khoa Học'),(13,1,'Kinh Dị','kinh-di','Kinh Dị'),(14,1,'Học Đường','hoc-duong','Học Đường'),(15,1,'Gia Đình','gia-dinh','Gia Đình'),(16,1,'Chính Kịch','chinh-kich','Chính Kịch'),(17,1,'Âm Nhạc','am-nhac','Âm Nhạc'),(18,1,'Bí Ẩn','bi-an','Bí Ẩn'),(19,1,'Phim 18+','phim-18','Phim 18+');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_watch`
--

DROP TABLE IF EXISTS `history_watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_watch` (
  `episode` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `user_watch` int DEFAULT NULL,
  `view_movie` int DEFAULT NULL,
  `img_movie` varchar(255) DEFAULT NULL,
  `name_movie` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_watch`
--

LOCK TABLES `history_watch` WRITE;
/*!40000 ALTER TABLE `history_watch` DISABLE KEYS */;
INSERT INTO `history_watch` VALUES (1,13,1,75,'https://img.ophim15.cc/uploads/movies/immortality-3-thumb.jpg','Immortality 3','immortality-3'),(1,14,1,2030,'https://img.ophim15.cc/uploads/movies/tich-hoa-chi-thumb.jpg','Tích Hoa Chỉ','tich-hoa-chi'),(1,15,1,81,'https://img.ophim15.cc/uploads/movies/cuoc-song-thanh-thoi-tai-di-gioi-cua-cuu-ung-vien-dung-gia-da-gian-lan-tu-cap-do-hai-thumb.jpg','Cuộc sống thảnh thơi tại dị giới của cựu ứng viên dũng giả đã gian lận từ cấp độ hai','cuoc-song-thanh-thoi-tai-di-gioi-cua-cuu-ung-vien-dung-gia-da-gian-lan-tu-cap-do-hai');
/*!40000 ALTER TABLE `history_watch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `content` text,
  `author` varchar(100) DEFAULT NULL,
  `id_author` bigint DEFAULT NULL,
  `create_at` varchar(100) DEFAULT NULL,
  `image` text,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_user_idx` (`id_author`),
  CONSTRAINT `post_user` FOREIGN KEY (`id_author`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` int DEFAULT NULL,
  `point` int DEFAULT NULL,
  `crystal` int DEFAULT NULL,
  `is_activity` int DEFAULT NULL,
  `activation_code` varchar(10) DEFAULT NULL,
  `attendance` varchar(10) DEFAULT NULL,
  `attendance_day` varchar(10) DEFAULT NULL,
  `create_at` varchar(50) DEFAULT NULL,
  `image` text,
  `last_active` varchar(100) DEFAULT NULL,
  `maxim` text,
  `users_badges` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tien','$2a$10$sATjWilyrIRoHGisPkN12eadKociNuePZIw7AD91kV4KRiHbxn4HS','Tao Là Tiến','nguyentien170803@gmail.com',1,14662,2112,1,'','2','26','16/04/2024','http://res.cloudinary.com/dwn20guz0/image/upload/v1714118761/avatarUser/618f083c61a7460ce0a6064319af41bd_dthlvw.gif','','yêu em nhé','3, 1, 2',NULL),(2,'tiendz','$2a$10$sATjWilyrIRoHGisPkN12eadKociNuePZIw7AD91kV4KRiHbxn4HS','Lý Mộ Uyển','huyen182k3@gmail.com',1,694,10,1,'','2','25','16/04/2024',NULL,'','yêu anh không','3',NULL),(4,'tiennguyen','$2a$10$sATjWilyrIRoHGisPkN12eadKociNuePZIw7AD91kV4KRiHbxn4HS','Hàn Lão Ma','nt687901@gmail.com',1,60,400,1,'','1','25','18/04/2024','http://res.cloudinary.com/dwn20guz0/image/upload/v1713453682/avatarUser/618f083c61a7460ce0a6064319af41bd_xeuhg3.gif','','Vương Lâm ta đời này \"Không bái thiên địa, Không kính quỷ thần, Chỉ bái cha mẹ, Chỉ kính Tư Đồ Nam\". Vì một người con gái mình yêu mà tính kế vạn cổ.',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-28 11:58:21
