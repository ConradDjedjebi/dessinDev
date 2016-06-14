/* Afficher les candidats ayant remis leur dessin pour un concours donné */

SELECT Competiteur.nom, Concours.theme, Concours.saison, Concours.annee
FROM Concours, Dessin, Evaluation, Competiteur
WHERE Concours.numero = '1' AND Dessin.ref_Competiteur = Competiteur.numero
AND Dessin.ref_Concours = Concours.numero AND Dessin.etat = "déposé";