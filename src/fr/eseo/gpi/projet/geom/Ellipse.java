/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;
import java.lang.Math;

public class Ellipse extends Forme{

	//------------- CONSTRUCTEUR -------------//
	
	public Ellipse() {
		this(new Point());
	}

	public Ellipse(Point center, int largeur, int hauteur) {
		super(center, largeur, hauteur);
	}

	public Ellipse(int x, int y, int largeur, int hauteur) {
		this(new Point(x,y), largeur, hauteur);
	}

	public Ellipse(int largeur, int hauteur) {
		this(new Point(), largeur, hauteur);
	}

	public Ellipse(Point center) {
		this(center, Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}

	//------------- AFFICHAGE -------------//

	/**
	* Renvoie en String les informations sur Ellipse
	* @return message (String)
	**/


	public String toString(){
		return this.toString("Ellipse");
	}


	//----------- AUTRES METHODES -----------//


	/**
	* Renvoie l'air
	* @return air (int)
	**/
	public double aire(){
		return Math.PI*this.largeur*this.hauteur/4;
	}

	/**
	* Renvoie le périmètre de l'ellipse (approximation de Ramanujan)
	* @return perimeter
	**/
	public double perimetre(){
		return 
			Math.PI/2*
			(
				3*(this.largeur+this.hauteur)
				-
				Math.sqrt(
					(3*this.largeur+this.hauteur)*
					(3*this.hauteur+this.largeur)
				)
			);
	}

}