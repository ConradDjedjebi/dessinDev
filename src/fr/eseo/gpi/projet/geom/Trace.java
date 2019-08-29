/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import fr.eseo.gpi.projet.geom.Ligne;

/**
 * Définie la classe Tracé (qui devrait être un enfant de la classe FormeComposee btw)
 */
public class Trace extends Forme {

	ArrayList<Ligne> lignes;

	//---------- CONSTRUCTORS ----------//


	public Trace (Point p1, Point p2) {
		super(new Ligne(p1, p2).getMinX(), new Ligne(p1, p2).getMaxY());
		this.lignes = new ArrayList<Ligne>();
		int xMin = Math.min(p1.getX(), p2.getX());
		int xMax = Math.max(p1.getX(), p2.getX());
		int yMin = Math.min(p1.getY(), p2.getY());
		int yMax = Math.max(p1.getY(), p2.getY());
		setLargeur(xMax-xMin);
		setHauteur(yMax-yMin);
		//setPosition(new Point(xMin, yMax));
	}


	//------------ SETTERS -------------//


	public void addLineTo(Point p) {
		this.lignes.add(new Ligne(this.lignes.get(lignes.size() - 1).getP2(), p));
		this.setPosition(findPosition(lignes));
	}


	public void setX(int newX) {
		this.deplacerVers(newX, this.getPosition().getY());
	}
	public void setY(int newY) {
		this.deplacerVers(this.getPosition().getX(), newY);
	}

	public void setPosition(Point p) {
		Point p1 = this.getPosition();
		int deltaX = p.getX()-p1.getX(),
				deltaY = p.getY()-p1.getY();

		this.deplacerDe(deltaX, deltaY);
		super.setPosition(p);
	}

	public void setDimensions(int largeur, int hauteur) {
		setLargeur(largeur);
		setHauteur(hauteur);
	}


	// --------- GETTERS ----------- //

	public int findLargeur(ArrayList<Ligne> lignes) {
		int xMin = lignes.get(0).getMinX();
		int xMax = lignes.get(0).getMaxY();
		for(int i=1; i<lignes.size(); i++){
			if(xMin>lignes.get(i).getMinX()){
				xMin = lignes.get(i).getMinX();
			}
		}
		for(int i=1; i<lignes.size(); i++){
			if(xMax<lignes.get(i).getMaxX()){
				xMax = lignes.get(i).getMaxX();
			}
		}
		return xMax-xMin;
	}


	public int findHauteur(ArrayList<Ligne> lignes) {
		int yMin = lignes.get(0).getMinY();
		int yMax = lignes.get(0).getMaxY();
		for(int i=1; i<lignes.size(); i++){
			if(yMin>lignes.get(i).getMinY()){
				yMin = lignes.get(i).getMinY();
			}
		}
		for(int i=1; i<lignes.size(); i++){
			if(yMax<lignes.get(i).getMaxY()){
				yMax = lignes.get(i).getMaxY();
			}
		}
		return yMax-yMin;
	}


	public Point findPosition(ArrayList<Ligne> lignes){
		int xMin = lignes.get(0).getMinX();
		int yMax = lignes.get(0).getMaxY();
		for(int i=1; i<lignes.size(); i++) {
			if (xMin > lignes.get(i).getMinX()) {
				xMin = lignes.get(i).getMinX();
			}
			if (yMax < lignes.get(i).getMaxY()) {
				yMax = lignes.get(i).getMaxY();
			}
		}
		return new Point(xMin, yMax);
	}

	public ArrayList<Ligne> getLignes() {
		return lignes;
	}

	//----------- METHODS -----------//


	public void deplacerDe(int deltaX, int deltaY) {

		for (Ligne ligne : this.lignes) ligne.deplacerDe(deltaX, deltaY);
	}

	public void deplacerVers(int newX, int newY) {
		this.setPosition(new Point(newX, newY));
	}



	//------------- PRINTS -------------//

	public String toString() {
		return this.toString("Tracé")+
			"\n\t longueur : "+this.perimetre()+
			"\n\t nbLignes : "+this.lignes.size()+
			"\n===END OF DESCRIPTION===\n";
	}



	//------------- OTHERS -------------//

	public double aire() {return 0;}

	public double perimetre() {
		ListIterator<Ligne> listI = this.lignes.listIterator();
		double returnedPerimetre = 0;

		while (listI.hasNext())
			returnedPerimetre+=listI.next().perimetre();

		return returnedPerimetre;
	}
}