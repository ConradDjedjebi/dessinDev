/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;
import fr.eseo.gpi.projet.geom.Point;


abstract class Forme {
	static protected int LARGEUR_PAR_DEFAUT = 1;
	static protected int HAUTEUR_PAR_DEFAUT = 1;
	protected int largeur, hauteur;

	protected Point position;


	// =========	CONTRUCTORS ===============


	public Forme() {
		this(new Point());
	}

	public Forme(Point position, int largeur, int hauteur) {
		this.setPosition(position);
		this.setLargeur(largeur);
		this.setHauteur(hauteur);

	}

	public Forme(int x, int y, int largeur, int hauteur) {
		this(new Point(x,y), largeur, hauteur);
	}

	public Forme(int largeur, int hauteur) {
		this(new Point(), largeur, hauteur);
	}

	public Forme(Point position) {
		this(position, LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT);
	}


	// ========================================
	// =========	GETERS		===============

	public Point getPosition() {
		return position;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	
	// =========	GEOM		===============

	public int getMinX() {
		return Math.min(position.getX()+this.getLargeur(), position.getX());
	}

	public int getMaxX() {
		return Math.max(position.getX()+this.getLargeur(), position.getX());
	}

	public int getMinY() {
		return Math.min(position.getY()+this.getLargeur(), position.getY());
	}

	public int getMaxY() {
		return Math.max(position.getY()+this.getLargeur(), position.getY());
	}




	// ========================================
	// =========	SETERS		===============

	public void setPosition(Point newPos) {
		position = newPos;
	}

	public void setX(int newPos) {
		position.setX(newPos);
	}

	public void setY(int newPos) {
		position.setY(newPos);
	}

	public void setLargeur(int newLength) {
		largeur = newLength;
	}

	public void setHauteur(int newLength) {
		hauteur = newLength;
	}

	
	// ========================================
	// =========	OTHERS		===============

	public void deplacerVers(int x, int y) {
		position.moveTo(x,y);
	}

	public void deplacerDe(int deltaX, int deltaY) {
		this.position.moveVect(deltaX, deltaY);
	}

	
	// ========================================

	abstract public String toString();

	protected final String toString(String nom) {
		return "Forme > ["+nom+']'+
				"\n\t Position : "+position.toString()+
				"\n\t Largeur:"+getLargeur()+
				"\n\t Hauteur:"+getHauteur()+
				"\n\t Aire:"+this.aire()+
				"\n\t Périmètre:"+this.perimetre()+
				"\n===END OF DESCRIPTION===\n";
	}


	/*protected final String toString(String nom) {
		return  "Forme > " + this.getClass().getName()+" "+
				"\n\t Position : "+position.toString()+
				"\n\t Largeur:"+getLargeur()+
				"\n\t Hauteur:"+getHauteur()+
				"\n\t Aire:"+this.aire()+
				"\n\t Périmètre:"+this.perimetre()+
				"\n===END OF DESCRIPTION===\n";
	}*/

	// ========================================
	
	abstract double aire();
	abstract double perimetre();

}