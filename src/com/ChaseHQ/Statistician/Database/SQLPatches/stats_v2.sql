/*
  This update is to change All potential Rolloverable Ints
  To Unsigned BigInts covering all future recordings
*/

SET FOREIGN_KEY_CHECKS=0;

-- ------------------------
-- New `server` table
-- ------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server` (
`startup_time` int(11) NOT NULL,
`shutdown_time` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `server` VALUES (0,UNIX_TIMESTAMP());

-- ------------------------
-- Fixes for `blocks` table
-- ------------------------

ALTER TABLE `blocks` CHANGE `num_destroyed` `num_destroyed` BIGINT UNSIGNED;
ALTER TABLE `blocks` CHANGE `num_placed` `num_placed` BIGINT UNSIGNED;

-- ------------------------
-- Fixes for `pickup_drop` table
-- ------------------------

ALTER TABLE `pickup_drop` CHANGE `num_pickedup` `num_pickedup` BIGINT UNSIGNED;
ALTER TABLE `pickup_drop` CHANGE `num_dropped` `num_dropped` BIGINT UNSIGNED;

-- ------------------------
-- Fixes for `players` table
-- ------------------------

ALTER TABLE `players` CHANGE `num_secs_loggedon` `num_secs_loggedon` BIGINT UNSIGNED;
ALTER TABLE `players` CHANGE `distance_traveled` `distance_traveled` BIGINT UNSIGNED;
ALTER TABLE `players` CHANGE `distance_traveled_in_minecart` `distance_traveled_in_minecart` BIGINT UNSIGNED;
ALTER TABLE `players` CHANGE `distance_traveled_in_boat` `distance_traveled_in_boat` BIGINT UNSIGNED;
ALTER TABLE `players` CHANGE `distance_traveled_on_pig` `distance_traveled_on_pig` BIGINT UNSIGNED;

-- ---------------------------
-- New pluginStartup Procedure
-- ---------------------------

DROP PROCEDURE IF EXISTS `pluginStartup`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `pluginStartup`()
BEGIN 
 UPDATE `players` SET `online` = 'N';
 UPDATE `server` SET `startup_time` = UNIX_TIMESTAMP();
END
;;
DELIMITER ;

-- ----------------------------
-- New pluginShutdown Procedure
-- ----------------------------

DROP PROCEDURE IF EXISTS `pluginShutdown`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `pluginShutdown`()
BEGIN 
 UPDATE `players` SET `online` = 'N';
 UPDATE `server` SET `shutdown_time` = UNIX_TIMESTAMP();
END
;;
DELIMITER ;

-- ---------------------------
-- Update DBVersion To 2
-- --------------------------

UPDATE `config` SET `dbVersion` = 2;