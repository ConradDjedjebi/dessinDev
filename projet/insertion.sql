USE projet;

INSERT INTO `concours` 
	(`annee`, `saison`, `date_debut`, `date_fin`, `theme`) VALUES
		(2015, 'été', '2015-06-01', '2015-08-31', 'l''eau'),
		(2016, 'printemps', '2016-03-01', '2016-05-31', 'le rouge');

INSERT INTO `competiteur` (`nom`, `adresse`, `date_naissance`, `email`) VALUES
	('Thomas Fardeau', '38 rue Paul-François Proust 79000 Niort', '1994-06-07', 'Thomas.Fardeau@seio.org'),
	('Thomas Fouquet', '10 rue Saint Laud 49100 Angers', '1994-01-07', 'Thomas.Fouquet@seio.org'),
	('Romain Hamon', '11 rue Saint Léonard 49100 Angers', '1994-09-12', 'Romain.Hamon@seio.org'),
	('Aude Laurent', '1 Boulevard Janneteau 49100 Angers', '1994-08-12', 'Aude.Laurent@seio.org'),
	('Pierre Lemasson', '3 Boulevard de la liberté 49100 Angers', '1995-08-12', 'Pierre.Lemasson@seio.org'),
	('Vincent Pietroboni', '79 impasse du mur de la honte 49100 Angers', '1994-12-25', 'Vincent.Pietroboni@seio.org'),
	('Romain Pochet', '2 place Imbach 49100 Angers', '1994-12-25', 'Romain.Pochet@seio.org'),
	('Antoine Puchault', '19 boulevard Carnot 49100 Angers', '1994-12-25', 'Antoine.Puchault@seio.org');


INSERT INTO `evaluateur` (`nom`, `adresse`, `telephone`, `email`) VALUES
	('Antoine Regnier', '10 rue de la Paix 75000 Paris', '0666362833', 'antoine.regnier@seio.org'),
	('Remy Salim', '11 avenue des Champs-Elysées 75000 Paris', '0678141274', 'Remy.Salim@seio.org'),
	('Julien Le Théno', '11 avenue des Champs-Elysées 75000 Paris', '0606060606', 'Julien.thn@facebook.com'),
	('Timothée Pons', '12 Boulevard des Capucines 75000 Paris', '0707070707', 'Timothee.Pons@facebook.com'),
	('Pierre-Fraçois Sorly', '13 Boulevard Malherbes 75000 Paris', '0549283273', 'pf.sorly@facebook.com');

INSERT INTO `jury` (`ref_Concours`, `ref_Evaluateur`) VALUES
	(1,1),
	(1,2),
	(1,3),
	(2,4),
	(2,5),
	(2,1);

