/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class FormeComposee extends Forme {

	ArrayList<Forme> formes;

	public FormeComposee(Forme[] lesFormes) {
		this.formes = new ArrayList<Forme>();
		for (Forme forme : this.formes)
			this.ajouterForme(forme);
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
		ListIterator<Forme> listI = this.formes.listIterator();

		while (listI.hasNext())
			listI.next().deplacerDe(deltaX, deltaY);
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

	public String toString() {
		// TODO Auto-generated method stub
		return this.toString("Forme composée");
	}

	double aire() {
		ListIterator<Forme> listI = this.formes.listIterator();
		double returnedPerimetre = 0;

		while (listI.hasNext())
			returnedPerimetre+=listI.next().aire();

		return returnedPerimetre;
	}

	double perimetre() {
		ListIterator<Forme> listI = this.formes.listIterator();
		double returnedPerimetre = 0;

		while (listI.hasNext())
			returnedPerimetre+=listI.next().perimetre();

		return returnedPerimetre;
	}

}
