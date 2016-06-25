# GPI 2 - Be An Artist

Grand Projet Informatique du semestre S6 pour le Groupe ESEO.


Les contraintes à respecter lors de la programmation : 

 * Un concours doit avoir au moins cinq compétiteurs inscrits
 * Tout dessin proposé doit être évalué par deux évaluateurs
 * Au plus 3 dessins par compétiteur dans un même concours
 * Un évaluateur ne peut pas évaluer plus de deux dessins d'un même compétiteur lors d'un concours
 * Un membre du jury peut évaluer au plus 8 dessins lors d'un concours.
 * Qu'un seul concours par an et pour une session donnée


Les améliorations développées :

 * Utilisation de l'AJAX pour les formulaires pour éviter de reset le formulaire à la moindre erreur
 * Ajout d'un menu latéral pour faciliter la navigation
 * Ajout de l'aperçu des images lors de l'ajout d'une note
 * Ajout du nom du competiteur sur le tableau des résultats

Modifications effectuées sur la base de données : 

 * Le nom de l'attribut `Competiteur.addresse` a été renomé en `Competiteur.adresse`
 * La contrainte `NOT NULL` a été retirée des attributs `Dessin.commentaire` et `Dessin.descriptif`
 * Le type `VARCHAR` du champ `Concours.saison` remplacé par `ENUM`
 * Le type `VARCHAR` du champ `Dessin.etat` remplacé par `ENUM`



Conseils :

 * Ajouter le mode debug sur la VM. Cela permet d'afficher les erreurs qu'il y a dans le code html (voir post de Shhel sur le forum)
