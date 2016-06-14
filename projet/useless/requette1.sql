SELECT nom, addresse FROM participe
    LEFT JOIN competiteur ON competiteur.numero=ref_Competiteur
    LEFT JOIN concours ON concours.numero=ref_Concours
    WHERE annee = 2015 AND saison='été';