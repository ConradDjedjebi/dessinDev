public class Trace extends Forme {

	ArrayList<Ligne> lignes;


	public Trace (Point p1, Point p2) {
		this.lignes = new ArrayList<Ligne>();
		this.lignes.add(new Ligne(p1, p2));
	}

	public List<Ligne> getLignes() {
		return this.lignes;
	}

	public void addLineTo(Point p) {
		this.lignes.add(new Ligne(this.lignes.get(lignes.size()).getP2(), p));
	}

	public void setPosition(Point p) {
		Point p1 = this.getPosition();
		int deltaX = p.getX()-p1.getX(),
			deltaY = p.getY()-p1.getY();

		this.deplacerDe(deltaX, deltaY);
	}

	public void deplacerDe(deltaX, deltaY) {
		ListIterator<Ligne> listI = this.lignes.listIterator();

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



	public void setLargeur(int largeur) {
		//
	}

	public void setHauteur(int hauteur) {
		//
	}

	public void setDimensions(int largeur, int hauteur) {
		setLargeur(largeur);
		setHauteur(hauteur);
	}


	public String toString() {
		return this.toString("Tracé");
	}

	public double aire() {return 0;}

	public double perimetre() {
		ListIterator<Ligne> listI = this.lignes.listIterator();
		double returnedPerimetre = 0;

		while (listI.hasNext())
			returnedPerimetre+=listI.next().perimetre();

		return returnedPerimetre;
	}
}