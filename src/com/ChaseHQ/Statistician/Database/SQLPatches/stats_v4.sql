/*
* Adds the ability to up the amount of max players ever logged on
*/

-- -----------------------------------------------
-- Add the max_players_ever_online to server table
-- -----------------------------------------------

ALTER TABLE `server` ADD COLUMN `max_players_ever_online` INT(11) UNSIGNED DEFAULT '0' NOT NULL AFTER `shutdown_time`;
ALTER TABLE `server` ADD COLUMN `max_players_ever_online_time` INT(11) UNSIGNED DEFAULT '0' NOT NULL AFTER `max_players_ever_online`;
UPDATE `server` SET `max_players_ever_online_time` = UNIX_TIMESTAMP();

-- ----------------------------------
-- New updateMostEverOnline Procedure
-- ----------------------------------

DROP PROCEDURE IF EXISTS `updateMostEverOnline`;
DELIMITER ;;
CREATE DEFINER=CURRENT_USER PROCEDURE `updateMostEverOnline`()

BEGIN

	DECLARE _currentMostEverOnline INT UNSIGNED;
	DECLARE _currentOnline INT UNSIGNED;

	SELECT `max_players_ever_online` INTO _currentMostEverOnline FROM `server`;
	SELECT count(*) INTO _currentOnline FROM `players` WHERE `online` = 'Y';

	UPDATE `server` SET `max_players_ever_online` = IF (_currentOnline > _currentMostEverOnline,  _currentOnline, _currentMostEverOnline);
	UPDATE `server` SET `max_players_ever_online_time` = IF (_currentOnline >= _currentMostEverOnline,  UNIX_TIMESTAMP(), `max_players_ever_online_time`);

END
;;
DELIMITER ;

-- ---------------------------
-- Update DBVersion To 4
-- --------------------------

UPDATE `config` SET `dbVersion` = 4;