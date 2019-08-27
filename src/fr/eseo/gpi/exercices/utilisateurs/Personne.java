/**
 * @author Antoine du HAMEL
 * @section 1.4
 */
package fr.eseo.gpi.exercices.utilisateurs;

import java.text.Normalizer;
import java.text.Normalizer.Form;


public class Personne {

	static final String NATIONALITE_PAR_DEFAUT="française";
	static int nbPersonnes;

	String nom;
	String prenom;
	int anneeDeNaissance;
	String nationalite;


	//------- CONSTRUCTEURS----------//

	/**
	* Constructeur à 4 paramètres
	**/ 

	public Personne(String prenom,String nom, int anneeDeNaissance, String nationalite){
		this.nom=nom;
		this.prenom=prenom;
		this.anneeDeNaissance=anneeDeNaissance;
		this.nationalite=nationalite;
		Personne.incrementNbPersonnes();
	}

	/**
	* Constructeur à 2 paramètres
	**/
	public Personne(String prenom,String nom){
		this(prenom, nom, 0, NATIONALITE_PAR_DEFAUT);
	}

	//------------------------------//



	public static void incrementNbPersonnes(){
		nbPersonnes++;
	}

	// *---Gets---* //

	public static int getNbPersonnes(){
		return nbPersonnes;
	}

	public String getNom(){
		return this.nom;
	}

	public String getPrenom(){
		return this.prenom;
	}

	public int getAnneeDeNaissance(){
		return this.anneeDeNaissance;
	}

	public String getNationalite(){
		return this.nationalite;
	}

	// *---Sets---* // 

	public void setNom(String nom){
		this.nom=nom;
	}

	public void setPrenom(String prenom){
		this.prenom=prenom;
	}

	public void setAnneeDeNaissance(int anneeDeNaissance){
		this.anneeDeNaissance=anneeDeNaissance;
	}

	public void setNationalite(String nationalite){
		this.nationalite=nationalite;
	}


	// *---Autres méthodes---* // 

	public int age(int annee){
		return annee - getAnneeDeNaissance();
	}

	public String identite(){
		String ans = prenom+' '+nom+" - Année de naissance : "+anneeDeNaissance+" - Nationalité : "+nationalite;
		return ans;
	}

	//** Création du username **//
	public String userID(){

		// Partie 1 : 5 premières lettres du nom
		String name_clear=Normalizer.normalize(this.nom, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); 
		name_clear=name_clear.replaceAll("[^a-zA-Z]+","").toLowerCase();
		String id_part1=name_clear;
		if(name_clear.length()>5){
			id_part1=name_clear.substring(0,5);
		}

		// Partie 2 : 3 premières lettres du prénom
		String first_name_clear=Normalizer.normalize(this.prenom, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); 
		first_name_clear=first_name_clear.replaceAll("[^a-zA-Z]+","").toLowerCase();
		String id_part2=first_name_clear;
		if(first_name_clear.length()>3){
			id_part2=first_name_clear.substring(0,3);
		}

		//Partie 3 : 2 derniers chiffres birthday
		String id_part3;
		if(this.anneeDeNaissance==0){
			id_part3="XX";
		} else {
			id_part3=""+this.anneeDeNaissance;
			id_part3=id_part3.substring(2,4);
		}


		String id = id_part1+id_part2+id_part3;

		return id;


	}


	public String toString()
	{
		return "Personne";
	}

}