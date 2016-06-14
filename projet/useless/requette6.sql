-- Nombre de dessins par concours

SELECT CONCAT(theme, ' ', saison, ' ', annee) AS "Nom du Concours", COUNT(*) FROM dessin
    LEFT JOIN concours ON concours.numero=ref_Concours
	GROUP BY ref_Concours;
