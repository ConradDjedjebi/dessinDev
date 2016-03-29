/**
 * @author Antoine du HAMEL; Elphège DJEDJEBI
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;

public class Rectangle extends Forme{

	//------------- CONSTRUCTEUR -------------//

	public Rectangle() {
		this(new Point());
	}

	public Rectangle(Point position, int largeur, int hauteur) {
		super(position, largeur, hauteur);
	}

	public Rectangle(int x, int y, int largeur, int hauteur) {
		this(new Point(x,y), largeur, hauteur);
	}

	public Rectangle(int largeur, int hauteur) {
		this(new Point(), largeur, hauteur);
	}

	public Rectangle(Point position) {
		this(position, Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}

	//------------- AFFICHAGE -------------//

	/**
	* Renvoie en String les informations du rectangle
	* @return message (String)
	**/
	public String toString(){
		return this.toString("Rectangle");
	}


	//----------- AUTRES METHODES -----------//


	/**
	* Renvoie l'air du rectangle
	* @return air (int)
	**/
	public double aire(){
		return this.getLargeur()*this.getHauteur();
	}

	/**
	* Renvoie le périmètre du rectangle
	* @return perimeter (int)
	**/
	public double perimetre(){
		return 2*this.getLargeur()+2*this.getHauteur();
	}

}