package personne;
import java.text.Normalizer;
import java.lang.Integer;
import java.text.Normalizer.Form;
public class Personne
{
		//Constantes de classe
		static final String NATIONALITE_PAR_DEFAUT="français";
		static final int ANNEE_NAISSANCE_PAR_DEFAUT=0;
		//Variables de classes
		static int nbPersonnes;

		
		//Variables d'instance
		String nom;
		String prenom;
		int anneeDeNaissance = ANNEE_NAISSANCE_PAR_DEFAUT;
		String nationalite = NATIONALITE_PAR_DEFAUT;
		
		//Constructeurs
		
		public Personne(String prenom, String nom, String nationalite, int anneeDeNaissance)
		{
			this.prenom = prenom;
			this.nom = nom;
			this.nationalite = nationalite;
			this.anneeDeNaissance = anneeDeNaissance;
			Personne.incrementeNbPersonnes();
		}
		
		public Personne(String prenom, String nom)
		{
			this(prenom, nom, NATIONALITE_PAR_DEFAUT, ANNEE_NAISSANCE_PAR_DEFAUT);
			Personne.incrementeNbPersonnes();
		}
		
		public Personne(String prenom, String nom, String nationalite)
		{
			this(prenom, nom, nationalite, ANNEE_NAISSANCE_PAR_DEFAUT);
			Personne.incrementeNbPersonnes();
		}
		
		
		public Personne(String prenom, String nom, int anneeDeNaissance)
		{
			this(prenom, nom, NATIONALITE_PAR_DEFAUT, anneeDeNaissance);
			Personne.incrementeNbPersonnes();
		}
			
			
	
		//Methodes
		
			//Accesseurs et Mutateurs
			String getNom()
			{
				return this.nom;
			}
	
			void setNom(String nom)
			{
				this.nom = nom;
			}
	
			String getPrenom()
			{
				return this.prenom;
			}

			void setPrenom(String prenom)
			{
				this.prenom = prenom;
			}
	
			int getAnneeDeNaissance()
			{
				return this.anneeDeNaissance;
			}
	
			void setAnneeDeNaissance(int anneeDeNaissance)
			{
				this.anneeDeNaissance = anneeDeNaissance;
			}
	
	
			String getNationalite()
			{
				return this.nationalite;
			}
	
	
			void setNationalite(String nationalite)
			{
				this.nationalite = nationalite;
			}
	
	

	
			//Methodes d'instance
			int age(int annee)
			{
				return annee - getAnneeDeNaissance();
			}
	
			public String Identite(){
				String s;
				s = this.prenom+" "+ this.nom + " - Année de Naissance : " + this.anneeDeNaissance
				+ " - Nationalité : " + this.nationalite + " - Identifiant : "+this.userID();
				return s;
			}
			
			
			/*public String userId()
			{
				String naissance;
				if(this.anneeDeNaissance != ANNEE_NAISSANCE_PAR_DEFAUT){
					naissance = Integer.toString(this.anneeDeNaissance%100);
				}
				else{
					naissance = "xx";
				}
				
				String userId;
				userId = this.nom.substring(0,5) + this.prenom.substring(0,3) + naissance;
				userId = Normalizer.normalize(userId, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
				return userId;
			}*/
			
			
			
			/**  
			 *  supprimer   les   espaces et les apostrophes
			 *  supprimer   les   caractères   accentués) 
			 *  former en   lettres   minuscules,
			 */
			
			
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
				return this.Identite();
			}

			//Methodes de classe
	
			public static int getNbPersonnes()
			{
				return nbPersonnes;
			}
	
			public static void incrementeNbPersonnes()
			{
				nbPersonnes++;
			}
	
			
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	




