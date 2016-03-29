/**
 * @author Antoine du HAMEL
 * @section 1.4
 */
package fr.eseo.gpi.exercices.utilisateurs;

class UserID{
	public static void main (String[]args){
		Personne personne = new Personne(args[0], args[1]);
//		personne.setNom(args[0]);
//		personne.setPrenom(args[1]);
		personne.setNationalite(args[2]);

		if(args.length>3){
			personne.setAnneeDeNaissance(Integer.parseInt(args[3]));
		} else {
			personne.setAnneeDeNaissance(0);
		}
		System.out.println(personne.identite()+" => "+personne.userID());

	}
}