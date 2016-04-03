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
		this(new Ligne(p1, p2));
	}

	public Trace () {
		this(new Ligne());
	}

	public Trace (Ligne ligne) {
		this.lignes = new ArrayList<Ligne>();
		this.lignes.add(ligne);
		this.setPosition(new Point(ligne.getMinX(), ligne.getMaxY()));
		this.setLargeur(findLargeur(lignes));
		this.setHauteur(findHauteur(lignes));
	}

	//------------ SETTERS -------------//


	public ArrayList<Ligne> getLignes() {
		return this.lignes;
	}

	//The method addLineTo may not work if the list is empty

	public void addLineTo(Point p) {
		this.lignes.add(new Ligne(this.lignes.get(lignes.size()).getP2(), p));
		this.setPosition(findPosition(lignes));
	}


	public void setX(int newX) {
		this.deplacerVers(newX, this.getPosition().getY());
	}
	public void setY(int newY) {
		this.deplacerVers(this.getPosition().getX(), newY);
	}


	//The method findLargeur calculates the width of the outline   #Elphege
	//If the outline's width have not been modified, we just can call "getLargeur"    #Elphege

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
		for(int i=1; i<lignes.size(); i++){
			if(xMin>lignes.get(i).getMinX()){
				xMin = lignes.get(i).getMinX();
			}
		}
		for(int i=1; i<lignes.size(); i++){
			if(yMax<lignes.get(i).getMaxY()){
				yMax = lignes.get(i).getMaxY();
			}
		}
		return new Point(xMin, yMax);
	}


	public void setPosition(Point p) {
		Point p1 = this.getPosition();
		int deltaX = p.getX()-p1.getX(),
				deltaY = p.getY()-p1.getY();

		this.deplacerDe(deltaX, deltaY);
		this.position = p;
	}

	public void setDimensions(int largeur, int hauteur) {
		setLargeur(largeur);
		setHauteur(hauteur);
	}

	//A chaque instant, il est nécessaire de connaitre le x min et y max de l'ensemble des points du tracés
	//Lla position du trace correspont au point d'abscisse (xMin, yMax)
	//Les méthodes à construire se serviront des méthodes précédentes :
	// Il faut le yMax des yMax Math.max(yMax1, yMax2..), xMin des xMin Math.min(xMin1, xMin2..)





	//----------- METHODS -----------//


	public void deplacerDe(int deltaX, int deltaY) {

		for (Ligne ligne : this.lignes) ligne.deplacerDe(deltaX, deltaY);
	}

	public void deplacerVers(int newX, int newY) {
		this.setPosition(new Point(newX, newY));
	}



	//------------- PRINTS -------------//

	public String toString() {
		return this.toString("Tracé");
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