SELECT 
    concours.numero, annee, saison, theme,
    competiteur.nom,
    dessin.numero, dessin.commentaire,
    evaluation.note, evaluation.commentaire,
    evaluateur.nom
FROM dessin
    LEFT JOIN evaluation ON dessin.numero=ref_dessin
    LEFT JOIN Evaluateur ON Evaluateur.numero=ref_Evaluateur
    LEFT JOIN competiteur ON competiteur.numero=ref_Competiteur
    LEFT JOIN concours ON concours.numero=ref_Concours
WHERE etat="évalué";