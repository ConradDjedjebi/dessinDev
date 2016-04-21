package personne;

import static org.junit.Assert.*;

import org.junit.Test;

import personne.Personne;

public class PersonneTest {

	
	/**
	 * Test constructeur Personne(prenom, nom)
	 */
	@Test
	
	public void testconstructeur1() {
				
		Personne [] listePersonne = new Personne [3];
		
		listePersonne[0] = new Personne("Sènôu", "Djédjébi");
			
		String Observé = listePersonne[0].toString();
		String Attendu ="Sènôu Djédjébi - Année de Naissance : 0 - Nationalité : français - Identifiant : djedjsenXX";
		assertEquals("Iidentité attendu attendu", Attendu, Observé);
	}
	
	
	
	
	/**
	 * Test constructeur Personne(prenom, nom, anneeDeNaissance).
	 */
	@Test
	
	public void testconstructeur2() {
				
		Personne [] listePersonne = new Personne [3];
		
		
		listePersonne[0] = new Personne("Sènôu", "Djédjébi", 1995);
		//listePersonne[1] = new Personne("Elphège", "Djédjébi", "Francais", 1995);
		//listePersonne[2] = new Personne("Cônrad", "Djédjébi", "Hollandais", 1995);
			
		String Observé = listePersonne[0].toString();
		String Attendu ="Sènôu Djédjébi - Année de Naissance : 1995 - Nationalité : français - Identifiant : djedjsen95";
		assertEquals("Iidentité attendu attendu", Attendu, Observé);
	}
	
	
	
	
	/**
	 * Test constructeur Personne(prenom, nom, nationalite).
	 */
	@Test
	
	public void testconstructeur3() {
				
		Personne [] listePersonne = new Personne [3];
		
		
		listePersonne[0] = new Personne("Sènôu", "Djédjébi", "béninois");
		//listePersonne[1] = new Personne("Elphège", "Djédjébi", "Francais", 1995);
		//listePersonne[2] = new Personne("Cônrad", "Djédjébi", "Hollandais", 1995);
			
		String Observé = listePersonne[0].toString();
		String Attendu ="Sènôu Djédjébi - Année de Naissance : 0 - Nationalité : béninois - Identifiant : djedjsenXX";
		assertEquals("Iidentité attendu attendu", Attendu, Observé);
	}
	
	
	/**
	 * Test constructeurs Personne(prenom, nom, nationalite, anneeDeNaissance).
	 */
	@Test
	
	public void testconstructeur4() {
				
		Personne [] listePersonne = new Personne [3];
		
		
		listePersonne[0] = new Personne("Sènôu", "Djédjébi", "béninois", 1995);
		//listePersonne[1] = new Personne("Elphège", "Djédjébi", "Francais", 1995);
		//listePersonne[2] = new Personne("Cônrad", "Djédjébi", "Hollandais", 1995);
			
		String Observé = listePersonne[0].toString();
		String Attendu ="Sènôu Djédjébi - Année de Naissance : 1995 - Nationalité : béninois - Identifiant : djedjsen95";
		assertEquals("Iidentité attendu attendu", Attendu, Observé);
	}
	
	
	/**
	 * Test UserId - Création d'un ID.
	 */
	
	@Test
	
	public void testcreateUserId() {
				
		Personne [] listePersonne = new Personne [3];
		
		listePersonne[0] = new Personne("Sènôu", "Djédjébi", "Béninois",1995);
		//listePersonne[1] = new Personne("Oscar", "Sanchez", "Portuguais", 1989);
		//listePersonne[2] = new Personne("Cônrad", "Van Kerk", "Hollandais", 1994);
			
		String s = listePersonne[0].userID();
		String Attendu = "djedjsen95";
		assertEquals("UserId attendu", Attendu, s);
	}
	
	
	
	/**
	 * Test UserId - Les Majuscules sont tranformées en minuscules.
	 */
	@Test
	
	public void testUserIdMajuscules() {
				
		Personne [] listePersonne = new Personne [3];
		
		listePersonne[0] = new Personne("SENOU", "DJEDJEBI", "Béninois", 1995);
		//listePersonne[1] = new Personne("Oscar", "Sanchez", "Portuguais", 1989);
		//listePersonne[2] = new Personne("Cônrad", "Van Kerk", "Hollandais", 1994);
		
		String s = listePersonne[0].userID();
		String Attendu = "djedjsen95";
		assertEquals("UserId attendu", Attendu, s);
	}
	
	
	/**
	 * Test UserId - Les espaces et les virgules/point-virgules sont supprimés.
	 */
	@Test
	
	public void testUserIdEspaces() {
				
		Personne [] listePersonne = new Personne [3];
		
		listePersonne[0] = new Personne("S E N O U", "D J E D J E B I", "Béninois", 1995);
		//listePersonne[1] = new Personne("Oscar", "Sanchez", "Portuguais", 1989);
		//listePersonne[2] = new Personne("Cônrad", "Van Kerk", "Hollandais", 1994);
		
		String s = listePersonne[0].userID();
		String Attendu = "djedjsen95";
		assertEquals("UserId attendu", Attendu, s);
	}
	
	
	/**
	 * Test UserId - Les caractères spéciaux sont supprimés.
	 */
	@Test
	
	public void testUserIdCaracteresSpeciaux() {
				
		Personne [] listePersonne = new Personne [3];
		
		listePersonne[0] = new Personne("S Ë N Ö Ü", "D J Ë D J È B Ï", "Béninois", 1995);
		//listePersonne[1] = new Personne("Oscar", "Sanchez", "Portuguais", 1989);
		//listePersonne[2] = new Personne("Cônrad", "Van Kerk", "Hollandais", 1994);
		
		String s = listePersonne[0].userID();
		String Attendu = "djedjsen95";
		assertEquals("UserId attendu", Attendu, s);
	}
	

}
