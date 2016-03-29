/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;

public class Carre extends Rectangle {

	//------------- CONSTRUCTEUR -------------//

	public Carre() {
		this(new Point());
	}

	public Carre(Point position, int cote) {
		super(position, cote, cote);
	}

	public Carre(int x, int y, int cote) {
		this(new Point(x,y), cote);
	}

	public Carre(Point position) {
		this(position, Forme.LARGEUR_PAR_DEFAUT);
	}

	public Carre(int cote) {
		this(new Point(), cote);
	}
	
	//------------- AFFICHAGE -------------//

	/**
	* Renvoie en String les informations du rectangle
	* @return message (String)
	**/
	public String toString(){
		return this.toString("Carré");
	}

	//----------- REDIFINITION DES METHODES -----------//
	
	public void setLargeur(int newLength) {
		super.setLargeur(newLength);
		super.setHauteur(newLength);
	}
	
	public void setHauteur(int newLength) {
		this.setLargeur(newLength);
	}

	//----------- AUTRES METHODES -----------//

	/**
	* Renvoie le périmètre du rectangle
	* @return perimeter (int)
	**/
	public double perimetre(){
		return 4*this.getHauteur();
	}

}