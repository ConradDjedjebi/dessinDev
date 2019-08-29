package fr.eseo.gpi.tests.exercices.utilisateurs;
import fr.eseo.gpi.exercices.utilisateurs.Etudiant;

class EtudiantTest {

	public static void main (String[]args){


		Etudiant antoine = new Etudiant("Antoine","Gargot","Française",1995,3096);
		
		System.out.println(antoine.toString());
		
	}
}