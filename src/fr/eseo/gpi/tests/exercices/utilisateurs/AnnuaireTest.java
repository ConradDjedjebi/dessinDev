/**
 * @author Antoine du HAMEL
 * @section 1.7
 * @exercice 3
 */
package fr.eseo.gpi.tests.exercices.utilisateurs;

import fr.eseo.gpi.exercices.utilisateurs.*;
class AnnuaireTest {
	public static void main(String[] args) {
		Annuaire annuaire = new Annuaire();
		//Personne [] personnes = new Personne[5];

		for (int i=0; i<5; ++i) {
			//personnes[i] = ;
			annuaire.ajouter(new Personne("prenom"+i, "nom"+i));
		}

		// annuaire.supprimer("nom2");
		System.out.println(annuaire.supprimer("nom2").identite());
		annuaire.ajouter(new Personne("Antoine", "du HAMEL"));

		System.out.println(annuaire.chercher("du HAMEL").identite());
		System.out.println(annuaire.chercher("unknown").identite());

	}
}