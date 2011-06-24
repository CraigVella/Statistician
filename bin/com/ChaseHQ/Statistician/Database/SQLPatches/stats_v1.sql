/*
Statistician Version 1 Database
Hi from ChaseHQ :)
If you found this PM me on the bukkit forums!
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blocks`
-- ----------------------------
DROP TABLE IF EXISTS `blocks`;
CREATE TABLE `blocks` (
  `uuid` varchar(255) NOT NULL,
  `block_id` int(11) NOT NULL,
  `num_destroyed` int(11) NOT NULL DEFAULT '0',
  `num_placed` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uuid`,`block_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of blocks
-- ----------------------------

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `dbVersion` int(11) NOT NULL,
  `show_firstjoin_welcome` enum('N','Y') NOT NULL DEFAULT 'Y',
  `firstjoin_welcome_msg` varchar(255) DEFAULT NULL,
  `show_lastjoin_welcome` enum('N','Y') DEFAULT 'Y',
  `lastjoin_welcome_message` varchar(255) DEFAULT NULL,
  `date_format` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dbVersion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', 'Y', '&aWelcome &d{playerName}! &aI See this is your first time here. &3[Statistician] &ahas begun logging you.', 'Y', '&aWelcome back &d{playerName}, the last time you were here was &e{lastJoin}', 'EEE, MMM d, yyyy \'at\' hh:mm:ss a');

-- ----------------------------
-- Table structure for `creatures`
-- ----------------------------
DROP TABLE IF EXISTS `creatures`;
CREATE TABLE `creatures` (
  `id` int(11) NOT NULL,
  `creature_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of creatures
-- ----------------------------
INSERT INTO `creatures` VALUES ('0', 'None');
INSERT INTO `creatures` VALUES ('999', 'Player');
INSERT INTO `creatures` VALUES ('1', 'Chicken');
INSERT INTO `creatures` VALUES ('2', 'Cow');
INSERT INTO `creatures` VALUES ('3', 'Creeper');
INSERT INTO `creatures` VALUES ('4', 'Electrified Creeper');
INSERT INTO `creatures` VALUES ('5', 'Ghast');
INSERT INTO `creatures` VALUES ('6', 'Giant');
INSERT INTO `creatures` VALUES ('7', 'Monster');
INSERT INTO `creatures` VALUES ('8', 'Pig');
INSERT INTO `creatures` VALUES ('9', 'Pig Zombie');
INSERT INTO `creatures` VALUES ('10', 'Sheep');
INSERT INTO `creatures` VALUES ('11', 'Skeleton');
INSERT INTO `creatures` VALUES ('12', 'Slime');
INSERT INTO `creatures` VALUES ('13', 'Spider');
INSERT INTO `creatures` VALUES ('14', 'Squid');
INSERT INTO `creatures` VALUES ('15', 'Wolf');
INSERT INTO `creatures` VALUES ('16', 'Tame Wolf');
INSERT INTO `creatures` VALUES ('17', 'Spider Jocky');
INSERT INTO `creatures` VALUES ('18', 'Block');
INSERT INTO `creatures` VALUES ('19', 'Zombie');

-- ----------------------------
-- Table structure for `kill_types`
-- ----------------------------
DROP TABLE IF EXISTS `kill_types`;
CREATE TABLE `kill_types` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of kill_types
-- ----------------------------
INSERT INTO `kill_types` VALUES ('0', 'None');
INSERT INTO `kill_types` VALUES ('1', 'Block Explosion');
INSERT INTO `kill_types` VALUES ('2', 'Drowning');
INSERT INTO `kill_types` VALUES ('3', 'Entity Explosion');
INSERT INTO `kill_types` VALUES ('4', 'Fall');
INSERT INTO `kill_types` VALUES ('5', 'Fire');
INSERT INTO `kill_types` VALUES ('6', 'Fire Tick');
INSERT INTO `kill_types` VALUES ('7', 'Void');
INSERT INTO `kill_types` VALUES ('8', 'Suffocation');
INSERT INTO `kill_types` VALUES ('9', 'Lightening');
INSERT INTO `kill_types` VALUES ('10', 'Lava');
INSERT INTO `kill_types` VALUES ('11', 'Contact');
INSERT INTO `kill_types` VALUES ('12', 'Entity Attack');
INSERT INTO `kill_types` VALUES ('13', 'Custom');

-- ----------------------------
-- Table structure for `kills`
-- ----------------------------
DROP TABLE IF EXISTS `kills`;
CREATE TABLE `kills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `killed` int(11) DEFAULT NULL,
  `killed_by` int(11) DEFAULT NULL,
  `kill_type` int(11) DEFAULT NULL,
  `killed_using` int(11) DEFAULT NULL,
  `killed_projectile` int(11) DEFAULT NULL,
  `killed_by_uuid` varchar(255) DEFAULT NULL,
  `killed_uuid` varchar(255) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of kills
-- ----------------------------

-- ----------------------------
-- Table structure for `pickup_drop`
-- ----------------------------
DROP TABLE IF EXISTS `pickup_drop`;
CREATE TABLE `pickup_drop` (
  `uuid` varchar(255) NOT NULL,
  `item` int(11) NOT NULL,
  `num_pickedup` int(11) NOT NULL DEFAULT '0',
  `num_dropped` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uuid`,`item`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pickup_drop
-- ----------------------------

-- ----------------------------
-- Table structure for `players`
-- ----------------------------
DROP TABLE IF EXISTS `players`;
CREATE TABLE `players` (
  `uuid` varchar(255) NOT NULL DEFAULT '',
  `player_name` varchar(255) DEFAULT NULL,
  `online` enum('N','Y') NOT NULL DEFAULT 'N',
  `firstever_login` int(11) DEFAULT NULL,
  `last_login` int(11) DEFAULT NULL,
  `num_logins` int(11) DEFAULT NULL,
  `this_login` int(11) DEFAULT NULL,
  `last_logout` int(11) DEFAULT NULL,
  `num_secs_loggedon` int(11) NOT NULL DEFAULT '0',
  `distance_traveled` int(11) NOT NULL DEFAULT '0',
  `distance_traveled_in_minecart` int(11) NOT NULL DEFAULT '0',
  `distance_traveled_in_boat` int(11) NOT NULL DEFAULT '0',
  `distance_traveled_on_pig` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of players
-- ----------------------------

-- ----------------------------
-- Table structure for `projectiles`
-- ----------------------------
DROP TABLE IF EXISTS `projectiles`;
CREATE TABLE `projectiles` (
  `id` int(11) NOT NULL,
  `projectile_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of projectiles
-- ----------------------------
INSERT INTO `projectiles` VALUES ('0', 'None');
INSERT INTO `projectiles` VALUES ('1', 'Arrow');

-- ----------------------------
-- Table structure for `resource_desc`
-- ----------------------------
DROP TABLE IF EXISTS `resource_desc`;
CREATE TABLE `resource_desc` (
  `resource_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of resource_desc
-- ----------------------------
INSERT INTO `resource_desc` VALUES ('0', 'Air');
INSERT INTO `resource_desc` VALUES ('1', 'Stone');
INSERT INTO `resource_desc` VALUES ('2', 'Grass');
INSERT INTO `resource_desc` VALUES ('3', 'Dirt');
INSERT INTO `resource_desc` VALUES ('4', 'Cobblestone');
INSERT INTO `resource_desc` VALUES ('5', 'Wooden Plank ');
INSERT INTO `resource_desc` VALUES ('6', 'Sapling');
INSERT INTO `resource_desc` VALUES ('7', 'Bedrock');
INSERT INTO `resource_desc` VALUES ('8', 'Water');
INSERT INTO `resource_desc` VALUES ('9', 'Stationary Water');
INSERT INTO `resource_desc` VALUES ('10', 'Lava');
INSERT INTO `resource_desc` VALUES ('11', 'Stationary Lava');
INSERT INTO `resource_desc` VALUES ('12', 'Sand');
INSERT INTO `resource_desc` VALUES ('13', 'Gravel');
INSERT INTO `resource_desc` VALUES ('14', 'Gold Ore');
INSERT INTO `resource_desc` VALUES ('15', 'Iron Ore');
INSERT INTO `resource_desc` VALUES ('16', 'Coal Ore');
INSERT INTO `resource_desc` VALUES ('17', 'Wood');
INSERT INTO `resource_desc` VALUES ('18', 'Leaves');
INSERT INTO `resource_desc` VALUES ('19', 'Sponge');
INSERT INTO `resource_desc` VALUES ('20', 'Glass');
INSERT INTO `resource_desc` VALUES ('21', 'Lapis Lazuli Ore');
INSERT INTO `resource_desc` VALUES ('22', 'Lapis Lazuli Block');
INSERT INTO `resource_desc` VALUES ('23', 'Dispenser ');
INSERT INTO `resource_desc` VALUES ('24', 'Sandstone');
INSERT INTO `resource_desc` VALUES ('25', 'Note Block');
INSERT INTO `resource_desc` VALUES ('26', 'Bed');
INSERT INTO `resource_desc` VALUES ('27', 'Powered Rail');
INSERT INTO `resource_desc` VALUES ('28', 'Detector Rail');
INSERT INTO `resource_desc` VALUES ('30', 'Cobweb');
INSERT INTO `resource_desc` VALUES ('31', 'Tall Grass');
INSERT INTO `resource_desc` VALUES ('32', 'Dead Shrubs');
INSERT INTO `resource_desc` VALUES ('35', 'Wool');
INSERT INTO `resource_desc` VALUES ('37', 'Dandelion');
INSERT INTO `resource_desc` VALUES ('38', 'Rose');
INSERT INTO `resource_desc` VALUES ('39', 'Brown Mushroom');
INSERT INTO `resource_desc` VALUES ('40', 'Red Mushroom ');
INSERT INTO `resource_desc` VALUES ('41', 'Gold Block');
INSERT INTO `resource_desc` VALUES ('42', 'Iron Block');
INSERT INTO `resource_desc` VALUES ('43', 'Double Slabs');
INSERT INTO `resource_desc` VALUES ('44', 'Slabs');
INSERT INTO `resource_desc` VALUES ('45', 'Brick Block ');
INSERT INTO `resource_desc` VALUES ('46', 'TNT');
INSERT INTO `resource_desc` VALUES ('47', 'Bookshelf');
INSERT INTO `resource_desc` VALUES ('48', 'Moss Stone');
INSERT INTO `resource_desc` VALUES ('49', 'Obsidian');
INSERT INTO `resource_desc` VALUES ('50', 'Torch');
INSERT INTO `resource_desc` VALUES ('51', 'Fire');
INSERT INTO `resource_desc` VALUES ('52', 'Monster Spawner ');
INSERT INTO `resource_desc` VALUES ('53', 'Wooden Stairs');
INSERT INTO `resource_desc` VALUES ('54', 'Chest');
INSERT INTO `resource_desc` VALUES ('55', 'Redstone Wire');
INSERT INTO `resource_desc` VALUES ('56', 'Diamond Ore ');
INSERT INTO `resource_desc` VALUES ('57', 'Diamond Block ');
INSERT INTO `resource_desc` VALUES ('58', 'Crafting Table ');
INSERT INTO `resource_desc` VALUES ('59', 'Seeds');
INSERT INTO `resource_desc` VALUES ('60', 'Farmland');
INSERT INTO `resource_desc` VALUES ('61', 'Furnace');
INSERT INTO `resource_desc` VALUES ('62', 'Burning Furnace');
INSERT INTO `resource_desc` VALUES ('63', 'Sign Post');
INSERT INTO `resource_desc` VALUES ('64', 'Wooden Door');
INSERT INTO `resource_desc` VALUES ('65', 'Ladders');
INSERT INTO `resource_desc` VALUES ('66', 'Rails');
INSERT INTO `resource_desc` VALUES ('67', 'Cobblestone Stairs');
INSERT INTO `resource_desc` VALUES ('68', 'Wall Sign');
INSERT INTO `resource_desc` VALUES ('69', 'Lever');
INSERT INTO `resource_desc` VALUES ('70', 'Stone Pressure Plate');
INSERT INTO `resource_desc` VALUES ('71', 'Iron Door ');
INSERT INTO `resource_desc` VALUES ('72', 'Wooden Pressure Plate');
INSERT INTO `resource_desc` VALUES ('73', 'Redstone Ore');
INSERT INTO `resource_desc` VALUES ('74', 'Glowing Redstone Ore');
INSERT INTO `resource_desc` VALUES ('75', 'Redstone Torch (\"off\" state) ');
INSERT INTO `resource_desc` VALUES ('76', 'Redstone Torch (\"on\" state)');
INSERT INTO `resource_desc` VALUES ('77', 'Stone Button ');
INSERT INTO `resource_desc` VALUES ('78', 'Snow');
INSERT INTO `resource_desc` VALUES ('79', 'Ice');
INSERT INTO `resource_desc` VALUES ('80', 'Snow Block');
INSERT INTO `resource_desc` VALUES ('81', 'Cactus');
INSERT INTO `resource_desc` VALUES ('82', 'Clay Block');
INSERT INTO `resource_desc` VALUES ('83', 'Sugar Cane');
INSERT INTO `resource_desc` VALUES ('84', 'Jukebox');
INSERT INTO `resource_desc` VALUES ('85', 'Fence');
INSERT INTO `resource_desc` VALUES ('86', 'Pumpkin');
INSERT INTO `resource_desc` VALUES ('87', 'Netherrack');
INSERT INTO `resource_desc` VALUES ('88', 'Soul Sand');
INSERT INTO `resource_desc` VALUES ('89', 'Glowstone Block');
INSERT INTO `resource_desc` VALUES ('90', 'Portal');
INSERT INTO `resource_desc` VALUES ('91', 'Jack-O-Lantern');
INSERT INTO `resource_desc` VALUES ('92', 'Cake Block');
INSERT INTO `resource_desc` VALUES ('93', 'Redstone Repeater (\"off\" state)');
INSERT INTO `resource_desc` VALUES ('94', 'Redstone Repeater (\"on\" state)');
INSERT INTO `resource_desc` VALUES ('95', 'Locked Chest');
INSERT INTO `resource_desc` VALUES ('96', 'Trapdoor');
INSERT INTO `resource_desc` VALUES ('256', 'Iron Shovel');
INSERT INTO `resource_desc` VALUES ('257', 'Iron Pickaxe');
INSERT INTO `resource_desc` VALUES ('258', 'Iron Axe');
INSERT INTO `resource_desc` VALUES ('259', 'Flint and Steel ');
INSERT INTO `resource_desc` VALUES ('260', 'Apple');
INSERT INTO `resource_desc` VALUES ('261', 'Bow');
INSERT INTO `resource_desc` VALUES ('262', 'Arrow');
INSERT INTO `resource_desc` VALUES ('263', 'Coal');
INSERT INTO `resource_desc` VALUES ('264', 'Diamond');
INSERT INTO `resource_desc` VALUES ('265', 'Iron Ingot');
INSERT INTO `resource_desc` VALUES ('266', 'Gold Ingot');
INSERT INTO `resource_desc` VALUES ('267', 'Iron Sword');
INSERT INTO `resource_desc` VALUES ('268', 'Wooden Sword ');
INSERT INTO `resource_desc` VALUES ('269', 'Wooden Shovel');
INSERT INTO `resource_desc` VALUES ('270', 'Wooden Pickaxe');
INSERT INTO `resource_desc` VALUES ('271', 'Wooden Axe');
INSERT INTO `resource_desc` VALUES ('272', 'Stone Sword');
INSERT INTO `resource_desc` VALUES ('273', 'Stone Shovel');
INSERT INTO `resource_desc` VALUES ('274', 'Stone Pickaxe');
INSERT INTO `resource_desc` VALUES ('275', 'Stone Axe');
INSERT INTO `resource_desc` VALUES ('276', 'Diamond Sword');
INSERT INTO `resource_desc` VALUES ('277', 'Diamond Shovel');
INSERT INTO `resource_desc` VALUES ('278', 'Diamond Pickaxe');
INSERT INTO `resource_desc` VALUES ('279', 'Diamond Axe');
INSERT INTO `resource_desc` VALUES ('280', 'Stick');
INSERT INTO `resource_desc` VALUES ('281', 'Bowl');
INSERT INTO `resource_desc` VALUES ('282', 'Mushroom Soup');
INSERT INTO `resource_desc` VALUES ('283', 'Gold Sword ');
INSERT INTO `resource_desc` VALUES ('284', 'Gold Shovel');
INSERT INTO `resource_desc` VALUES ('285', 'Gold Pickaxe');
INSERT INTO `resource_desc` VALUES ('286', 'Gold Axe');
INSERT INTO `resource_desc` VALUES ('287', 'String');
INSERT INTO `resource_desc` VALUES ('288', 'Feather');
INSERT INTO `resource_desc` VALUES ('289', 'Gunpowder');
INSERT INTO `resource_desc` VALUES ('290', 'Wooden Hoe');
INSERT INTO `resource_desc` VALUES ('291', 'Stone Hoe');
INSERT INTO `resource_desc` VALUES ('292', 'Iron Hoe');
INSERT INTO `resource_desc` VALUES ('293', 'Diamond Hoe');
INSERT INTO `resource_desc` VALUES ('294', 'Gold Hoe');
INSERT INTO `resource_desc` VALUES ('295', 'Seeds');
INSERT INTO `resource_desc` VALUES ('296', 'Wheat');
INSERT INTO `resource_desc` VALUES ('297', 'Bread');
INSERT INTO `resource_desc` VALUES ('298', 'Leather Cap');
INSERT INTO `resource_desc` VALUES ('299', 'Leather Tunic');
INSERT INTO `resource_desc` VALUES ('300', 'Leather Pants');
INSERT INTO `resource_desc` VALUES ('301', 'Leather Boots ');
INSERT INTO `resource_desc` VALUES ('302', 'Chain Helmet');
INSERT INTO `resource_desc` VALUES ('303', 'Chain Chestplate');
INSERT INTO `resource_desc` VALUES ('304', 'Chain Leggings');
INSERT INTO `resource_desc` VALUES ('305', 'Chain Boots');
INSERT INTO `resource_desc` VALUES ('306', 'Iron Helmet');
INSERT INTO `resource_desc` VALUES ('307', 'Iron Chestplate');
INSERT INTO `resource_desc` VALUES ('308', 'Iron Leggings');
INSERT INTO `resource_desc` VALUES ('309', 'Iron Boots');
INSERT INTO `resource_desc` VALUES ('310', 'Diamond Helmet');
INSERT INTO `resource_desc` VALUES ('311', 'Diamond Chestplate');
INSERT INTO `resource_desc` VALUES ('312', 'Diamond Leggings');
INSERT INTO `resource_desc` VALUES ('313', 'Diamond Boots');
INSERT INTO `resource_desc` VALUES ('314', 'Gold Helmet');
INSERT INTO `resource_desc` VALUES ('315', 'Gold Chestplate');
INSERT INTO `resource_desc` VALUES ('316', 'Gold Leggings');
INSERT INTO `resource_desc` VALUES ('317', 'Gold Boots');
INSERT INTO `resource_desc` VALUES ('318', 'Flint');
INSERT INTO `resource_desc` VALUES ('319', 'Raw Porkchop ');
INSERT INTO `resource_desc` VALUES ('320', 'Cooked Porkchop ');
INSERT INTO `resource_desc` VALUES ('321', 'Paintings');
INSERT INTO `resource_desc` VALUES ('322', 'Golden Apple ');
INSERT INTO `resource_desc` VALUES ('323', 'Sign');
INSERT INTO `resource_desc` VALUES ('324', 'Wooden door ');
INSERT INTO `resource_desc` VALUES ('325', 'Bucket');
INSERT INTO `resource_desc` VALUES ('326', 'Water bucket');
INSERT INTO `resource_desc` VALUES ('327', 'Lava bucket');
INSERT INTO `resource_desc` VALUES ('328', 'Minecart');
INSERT INTO `resource_desc` VALUES ('329', 'Saddle');
INSERT INTO `resource_desc` VALUES ('330', 'Iron door ');
INSERT INTO `resource_desc` VALUES ('331', 'Redstone');
INSERT INTO `resource_desc` VALUES ('332', 'Snowball');
INSERT INTO `resource_desc` VALUES ('333', 'Boat');
INSERT INTO `resource_desc` VALUES ('334', 'Leather');
INSERT INTO `resource_desc` VALUES ('335', 'Milk');
INSERT INTO `resource_desc` VALUES ('336', 'Clay Brick');
INSERT INTO `resource_desc` VALUES ('337', 'Clay');
INSERT INTO `resource_desc` VALUES ('338', 'Sugar Cane');
INSERT INTO `resource_desc` VALUES ('339', 'Paper');
INSERT INTO `resource_desc` VALUES ('340', 'Book');
INSERT INTO `resource_desc` VALUES ('341', 'Slimeball');
INSERT INTO `resource_desc` VALUES ('342', 'Storage Minecart');
INSERT INTO `resource_desc` VALUES ('343', 'Powered Minecart');
INSERT INTO `resource_desc` VALUES ('344', 'Egg');
INSERT INTO `resource_desc` VALUES ('345', 'Compass');
INSERT INTO `resource_desc` VALUES ('346', 'Fishing Rod');
INSERT INTO `resource_desc` VALUES ('347', 'Clock');
INSERT INTO `resource_desc` VALUES ('348', 'Glowstone Dust');
INSERT INTO `resource_desc` VALUES ('349', 'Raw Fish');
INSERT INTO `resource_desc` VALUES ('350', 'Cooked Fish');
INSERT INTO `resource_desc` VALUES ('351', 'Dye D ');
INSERT INTO `resource_desc` VALUES ('352', 'Bone');
INSERT INTO `resource_desc` VALUES ('353', 'Sugar');
INSERT INTO `resource_desc` VALUES ('354', 'Cake');
INSERT INTO `resource_desc` VALUES ('355', 'Bed');
INSERT INTO `resource_desc` VALUES ('356', 'Redstone Repeater');
INSERT INTO `resource_desc` VALUES ('357', 'Cookie');
INSERT INTO `resource_desc` VALUES ('358', 'Map');
INSERT INTO `resource_desc` VALUES ('2256', 'Gold Music Disc');
INSERT INTO `resource_desc` VALUES ('2257', 'Green Music Disc ');
INSERT INTO `resource_desc` VALUES ('9000', 'Player');
INSERT INTO `resource_desc` VALUES ('9001', 'Hand');
INSERT INTO `resource_desc` VALUES ('-1', 'None');

-- ----------------------------
-- View structure for `killchart`
-- ----------------------------
DROP VIEW IF EXISTS `killchart`;
CREATE ALGORITHM=UNDEFINED DEFINER=CURRENT_USER SQL SECURITY DEFINER VIEW `killchart` AS select `kills`.`id` AS `id`,`killer_creature`.`creature_name` AS `killer_creature_type`,`victim_creature`.`creature_name` AS `killed_creature_type`,`kill_types`.`description` AS `kill_type`,`resource_desc`.`description` AS `killed_using`,`projectiles`.`projectile_name` AS `projectile_name`,`kills`.`killed_by_uuid` AS `killed_by_uuid`,`kills`.`killed_uuid` AS `killed_uuid`,`kills`.`time` AS `time` from (((((`kills` join `creatures` `killer_creature` on((`kills`.`killed_by` = `killer_creature`.`id`))) join `creatures` `victim_creature` on((`kills`.`killed` = `victim_creature`.`id`))) join `kill_types` on((`kills`.`kill_type` = `kill_types`.`id`))) join `resource_desc` on((`kills`.`killed_using` = `resource_desc`.`resource_id`))) join `projectiles` on((`kills`.`killed_projectile` = `projectiles`.`id`))) ;

-- ----------------------------
-- View structure for `pickDropView`
-- ----------------------------
DROP VIEW IF EXISTS `pickDropView`;
CREATE ALGORITHM=UNDEFINED DEFINER=CURRENT_USER SQL SECURITY DEFINER VIEW `pickDropView` AS select `players`.`player_name` AS `player_name`,`pickup_drop`.`uuid` AS `uuid`,`resource_desc`.`description` AS `description`,`pickup_drop`.`num_pickedup` AS `num_pickedup`,`pickup_drop`.`num_dropped` AS `num_dropped` from ((`pickup_drop` join `players` on((`pickup_drop`.`uuid` = `players`.`uuid`))) join `resource_desc` on((`pickup_drop`.`item` = `resource_desc`.`resource_id`))) ;

-- ----------------------------
-- View structure for `playerblockview`
-- ----------------------------
DROP VIEW IF EXISTS `playerblockview`;
CREATE ALGORITHM=UNDEFINED DEFINER=CURRENT_USER SQL SECURITY DEFINER VIEW `playerblockview` AS select `players`.`player_name` AS `player_name`,`blocks`.`block_id` AS `block_id`,`blocks`.`num_destroyed` AS `num_destroyed`,`blocks`.`num_placed` AS `num_placed`,`resource_desc`.`description` AS `description` from ((`blocks` join `players` on((`players`.`uuid` = `blocks`.`uuid`))) join `resource_desc` on((`blocks`.`block_id` = `resource_desc`.`resource_id`))) ;

-- ----------------------------
-- Procedure structure for `incrementBlockDestroy`
-- ----------------------------
DROP PROCEDURE IF EXISTS `incrementBlockDestroy`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `incrementBlockDestroy`(`i_uuid` varchar(255),`i_blockid` int,`i_numtoadd` int)
BEGIN   INSERT INTO blocks (`uuid`,`block_id`,`num_destroyed`) VALUES(i_uuid,i_blockid,i_numtoadd) 		ON DUPLICATE KEY UPDATE `num_destroyed` = `num_destroyed` + i_numtoadd; END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `incrementBlockPlaced`
-- ----------------------------
DROP PROCEDURE IF EXISTS `incrementBlockPlaced`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `incrementBlockPlaced`(`i_uuid` varchar(255),`i_blockid` int,`i_numtoplace` int)
BEGIN 	INSERT INTO blocks (`uuid`,`block_id`,`num_placed`) VALUES(i_uuid,i_blockid,i_numtoplace) 		ON DUPLICATE KEY UPDATE `num_placed` = `num_placed` + i_numtoplace; END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `incrementDropped`
-- ----------------------------
DROP PROCEDURE IF EXISTS `incrementDropped`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `incrementDropped`(`i_uuid` varchar(255),`i_itemid` int,`i_numdropped` int)
BEGIN 	INSERT INTO pickup_drop (`uuid`,`item`,`num_dropped`) VALUES (i_uuid,i_itemid,i_numdropped) 		ON DUPLICATE KEY UPDATE `num_dropped` = `num_dropped` + i_numdropped; END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `incrementPickedup`
-- ----------------------------
DROP PROCEDURE IF EXISTS `incrementPickedup`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `incrementPickedup`(`i_uuid` varchar(255),`i_itemid` int, `i_numpickedup` int)
BEGIN 	INSERT INTO pickup_drop (`uuid`,`item`,`num_pickedup`) VALUES(i_uuid,i_itemid,i_numpickedup) 		ON DUPLICATE KEY UPDATE `num_pickedup` = `num_pickedup` + i_numpickedup; END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `loginPlayer`
-- ----------------------------
DROP PROCEDURE IF EXISTS `loginPlayer`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `loginPlayer`(`i_uuid` varchar(255))
BEGIN 	UPDATE players SET `online`='Y',`this_login`=UNIX_TIMESTAMP(),`num_logins`=`num_logins`+1 WHERE `uuid` = i_uuid; END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `logoutPlayer`
-- ----------------------------
DROP PROCEDURE IF EXISTS `logoutPlayer`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `logoutPlayer`(`i_uuid` varchar(255))
BEGIN UPDATE players SET `online` = 'N', `last_login` = `this_login`, `last_logout` = UNIX_TIMESTAMP() WHERE `uuid` = i_uuid; END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `newKill`
-- ----------------------------
DROP PROCEDURE IF EXISTS `newKill`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `newKill`(`i_killed` int,`i_killed_by` int,`i_kill_type` int,`i_killed_using` int,`i_killed_projectile` int,`i_killed_by_uuid` varchar(255),`i_killed_uuid` varchar(255))
BEGIN 	INSERT INTO kills (killed,killed_by,kill_type,killed_using,killed_projectile,killed_by_uuid,killed_uuid,`time`)  VALUES(i_killed,i_killed_by,i_kill_type,i_killed_using,i_killed_projectile,i_killed_by_uuid,i_killed_uuid,UNIX_TIMESTAMP()); END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `newPlayer`
-- ----------------------------
DROP PROCEDURE IF EXISTS `newPlayer`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `newPlayer`(`i_uuid` varchar(255),`i_player_name` varchar(255))
BEGIN  INSERT INTO players (`uuid`,`player_name`,`online`,`firstever_login`,`last_login`,`this_login`,`num_logins`)  VALUES (i_uuid,i_player_name,'Y',UNIX_TIMESTAMP(),UNIX_TIMESTAMP(),UNIX_TIMESTAMP(),1);  END
;;
DELIMITER ;
