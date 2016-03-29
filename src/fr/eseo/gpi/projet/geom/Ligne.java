/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;

public class Ligne extends Forme {

	//------------- CONSTRUCTEUR -------------//
	
	public Ligne() {
		this(new Point());
	}

	public Ligne(Point position) {
		this(position, Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}

	public Ligne(int x, int y, int largeur, int hauteur) {
		this(new Point(x,y), largeur, hauteur);
	}

	public Ligne(int largeur, int hauteur) {
		this(new Point(), largeur, hauteur);
	}

	public Ligne(Point position, int largeur, int hauteur) {
		super(position, largeur, hauteur);
	}

	//------------- GETTERS -------------//

	public Point getP1() {
		return this.getPosition();
	}

	public Point getP2() {
		return new Point(
				this.getX()+this.getLargeur(),
				this.getY()+this.getHauteur()
			);
	}
	
	//------------- SETTERS -------------//
	
	public void setP1(Point p1) {
		this.setPosition(p1);
	}

	public void setP2(Point p2) {
		this.setLargeur(this.getX()+p2.getX());
		this.setHauteur(this.getY()+p2.getY());
	}

	//------------- OTHERS -------------//

	public double perimetre() {return getLargeur();}
	public double aire() {return 0;}

	//------------- AFFICHAGE -------------//
	
	public String toString() {
		return "[Ligne] p1 : "+getP1().toString()+", p2 : "+getP2().toString()+", longueur : "+getLargeur();
	}
}