/*
* Update resources for Minecraft 1.7_01
*/

-- -----------------------
-- Resource updates 
-- -----------------------

INSERT INTO `resource_desc` VALUES ('29', 'Piston (Sticky)');
INSERT INTO `resource_desc` VALUES ('33', 'Piston');
INSERT INTO `resource_desc` VALUES ('34', 'Piston Extension');
INSERT INTO `resource_desc` VALUES ('36', 'Piston Moving Piece');
INSERT INTO `resource_desc` VALUES ('359', 'Shears');

-- ---------------------------
-- Update DBVersion To 5
-- --------------------------

UPDATE `config` SET `dbVersion` = 5;