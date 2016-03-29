/**
 * @author Antoine du HAMEL
 * @section 1.8
 * @exercice 1
 */
package fr.eseo.gpi.tests.exercices.utilisateurs;

import fr.eseo.gpi.exercices.utilisateurs.*;
class AnnuaireTabTest {
	public static void main(String[] args) {
		Annuaire annuaire = new Annuaire();
		//Personne [] personnes = new Personne[5];

		for (int i=0; i<5; ++i) {
			//personnes[i] = ;
			annuaire.ajouter(new Personne("prenom"+i, "nom"+i));
		}
		for (int i=0; i<5; ++i) {
			//personnes[i] = ;
			annuaire.ajouter(new Etudiant("prenomEleve"+i, "nomEleve"+i, 1995, i*i));
		}
		for (int i=0; i<5; ++i) {
			//personnes[i] = ;
			annuaire.ajouter(new Professeur("prenomProf"+i, "nomProf"+i, 1970+i));
		}

		// annuaire.supprimer("nom2");
		System.out.println(annuaire.supprimer("nom2").identite());
		annuaire.ajouter(new Personne("Antoine", "du HAMEL"));

		System.out.println(annuaire.chercher("du HAMEL").identite());
		try {
			System.out.println(annuaire.chercher("unknown").identite());
		}
		catch (NullPointerException e) {
			System.out.println("Impossible de trouver cette personne !");
		}

		for (Personne pers : annuaire.versTableau()) {
			System.out.println(pers.toString());
		}

	}
}