/**
 * @author Antoine du HAMEL
 * @section 1.8
 * @exercice 1
 */
package fr.eseo.gpi.exercices.utilisateurs;

public class Professeur extends Personne {
	private java.util.ArrayList<String> cours = new java.util.ArrayList<String>();

	public Professeur (String prenom,String nom, String nationalite, int anneeDeNaissance) {
		super(prenom,nom, anneeDeNaissance, nationalite);
	}
	public Professeur (String prenom,String nom, int anneeDeNaissance) {
		super(prenom,nom);
		super.setAnneeDeNaissance(anneeDeNaissance);
	}
	public Professeur (String prenom,String nom) {
		this(prenom,nom, 0);
	}

	public void ajouterCours(String cours)
	{
		this.cours.add(cours);
	}

	public java.util.ArrayList<String> getCours() {
		return this.cours;
	}

	public String toString()
	{
		return "Professeur";
	}
}