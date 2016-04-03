/**
 * @author Antoine du HAMEL & Djedjebi Elphege
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;
import java.lang.Math;

public class Ligne extends Forme {

	//------------- CONSTRUCTEUR -------------//
	
	public Ligne() {
		this(new Point());
	}

	public Ligne(Point position) {
		this(position, Forme.LARGEUR_PAR_DEFAUT, Forme.HAUTEUR_PAR_DEFAUT);
	}


	//Le constructeur suivant risque de ne pas marcher. setP2 définit des valeurs pour "largeur" et "hauteur".
	//Cependant, this(position) remplacera par la suite "largeur" et "heuteur" par des valeurs par défaut.
	public Ligne(Point position, Point p2) {
		this(position);
		this.setP2(p2);
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
		this.setLargeur(p2.getX()-this.getX());
		this.setHauteur(p2.getY()-this.getY());
	}

	//------------- OTHERS -------------//

	/*public double perimetre() {return getLargeur();}*/

	public double perimetre() {
		return Math.sqrt(this.getLargeur()*this.getLargeur() + this.getHauteur()*this.getHauteur());
	}

	public double aire() {return 0;}

	//------------- AFFICHAGE -------------//
	
	public String toString() {
		return "[Ligne] p1 : "+getP1().toString()+", p2 : "+getP2().toString()+", longueur : "+perimetre();
	}
}