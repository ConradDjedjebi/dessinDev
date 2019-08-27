package fr.eseo.gpi.tests.exercices.utilisateurs;

import fr.eseo.gpi.exercices.utilisateurs.Personne;

class PersonneTest {

	public static void main (String[]args){


		Personne darkVador = new Personne("Skywalker","Anakin",1581,"Tatooine");
		Personne test = new Personne ("Héléonörùê du p'uit","âè",1970,"italienne");
		Personne leDuff = new Personne ("LeDuff","Alain");


		System.out.println(Personne.getNbPersonnes());

		//Identités et userID

		System.out.println(darkVador.identite());
		System.out.println(darkVador.userID());

		System.out.println(test.identite());
		System.out.println(test.userID());

		System.out.println(leDuff.identite());
		System.out.println(leDuff.userID());




		
		

	}
	
}