-- Note minimale, maximale et la moyenne des notes affectées aux dessins d'un concours dont le numéro est donné

SELECT MIN(note), MAX(note), AVG(note) FROM dessin
    LEFT JOIN evaluation ON dessin.numero=ref_dessin
	WHERE ref_Concours=1;