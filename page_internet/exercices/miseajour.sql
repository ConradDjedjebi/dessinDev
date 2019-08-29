UPDATE `hotel` SET `categorie` = '1' WHERE `hotel`.`numhot` = 1;

UPDATE `client` SET `adrcli` = '1' WHERE `client`.`numcli` = 1;

UPDATE `reservation` SET `datefin` = '2016-07-21 14:44:57', `numch` = '4' WHERE `reservation`.`numcli` = 4 AND `reservation`.`numhot` = 3 AND `reservation`.`numch` = 1 AND `reservation`.`datedeb` = '2016-06-12 14:44:57';

DELETE FROM reservation WHERE datedeb > 2016-12-15 AND datedeb < 2017-01-10
