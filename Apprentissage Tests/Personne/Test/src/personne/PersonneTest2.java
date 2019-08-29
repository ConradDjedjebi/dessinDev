/**
 * 
 */
package personne;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author duhamean
 *
 */
public class PersonneTest2 {
	
	private static final String nomTest = "Chariot", prenomTest = "Jacky", natTest = "Française";
	private static final int anneeNaissance = 1936;

	/**
	 * Test method for {@link personne.Personne#age(int)}.
	 */
	@Test
	public void testAge() {
		Personne p = new Personne(prenomTest, nomTest, anneeNaissance);
		
		assertEquals("Test année 0", p.age(anneeNaissance), 0);
		assertEquals("Test année 10", p.age(anneeNaissance+10), 10);
		assertEquals("Test en négatif", p.age(anneeNaissance-10), -10);
	}

	/**
	 * Test method for {@link personne.Personne#Identite()}.
	 */
	@Test
	public void testIdentite() {
		Personne p = new Personne(prenomTest, nomTest, natTest, anneeNaissance);
		assertEquals(
				"Test de l'identité",
				p.Identite(),
				prenomTest+" "+ nomTest + " - Année de Naissance : " + anneeNaissance
				+ " - Nationalité : " + natTest + " - Identifiant : "+p.userID());
	}

}
