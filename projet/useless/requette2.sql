SELECT dessin.numero, note, competiteur.nom, concours.saison, concours.theme
    FROM dessin
    LEFT JOIN evaluation ON dessin.numero=ref_dessin
    LEFT JOIN competiteur ON competiteur.numero=ref_Competiteur
    LEFT JOIN concours ON concours.numero=ref_Concours
    WHERE YEAR(date_evaluation)=2016
    ORDER BY note DESC;