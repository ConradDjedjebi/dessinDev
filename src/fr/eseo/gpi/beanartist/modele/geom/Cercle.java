/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.beanartist.modele.geom;

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
		this(centre, Forme.LARGEUR_PAR_DÉFAUT);
	}

	public Cercle(int diameter) {
		super(diameter, diameter);
	}

	//----------- REDIFINITION DES METHODES -----------//

	public void setLargeur(int newLength) {
		this.setLargeur(newLength, false);
	}
	public void setLargeur(int newLength, boolean otherSide) {
		super.setLargeur(otherSide ? -newLength : newLength);
		super.setHauteur(newLength);
	}

	public void setHauteur(int newLength) {
		this.setLargeur(newLength);
	}


	//----------- AUTRES METHODES -----------//

	/**
	* Renvoie le périmètre
	* @return perimeter
	**/
	public double périmètre(){
		return Math.PI*this.getHauteur();
	}

    public boolean contient(int x, int y) {
		x -= this.getX();
		y -= this.getY();
    	return Math.sqrt(x*x+y*y) <= this.getLargeur();
	}

}