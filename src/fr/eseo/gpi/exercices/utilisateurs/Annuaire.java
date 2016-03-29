/**
 * @author Antoine du Hamel
 * @section 1.7
 * @exercice 3
 */
package fr.eseo.gpi.exercices.utilisateurs;

public class Annuaire {
	private java.util.ArrayList<Personne> list;

	public Annuaire(){
		this.list = new java.util.ArrayList<Personne>();
	}

	/**
	 * @param Personne pers Ajoute une personne à l'annuaire
	 */
	public void ajouter(Personne pers) {
		this.list.add(pers);
	}

	/**
	 * @param String nom Le nom de la personne recherchée
	 * @return Personne | null 
	 */
	public Personne chercher(String nom) {
		// System.out.println(this.list.length());
		java.util.Iterator<Personne> iterator = this.list.iterator();
		while(iterator.hasNext()) {
			Personne pers = iterator.next();
			if (pers.getNom().equals(nom))
				return pers;
		}
		return null;
	}

	/**
	 * @param String nom Le nom de la personne à supprimer
	 * @return Personne La personne supprimée
	 * @throws PointerNullException
	 */
	public Personne supprimer(String nom) {
		Personne pers = this.chercher(nom);
		if(pers!=null)
			this.list.remove(pers);
		return pers;
	}

	public Personne[] versTableau() {
		return this.list.toArray(new Personne[this.list.size()]);
	}
}