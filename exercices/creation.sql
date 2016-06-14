DROP TABLE IF EXISTS chambre;
CREATE TABLE Chambre (
  numhot int(11) NOT NULL,
  numch int(11) NOT NULL AUTO_INCREMENT,
  nblits smallint(5) UNSIGNED NOT NULL,
  PRIMARY KEY (numch, numhot),
  FOREIGN KEY (numhot) REFERENCES `Hotel` (numhot)
);

DROP TABLE IF EXISTS client;
CREATE TABLE `client` (
  numcli int(11) NOT NULL AUTO_INCREMENT,
  nomcli varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  adrcli varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL,
  telcli int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (numcli)
);

DROP TABLE IF EXISTS hotel;
CREATE TABLE Hotel (
  numhot int(11) NOT NULL AUTO_INCREMENT,
  nomhot varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  numsta int(11) NOT NULL,
  categorie tinyint(3) UNSIGNED NOT NULL,
  CONSTRAINT ValidCategorie CHECK (categorie<6 AND categorie>1),
  PRIMARY KEY (numhot),
  FOREIGN KEY (numsta) REFERENCES `Station` (numsta)
);

DROP TABLE IF EXISTS reservation;
CREATE TABLE Reservation (
  numcli int(11) NOT NULL,
  numhot int(11) NOT NULL,
  numch int(11) NOT NULL,
  datedeb timestamp NOT NULL,
  datefin timestamp NOT NULL,
  nbpers tinyint(3) UNSIGNED NOT NULL,
  CONSTRAINT ValidDates CHECK (datedeb<datefin),
  CONSTRAINT ValidNbPers CHECK (nbpers<11),
  PRIMARY KEY (datedeb, numcli, numch, numhot),
  FOREIGN KEY (numcli) REFERENCES `Client` (numcli),
  FOREIGN KEY (numch) REFERENCES `Chambre` (numch),
  FOREIGN KEY (numhot) REFERENCES `Hotel` (numhot)
);

DROP TABLE IF EXISTS station;
CREATE TABLE Station (
  numsta int(11) NOT NULL AUTO_INCREMENT,
  nomsta varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  altitude smallint(6) NOT NULL,
  region varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (numsta)
);