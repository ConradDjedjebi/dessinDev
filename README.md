# GPI 2 - Be An Artist

Grand Projet Informatique du semestre S6 pour le Groupe ESEO.

### Notre travail

#### Les contraintes à respecter lors de la programmation 

 * Un concours doit avoir au moins cinq compétiteurs inscrits
 * Tout dessin proposé doit être évalué par deux évaluateurs
 * Au plus 3 dessins par compétiteur dans un même concours
 * Un évaluateur ne peut pas évaluer plus de deux dessins d'un même compétiteur lors d'un concours
 * Un membre du jury peut évaluer au plus 8 dessins lors d'un concours.


#### Les améliorations développées

 * Vérification des contraintes sus-décrites
 * Utilisation de l'AJAX pour les formulaires
     - Le formulaire ne se vide pas à la moindre erreur
     - Cela permet d'afficher un message pour faire patienter l'utilisateur
 * Ajout d'un menu latéral pour faciliter la navigation
 * Ajout de l'aperçu des images lors de l'ajout d'une note
 * Ajout du nom du competiteur sur le tableau des résultats
 * Sécurisation des requêtes SQL pour empêcher les injections grâce à la librairie Prep
 * Compression des fichiers JavaScript
     - Optimisation du temps de téléchargement des scripts
     - Interprétation plus rapide pour le navigateur
 * Protection contre la faille XSS
 * Ajout d'un formulaire de connexion

### Modifications effectuées sur la base de données

Lors du développement de l'application web, nous nous sommes aperçu que certains points pouvaient être améliorés dans notre structure :

 * Le nom de l'attribut `Competiteur.addresse` a été renomé en `Competiteur.adresse`
 * La contrainte `NOT NULL` a été retirée des attributs `Dessin.commentaire` et `Dessin.descriptif`
 * Le type `VARCHAR` du champ `Concours.saison` remplacé par `ENUM`
 * Le type `VARCHAR` du champ `Dessin.etat` remplacé par `ENUM`
 * Le contrainte d'unicité pour ne permettre qu'un seul concours par an et pour une session donnée a été rajoutée

### Pour nous aider dans notre travail

Conseils :

 * Ajouter le mode debug sur la VM. Cela permet d'afficher les erreurs PHP dans le code html (voir post de Shhel sur le forum)

#### Les librairies externes

Nous avons réutiliser du code disponible sur internet pour améliorer la navigation et le confort de l'utilisateur sur notre application web. Toutes les librairies que nous avons utilisées sont disponibles gratuitement. Elles ne sont pas essentielles au fonctionnement de notre application, mais l'améliore significativement.

Côté serveur (PHP) :

 * [Prep](http://bitbucket.org/aduh95/prep)
 * [JavaScriptPacker](http://oliclic.free.fr/php/javascript-packer/)

Côté client (CSS / JS)

 * [jQuery](http://api.jquery.com)
 * [Bootstrap](http://getbootstrap.com)
 * [DatePicker](http://github.com/eternicode/bootstrap-datepicker)
 * [jQuery File Download Plugin](https://github.com/johnculviner/jquery.fileDownload)
