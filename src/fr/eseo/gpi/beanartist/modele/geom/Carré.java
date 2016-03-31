/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.beanartist.modele.geom;

public class Carré extends Rectangle {

	//------------- CONSTRUCTEUR -------------//

	public Carré() {
		this(new Point());
	}

	public Carré(Point position, int cote) {
		super(position, cote, cote);
	}

	public Carré(int x, int y, int cote) {
		this(new Point(x,y), cote);
	}

	public Carré(Point position) {
		this(position, Forme.LARGEUR_PAR_DÉFAUT);
	}

	public Carré(int cote) {
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
	public double périmètre(){
		return 4*this.getHauteur();
	}
}