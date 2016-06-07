USE projet;


DROP TABLE IF EXISTS `concours`;
CREATE TABLE `concours` (
	`numero` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`annee` YEAR NOT NULL ,
	`saison` VARCHAR(9) NOT NULL ,
	`theme` VARCHAR(255) NOT NULL ,
	`date_debut` DATE NOT NULL ,
	`date_fin` DATE NOT NULL ,
	CONSTRAINT ValidDates CHECK (date_debut<date_fin),
	PRIMARY KEY (numero));

DROP TABLE IF EXISTS `competiteur`;
CREATE TABLE `competiteur` (
	`numero` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`nom` VARCHAR(255) NOT NULL ,
	`addresse` VARCHAR(1023) NOT NULL ,
	`date_naissance` TIMESTAMP NOT NULL ,
	`email` VARCHAR(320) NOT NULL ,
	PRIMARY KEY (`numero`));


DROP TABLE IF EXISTS `dessin`;
CREATE TABLE `dessin` (
	`numero` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`commentaire` TEXT NOT NULL ,
	`le_dessin` MEDIUMBLOB NOT NULL ,
	`etat` TINYINT NOT NULL ,
	`date_remise` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	PRIMARY KEY (`numero`));

DROP TABLE IF EXISTS `evaluateur`;
CREATE TABLE `evaluateur` (
	`numero` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`nom` VARCHAR(255) NOT NULL ,
	`adresse` VARCHAR(1023) NOT NULL ,
	`telephone` INT UNSIGNED NOT NULL ,
	`email` VARCHAR(320) NOT NULL ,
	PRIMARY KEY (`numero`));


DROP TABLE IF EXISTS `Participe`;
CREATE TABLE `Participe` (
	`ref_Concours` INT UNSIGNED NOT NULL,
	`ref_Competiteur` INT UNSIGNED NOT NULL,
	PRIMARY KEY (ref_Competiteur, ref_Concours),
	FOREIGN KEY (ref_Concours) REFERENCES `Concours` (numero),
	FOREIGN KEY (ref_Competiteur) REFERENCES `Competiteur` (numero));

DROP TABLE IF EXISTS `Jury`;
CREATE TABLE `Jury` (
	`ref_Concours` INT UNSIGNED NOT NULL,
	`ref_Evaluateur` INT UNSIGNED NOT NULL,
	PRIMARY KEY (ref_Evaluateur, ref_Concours),
	FOREIGN KEY (ref_Concours) REFERENCES `Concours` (numero),
	FOREIGN KEY (ref_Evaluateur) REFERENCES `Evaluateur` (numero));

DROP TABLE IF EXISTS `Evaluation`;
CREATE TABLE `Evaluation` (
	`ref_Dessin` INT UNSIGNED NOT NULL,
	`ref_Evaluateur` INT UNSIGNED NOT NULL,
	`date_evaluation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	`note` TINYINT UNSIGNED NOT NULL ,
	`commentaire` TEXT NOT NULL ,
	PRIMARY KEY (ref_Evaluateur, ref_Dessin),
	FOREIGN KEY (ref_Dessin) REFERENCES `Dessin` (numero),
	FOREIGN KEY (ref_Evaluateur) REFERENCES `Evaluateur` (numero));
