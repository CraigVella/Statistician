/*
* Forgot to add default values for all new bigints
*/

-- ------------------------
-- Fixes for `blocks` table
-- ------------------------

ALTER TABLE `blocks` CHANGE `num_destroyed` `num_destroyed` BIGINT UNSIGNED DEFAULT '0' NOT NULL;
ALTER TABLE `blocks` CHANGE `num_placed` `num_placed` BIGINT UNSIGNED DEFAULT '0' NOT NULL;

-- ------------------------
-- Fixes for `pickup_drop` table
-- ------------------------

ALTER TABLE `pickup_drop` CHANGE `num_pickedup` `num_pickedup` BIGINT UNSIGNED DEFAULT '0' NOT NULL;
ALTER TABLE `pickup_drop` CHANGE `num_dropped` `num_dropped` BIGINT UNSIGNED DEFAULT '0' NOT NULL;

-- ------------------------
-- Fixes for `players` table
-- ------------------------

ALTER TABLE `players` CHANGE `num_secs_loggedon` `num_secs_loggedon` BIGINT UNSIGNED DEFAULT '0' NOT NULL;
ALTER TABLE `players` CHANGE `distance_traveled` `distance_traveled` BIGINT UNSIGNED DEFAULT '0' NOT NULL;
ALTER TABLE `players` CHANGE `distance_traveled_in_minecart` `distance_traveled_in_minecart` BIGINT UNSIGNED DEFAULT '0' NOT NULL;
ALTER TABLE `players` CHANGE `distance_traveled_in_boat` `distance_traveled_in_boat` BIGINT UNSIGNED DEFAULT '0' NOT NULL;
ALTER TABLE `players` CHANGE `distance_traveled_on_pig` `distance_traveled_on_pig` BIGINT UNSIGNED DEFAULT '0' NOT NULL;

-- ---------------------------
-- Update DBVersion To 3
-- --------------------------

UPDATE `config` SET `dbVersion` = 3;