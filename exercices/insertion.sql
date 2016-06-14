INSERT INTO `station` (`nomsta`, `altitude`, `region`) VALUES 
	('Chambéry', 2200, 'Haute-Savoie'),
	('Mont Blanc', 4908, 'Haute-Savoie');

INSERT INTO `hotel` (`nomhot`, `numsta`, `categorie`) VALUES 
	('Hot California', 1, 2),
	('Heisenberg''s palace', 1, 3),
	('Carlton', 2, 4),
	('BTM', 2, 5);

INSERT INTO `chambre` (`numch`, `numhot`, `nblits`) VALUES
	(1,1,1),
	(2,1,5),
	(3,1,3),
	(1,2,2),
	(2,2,2),
	(3,2,3),
	(1,3,3),
	(2,3,1),
	(3,3,2),
	(1,4,2),
	(2,4,4),
	(3,4,1);

INSERT INTO `client` (`nomcli`, `adrcli`, `telcli`) VALUES 
	('Dupont', 'Dans le placard sous l''escalier
4, Privet Drive
Little Whinging
Surrey', 064020156),
	('Dupond', 'Dans le placard sous l''escalier
4, Privet Drive
Little Whinging
Surrey', 074020156),
	('Tournesol', 'Boucherie Sanzot
Moulisart
Belgique', 065020156),
	('Haddock', 'Château de Moulinsart
Moulisart
Belgique', 066020156);

INSERT INTO `reservation` (`numcli`, `numhot`, `numch`, `datedeb`, `datefin`, `nbpers`) VALUES
	(1, 1, 1, NOW(), NOW() + INTERVAL 1 WEEK, 11),
	(1, 2, 1, NOW() - INTERVAL 2 MONTH, NOW(), 2),
	(2, 2, 2, NOW(), NOW() + INTERVAL 2 DAY, 4),
	(2, 1, 3, NOW() - INTERVAL 2 DAY, NOW(), 4),
	(3, 4, 1, NOW() - INTERVAL 2 DAY, NOW(), 4),
	(3, 3, 1, NOW() - INTERVAL 2 DAY, NOW(), 4),
	(4, 3, 1, NOW() + INTERVAL 1 MONTH, NOW() + INTERVAL 2 MONTH, 4),
	(4, 3, 1, NOW() + INTERVAL 5 DAY, NOW() + INTERVAL 2 WEEK, 4);
