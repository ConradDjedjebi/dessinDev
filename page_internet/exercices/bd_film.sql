-- 1.
SELECT titre, code_pays FROM `film`;

SELECT nom, region FROM `internaute`;

SELECT nom, annee_naissance FROM `artiste` WHERE annee_naissance < 1960;

SELECT titre, resume FROM `film` WHERE code_pays='FR';

SELECT genre FROM `film` GROUP BY genre;


SELECT titre, genre, resume FROM `film` WHERE genre IN ('Western', 'drame') AND resume LIKE "%vie%";


SELECT * FROM `artiste` WHERE nom LIKE "C%";

SELECT nom, prenom FROM `internaute` WHERE region = 'bretagne';


SELECT * FROM `artiste` WHERE annee_naissance IS NULL;

SELECT nom, prenom, YEAR(NOW())-annee_naissance AS age FROM `artiste` WHERE annee_naissance IS NOT NULL;

-- 2.
SELECT titre, nom, prenom FROM film LEFT JOIN artiste ON id_artiste=id_realisateur;


SELECT nom, prenom FROM role LEFT JOIN artiste ON id_artiste=id_acteur WHERE nom_role = 'Vincent Vega';


SELECT titre, nom, prenom FROM notation 
	NATURAL JOIN internaute
	NATURAL JOIN film
	WHERE note=4;

SELECT nom, prenom FROM role
	NATURAL JOIN film
	LEFT JOIN artiste ON id_artiste=id_acteur
	WHERE film.titre = 'Volte/Face';

SELECT titre FROM film
	LEFT JOIN artiste AS rea ON id_artiste=id_realisateur
	NATURAL JOIN role
	WHERE rea.nom='Woo' AND role.id_acteur IN (SELECT id_artiste FROM artiste as acteur WHERE acteur.nom='Cage');

SELECT titre, nom_role FROM role
	NATURAL JOIN film
	LEFT JOIN artiste ON id_artiste=id_acteur
	WHERE artiste.nom = 'Depp' AND artiste.prenom = 'Jonny';

SELECT nom, prenom FROM role
	NATURAL JOIN film
	LEFT JOIN artiste ON id_artiste=id_acteur
	WHERE id_realisateur=id_acteur;

SELECT titre FROM role
	NATURAL JOIN film
	LEFT JOIN artiste ON id_artiste=id_acteur
	WHERE artiste.nom = 'Spacey' AND artiste.prenom = 'Kevin';

SELECT titre, rea.nom, acteur.nom, nom_role, pays.nom FROM film
	LEFT JOIN pays ON code_pays=code
	NATURAL JOIN role
	LEFT JOIN artiste AS rea ON rea.id_artiste=id_realisateur
	LEFT JOIN artiste AS acteur ON acteur.id_artiste=role.id_acteur
	;

-- 3.
SELECT COUNT(*) FROM film GROUP BY pays;

SELECT MIN(note), MAX(note), AVG(note) FROM notation
	NATURAL JOIN film
	WHERE film.titre='Alien';

SELECT COUNT(*) FROM role
	LEFT JOIN artiste AS acteur ON acteur.id_artiste=role.id_acteur
	WHERE acteur.nom = 'Depp' AND acteur.prenom = 'Jonny' AND nom_role="Sean Archer/Castor Troy";

SELECT email, AVG(note), COUNT(note) FROM notation GROUP BY email;

SELECT titre, resume FROM film
	WHERE genre='drame' ORDER BY LENGTH(resume);

SELECT COUNT(*) FROM film WHERE genre='Action';

SELECT min(annee_naissance), MAX(annee_naissance) FROM artiste
	WHERE annee_naissance>1900 AND annee_naissance<2001;

SELECT COUNT(*) FROM artiste GROUP BY annee_naissance;

SELECT titre, pays.nom, AVG(note) FROM film
	LEFT JOIN pays ON code=code_pays
	NATURAL JOIN notation
	GROUP BY titre
    HAVING AVG(note)>3;


SELECT titre, nom, prenom, AVG(note), MIN(note), MAX(note) FROM film
	NATURAL JOIN notation
	LEFT JOIN artiste ON id_realisateur=id_artiste
	GROUP BY titre;

-- 4.
SELECT titre, pays.nom FROM film
	LEFT JOIN pays ON code=code_pays
	WHERE resume IS NULL;

SELECT * FROM film 
	WHERE id_film NOT IN (SELECT id_film FROM role);

SELECT nom, prenom FROM artiste
	WHERE id_artiste NOT IN (SELECT id_realisateur FROM film);

SELECT nom, prenom FROM artiste
	WHERE id_artiste NOT IN (SELECT id_acteur FROM role);

SELECT internaute.* FROM notation
		NATURAL JOIN internaute
		NATURAL JOIN film
		WHERE film.annee=1999

SELECT * FROM artiste
	WHERE id_artiste NOT IN (
		SELECT id_acteur FROM role
			NATURAL JOIN film
			WHERE code_pays='FR');

SELECT nom, prenom FROM artiste
	WHERE id_artiste NOT IN (
		SELECT id_realisateur FROM film
		UNION
		SELECT id_acteur FROM role);

SELECT nom, prenom FROM internaute
	WHERE region IN ('bretagne', 'pays de la loire');

SELECT nom, prenom, region FROM internaute
	WHERE region NOT LIKE 'pays de la loire';

SELECT nom_role FROM role
	WHERE nom_role IN (SELECT titre FROM film);

-- 5.
SELECT * FROM artiste AS art1
	INNER JOIN artiste AS art2 ON art1.annee_naissance=art2.annee_naissance AND art1.id_artiste>art2.id_artiste;

SELECT * FROM film AS f1
	INNER JOIN film AS f2 ON f1.id_realisateur=f2.id_realisateur AND f1.id_film>f2.id_film;

SELECT f1.* FROM film AS f1
	INNER JOIN film AS f2
		ON f2.titre='Alien' AND f1.genre=f2.genre AND f2.code_pays=f1.code_pays;

SELECT nom, nom_role, titre FROM role
	NATURAL JOIN film
	LEFT JOIN artiste ON id_acteur=id_artiste
	WHERE id_artiste IN (
		SELECT id_realisateur FROM film
		UNION
		SELECT id_acteur FROM role)
	AND id_artiste NOT IN (SELECT id_acteur FROM role
		NATURAL JOIN film
		LEFT JOIN artiste ON id_artiste=id_acteur
		WHERE id_realisateur=id_acteur);

SELECT nom, prenom, annee_naissance FROM artiste WHERE annee_naissance IS NOT NULL ORDER BY annee_naissance LIMIT 1;

SELECT nom, prenom, region, YEAR(NOW())-annee_naissance AS age FROM internaute
	NATURAL JOIN notation
	WHERE NOT EXISTS (
		SELECT email FROM notation
		WHERE id_film NOT IN (SELECT id_film FROM film)
		)
	GROUP BY email;

SELECT nom, prenom, region, YEAR(NOW())-annee_naissance AS age FROM notation
	NATURAL JOIN film
	NATURAL JOIN internaute
	WHERE EXISTS (SELECT id_artiste AS id_realisateur FROM artiste WHERE nom='Scott' AND prenom='Ridley');

SELECT titre, CONCAT(artiste.prenom, ' ', artiste.nom) AS realisateur, AVG(note), region
	FROM film
	NATURAL JOIN notation
	NATURAL JOIN internaute
	LEFT JOIN artiste ON id_realisateur=id_artiste
	GROUP BY titre, region;

-- Fonctionne pas
SELECT nom, annee_naissance FROM internaute
 WHERE NOT EXISTS (
 	SELECT * FROM film
 		WHERE NOT EXISTS (
 			SELECT * FROM notation
	 		WHERE (film.resume NOT LIKE '%vie%' AND film.resume NOT LIKE '%mort%') OR film.id_film=notation.id_film));

SELECT region, COUNT(*) AS nb_films FROM (
	SELECT region FROM notation
		NATURAL JOIN internaute
		GROUP BY id_film, region) AS t
	GROUP BY region
	ORDER BY nb_films DESC
	LIMIT 1;
