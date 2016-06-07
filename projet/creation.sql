USE projet;

DROP TABLE IF EXISTS Evaluation;
DROP TABLE IF EXISTS Dessin;
DROP TABLE IF EXISTS Jury;
DROP TABLE IF EXISTS Participe;
DROP TABLE IF EXISTS Evaluateur;
DROP TABLE IF EXISTS Competiteur;
DROP TABLE IF EXISTS Concours;


CREATE TABLE Concours (
	numero INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	annee YEAR NOT NULL ,
	saison VARCHAR(9) NOT NULL ,
	theme VARCHAR(255) NOT NULL ,
	date_debut DATE NOT NULL ,
	date_fin DATE NOT NULL ,
	CONSTRAINT ValidDates CHECK (date_debut<date_fin),
	PRIMARY KEY (numero));

CREATE TABLE Competiteur (
	numero INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	nom VARCHAR(255) NOT NULL ,
	addresse VARCHAR(1023) NOT NULL ,
	date_naissance DATE NOT NULL ,
	email VARCHAR(320) NOT NULL ,
	PRIMARY KEY (numero));



CREATE TABLE Evaluateur (
	numero INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	nom VARCHAR(255) NOT NULL ,
	adresse VARCHAR(1023) NOT NULL ,
	telephone INT UNSIGNED NOT NULL ,
	email VARCHAR(320) NOT NULL ,
	PRIMARY KEY (numero));



CREATE TABLE Participe (
	ref_Concours INT UNSIGNED NOT NULL,
	ref_Competiteur INT UNSIGNED NOT NULL,
	PRIMARY KEY (ref_Competiteur, ref_Concours),
	FOREIGN KEY (ref_Concours) REFERENCES Concours (numero),
	FOREIGN KEY (ref_Competiteur) REFERENCES Competiteur (numero));


CREATE TABLE Jury (
	ref_Concours INT UNSIGNED NOT NULL,
	ref_Evaluateur INT UNSIGNED NOT NULL,
	PRIMARY KEY (ref_Evaluateur, ref_Concours),
	FOREIGN KEY (ref_Concours) REFERENCES Concours (numero),
	FOREIGN KEY (ref_Evaluateur) REFERENCES Evaluateur (numero));


CREATE TABLE Dessin (
	numero INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	ref_Competiteur INT UNSIGNED NOT NULL,
	ref_Concours INT UNSIGNED NOT NULL,
	commentaire TEXT NOT NULL ,
	le_dessin  CHAR(50) NOT NULL,
	etat TINYTEXT NOT NULL ,
	date_remise TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	PRIMARY KEY (numero),
	FOREIGN KEY (ref_Competiteur) REFERENCES Competiteur (numero),
	FOREIGN KEY (ref_Concours) REFERENCES Concours (numero));


CREATE TABLE Evaluation (
	ref_Dessin INT UNSIGNED NOT NULL,
	ref_Evaluateur INT UNSIGNED NOT NULL,
	date_evaluation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	note TINYINT UNSIGNED NOT NULL ,
	commentaire TEXT NOT NULL ,
	PRIMARY KEY (ref_Evaluateur, ref_Dessin),
	FOREIGN KEY (ref_Dessin) REFERENCES Dessin (numero),
	FOREIGN KEY (ref_Evaluateur) REFERENCES Evaluateur (numero));