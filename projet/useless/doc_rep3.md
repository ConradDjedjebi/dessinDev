# Semestre 6:  
## Projet Informatique GPI2
### Partie : Bases de Données

#### Un système d’information pour la gestion des concours de dessins


##### Document : Réponses partie 3
##### L’interrogation de la base de données

Groupe : *I1 - A4*.

| Nom        | Prénom       |
| ------------- | ------------- |
| DJEDJEBI      | Elphège |
| du HAMEL      | Antoine     |

###### Question 6 : **Les requêtes SQL**
----------------------------------------------

Proposez 8 requêtes SQL permettant de répondre aux attentes présentées dans le cahier de charges ou que vous jugeriez utiles à l'exploitation du système. Chaque requête sera exprimée en français puis en langage SQL.  

###### Requête 1

Afficher le nom et l’adresse de tous les compétiteurs qui ont participé au concours en 2015 à la session d’été.  Vous afficherez aussi le thème du concours la date de début et la date de fin.

```sql
SELECT nom, adresse FROM participe
    LEFT JOIN competiteur ON competiteur.numero=ref_Competiteur
    LEFT JOIN concours ON concours.numero=ref_Concours
    WHERE annee = 2015 AND saison='été';
```

###### Requête 2

Afficher par ordre décroissant de la note tous les dessins qui ont été évalués en 2016. Vous afficherez les informations suivantes: le numéro du dessin et la note attribuée, le nom du compétiteur, la saison et le thème du concours.

```sql
SELECT dessin.numero, note, competiteur.nom, concours.saison, concours.theme
    FROM dessin
    LEFT JOIN evaluation ON dessin.numero=ref_dessin
    LEFT JOIN competiteur ON competiteur.numero=ref_Competiteur
    LEFT JOIN concours ON concours.numero=ref_Concours
    WHERE YEAR(date_evaluation)=2016
    ORDER BY note DESC;
```

###### Requête 3

Pour cette dernière requête on vous demande d’afficher des informations sur tous les dessins qui ont été évalués et qui sont stockés dans la base. Voici les informations qu’on souhaite voir affichés : le numéro, l’année, la saison (session) et le thème du concours dans lequel le dessin a été évalué ; le nom du compétiteur ayant proposé le dessin ; le numéro et le commentaire du dessin fait par le compétiteur ; la note et le commentaire de l’évaluation ; le nom de l’évaluateur.

```sql
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
```

###### Requête 4

Nom, adresse et l’âge des compétiteurs qui ont participé à tous les concours qui ont été organisés.

```sql
SELECT nom, adresse, TIMESTAMPDIFF(YEAR, date_naissance, NOW()) AS age
    FROM Competiteur, Participe
    WHERE Participe.ref_Competiteur = Competiteur.numero
    HAVING COUNT(DISTINCT Participe.ref_Concours) = (SELECT COUNT(*) FROM concours);
```

###### Requête 5

Afficher la liste des competiteurs évalués chaque evaluateur ainsi que la note moyenne obtenue avec celui-ci

```sql
SELECT Competiteur.nom, Evaluateur.nom, AVG(Evaluation.note) as "Moyenne"
FROM Competiteur, Evaluateur, Evaluation, Dessin
WHERE Evaluation.ref_Evaluateur = Evaluateur.numero AND Competiteur.numero = Dessin.ref_Competiteur
AND Evaluation.ref_Dessin = Dessin.numero
ORDER BY Evaluateur.numero ASC;
```

###### Requête 6

Afficher le nombre de dessins pour chaque concours

```sql
SELECT CONCAT(theme, ' ', saison, ' ', annee) AS "Nom du Concours", COUNT(*) FROM dessin
    LEFT JOIN concours ON concours.numero=ref_Concours
    GROUP BY ref_Concours;
```

###### Requête 7

Note minimale, maximale et la moyenne des notes affectées aux dessins d'un concours dont le numéro est donné

```sql
SELECT MIN(note), MAX(note), AVG(note) FROM dessin
    LEFT JOIN evaluation ON dessin.numero=ref_dessin
    WHERE ref_Concours=1;
```

###### Requête 8

Afficher les candidats ayant remis leur dessin pour un concours donné

```sql
SELECT Competiteur.nom, Concours.theme, Concours.saison, Concours.annee
FROM Concours, Dessin, Evaluation, Competiteur
WHERE Concours.numero = '1' AND Dessin.ref_Competiteur = Competiteur.numero
AND Dessin.ref_Concours = Concours.numero AND Dessin.etat = "déposé";
```
