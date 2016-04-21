package personne;

public class PersonneTestPrive {

	public static void main(String[] args) {
		Personne [] listePersonne = new Personne [3];
		/*Personne personne1 = new Personne("Djédjébi","Sènôu","Béninoise",1995);
		Personne personne2 = new Personne("Djédjébi","Elphège","Française",1996);
		Personne personne3 = new Personne("Djédjébi","Cônrad","Hollandaise",1997);*/
		
		listePersonne[0] = new Personne("Sènôu", "Djédjébi");
		listePersonne[1] = new Personne("Elphège", "Djédjébi");
		listePersonne[2] = new Personne("Cônrad", "Djédjébi");
		String s ="" + Personne.getNbPersonnes();
		System.out.println('\n'+ s +'\n');		
	
		/*individu1.setNom("Djédjébi");
		individu1.setPrenom("Sènôu");
		individu1.setAnneeDeNaissance(1995);
		individu1.setNationalite("Béninoise");
	
		individu2.setNom("Djédjébi");
		individu2.setPrenom("Elphège");
		individu2.setAnneeDeNaissance(1996);
		individu2.setNationalite("Française");
	
		individu3.setNom("Djédjébi");
		individu3.setPrenom("Cônrad");
		individu3.setAnneeDeNaissance(1997);
		individu3.setNationalite("Hollandaise");*/
		
		
		for (int i=0; i<3; i++){
			System.out.println('\n'+listePersonne[i].toString()+'\n');	
			listePersonne[i].userID();
		}
	}
}
