/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class FormeComposee extends Forme {

	private ArrayList<Forme> formes;


	// ------------- CONSTRUCTORS -------------- //

	public FormeComposee(Forme[] lesFormes) {
		this.formes = new ArrayList<>();
		for (Forme forme : this.formes)
			this.ajouterForme(forme);
		this.setPosition(this.findPosition(formes));
		this.setLargeur(this.findLargeur(formes));
		this.setHauteur(this.findHauteur(formes));
	}

	// ------------- SETTER & GETTERS ------------- //


	public int findLargeur(ArrayList<Forme> formes) {
		int xMin = formes.get(0).getPosition().getX();
		int xMax = formes.get(0).getPosition().getX();
		for(int i=1; i<formes.size(); i++){
			if(xMin>formes.get(i).getPosition().getX()){
				xMin = formes.get(i).getPosition().getX();
			}
			if (xMax < formes.get(i).getPosition().getX()) {
				xMax = formes.get(i).getPosition().getX();
			}
		}
		return xMax-xMin;
	}


	public int findHauteur(ArrayList<Forme> formes) {
		int yMin = formes.get(0).getPosition().getY();
		int yMax = formes.get(0).getPosition().getY();
		for(int i=1; i<formes.size(); i++){
			if(yMin>formes.get(i).getPosition().getY()){
				yMin = formes.get(i).getPosition().getY();
			}
			if (yMax < formes.get(i).getPosition().getY()) {
				yMax = formes.get(i).getPosition().getY();
			}
		}
		return yMax-yMin;
	}


	public Point findPosition(ArrayList<Forme> formes){
		int xMin = formes.get(0).getPosition().getX();
		int yMax = formes.get(0).getPosition().getY();
		for(int i=1; i<formes.size(); i++){
			if(xMin>formes.get(i).getPosition().getX()){
				xMin = formes.get(i).getPosition().getX();
			}
			if (yMax < formes.get(i).getPosition().getY()) {
				yMax = formes.get(i).getPosition().getY();
			}
		}
		return new Point(xMin, yMax);
	}

	public List<Forme> getFormes() {
		return this.formes;
	}

	public void ajouterForme(Forme forme) {
		this.formes.add(forme);
	}

	public void setPosition(Point p) {
		Point p1 = this.getPosition();
		int deltaX = p.getX()-p1.getX(),
			deltaY = p.getY()-p1.getY();

		this.deplacerDe(deltaX, deltaY);
	}

	public void deplacerDe(int deltaX, int deltaY) {

		for (Forme forme : this.formes) forme.deplacerDe(deltaX, deltaY);
	}

	public void deplacerVers(int newX, int newY) {
		this.setPosition(new Point(newX, newY));
	}


	public void setX(int newX) {
		this.deplacerVers(newX, this.getPosition().getY());
	}
	public void setY(int newY) {
		this.deplacerVers(this.getPosition().getX(), newY);
	}


	// ------------- AFFICHAGE ------------- //

	public String toString() {
		// TODO Auto-generated method stub
		return this.toString("Forme composée")+
				"\n===END OF DESCRIPTION===\n";
	}

	/**
	 * Calcule l'aire
	 * @return la somme des aires de l'ensemble des figures qui composent la forme composée
     */
	double aire() {
		ListIterator<Forme> listI = this.formes.listIterator();
		double returnedPerimeter = 0;

		while (listI.hasNext())
			returnedPerimeter+=listI.next().aire();

		return returnedPerimeter;
	}

	double perimetre() {
		ListIterator<Forme> listI = this.formes.listIterator();
		double returnedArea = 0;

		while (listI.hasNext())
			returnedArea+=listI.next().perimetre();

		return returnedArea;
	}

}
