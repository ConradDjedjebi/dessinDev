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
		super();
		this.formes = new ArrayList<>();
		for (Forme forme : this.formes)
			this.ajouterForme(forme);

        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        for(Forme forme : formes) {
            xMin = Math.min(forme.getMinX(), xMin);
            xMax = Math.max(forme.getMaxX(), xMax);
            yMin = Math.min(forme.getMinY(), yMin);
            yMax = Math.max(forme.getMaxY(), yMax);
        }
		this.largeur = xMax-xMin;
		this.hauteur = yMax-yMin;
		this.position = new Point(xMin, yMax);
	}

	// ------------- SETTER & GETTERS ------------- //

	public List<Forme> getFormes() {
		return this.formes;
	}

	public void ajouterForme(Forme forme) {
		this.formes.add(forme);
	}

	public void setPosition(Point p) {
		Point p1 = this.getPosition();
        try {
            int deltaX = p.getX() - p1.getX(),
                    deltaY = p.getY() - p1.getY();

            this.deplacerDe(deltaX, deltaY);
        }
        catch (NullPointerException e) {
            this.position = p;
        }
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
