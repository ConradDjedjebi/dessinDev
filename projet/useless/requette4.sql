SELECT nom, addresse, TIMESTAMPDIFF(YEAR, date_naissance, NOW()) AS age
    FROM Competiteur, Participe
    WHERE Participe.ref_Competiteur = Competiteur.numero
    HAVING COUNT(DISTINCT Participe.ref_Concours) = (SELECT COUNT(*) FROM concours);