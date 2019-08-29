/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;
import java.lang.Math;

public class Cercle extends Ellipse {

	//------------- CONSTRUCTEUR -------------//
	
	public Cercle() {
		this(new Point());
	}

	public Cercle(Point centre, int rayon) {
		super(centre, rayon, rayon);
	}

	public Cercle(int x, int y, int rayon) {
		this(new Point(x,y), rayon);
	}

	public Cercle(Point centre) {
		this(centre, Forme.LARGEUR_PAR_DEFAUT);
	}

	public Cercle(int rayon) {
		super(rayon*2, rayon*2);
	}

	//----------- REDIFINITION DES METHODES -----------//
	
	public void setLargeur(int newLength) {
		this.setLargeur(newLength);
		this.setHauteur(newLength);
	}
	
	public void setHauteur(int newLength) {
		this.setLargeur(newLength);
	}

	//------------- AFFICHAGE -------------//

	/**
	* Renvoie en String les informations sur le cercle
	* @return message (String)
	**/
	public String toString(){
		return this.toString("Cercle");
	}


	//----------- AUTRES METHODES -----------//

	/**
	* Renvoie le périmètre
	* @return perimeter
	**/
	public double perimetre(){
		return Math.PI*this.getHauteur();
	}

}