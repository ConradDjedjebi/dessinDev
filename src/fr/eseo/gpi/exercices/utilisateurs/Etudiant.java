/**
 * @author Antoine du HAMEL
 * @section 1.8
 * @exercice 1
 */
package fr.eseo.gpi.exercices.utilisateurs;

public class Etudiant extends Personne {
	protected int numEtudiant;

	public Etudiant (String prenom,String nom, String nationalite, int anneeDeNaissance, int numEtudiant) {
		super(prenom,nom, anneeDeNaissance, nationalite);
		this.setNumEtudiant(numEtudiant);
	}
	public Etudiant (String prenom,String nom, int anneeDeNaissance, int numEtudiant) {
		super(prenom,nom);
		super.setAnneeDeNaissance(anneeDeNaissance);
		this.setNumEtudiant(numEtudiant);
	}

	public int getNumEtudiant()
	{
		return this.numEtudiant;
	}

	public void setNumEtudiant(int num) {
		this.numEtudiant=num;
	}

	public String toString()
	{
		return "Etudiant";
	}
}