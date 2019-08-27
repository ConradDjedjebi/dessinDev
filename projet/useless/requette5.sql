/* Afficher la liste des competiteurs évalués chaque evaluateur ainsi que la note moyenne obtenue avec celui-ci */

SELECT Competiteur.nom, Evaluateur.nom, AVG(Evaluation.note) as "Moyenne"
FROM Competiteur, Evaluateur, Evaluation, Dessin
WHERE Evaluation.ref_Evaluateur = Evaluateur.numero AND Competiteur.numero = Dessin.ref_Competiteur
AND Evaluation.ref_Dessin = Dessin.numero
ORDER BY Evaluateur.numero ASC;