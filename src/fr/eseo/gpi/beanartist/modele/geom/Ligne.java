package fr.eseo.gpi.beanartist.modele.geom;

/**
 * @author Elphege
 * @date 12/04/2016
 * @project gpi_binome
 */
//import java.lang.Math;

public class Ligne extends Forme {

    public final static double EPSILON = 1;

    //------------- CONSTRUCTEUR -------------//

    public Ligne() {
        this(new Point());
    }

    public Ligne(Point position) {
        this(position, Forme.LARGEUR_PAR_DÉFAUT, Forme.HAUTEUR_PAR_DÉFAUT);
    }

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
                this.getX()+largeur,
                this.getY()+hauteur
        );
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    //------------- SETTERS -------------//

    public void setPosition(Point newPosition) {
        int deltaX = newPosition.getX() - getX();
        int deltaY = newPosition.getY() - getY();
        super.setPosition(newPosition);
        déplacerDe(deltaX, deltaY);
    }

    public void setP1(Point p1) {
        super.setLargeur(getP2().getX() - p1.getX());
        super.setHauteur(getP2().getY() - p1.getY());
        super.setPosition(p1);
    }

    public void setP2(Point p2) {
        super.setLargeur(p2.getX()-this.getX());
        super.setHauteur(p2.getY()-this.getY());
    }


    public void setHauteur(int newHauteur){
        setP2(new Point(getP1().getX() + getLargeur(), getP1().getY() + newHauteur));
        super.setHauteur(newHauteur);
    }

    public void setLargeur(int newLargeur){
        setP2(new Point(getP1().getX() + newLargeur, getP1().getY() + getHauteur()));
        super.setLargeur(newLargeur);
    }

    // =========    GEOM        ===============

    public int getMinX() {
        return Math.min(getP1().getX(), getP2().getX());
    }

    public int getMaxX() {
        return Math.max(getP1().getX(), getP2().getX());
    }

    public int getMinY() {
        return Math.min(getP1().getY(), getP2().getY());
    }

    public int getMaxY() {
        return Math.max(getP1().getY(), getP2().getY());
    }


    //------------- OTHERS -------------//

    public void déplacerDe(int deltaX, int deltaY) {
        this.getP1().déplacerDe(deltaX, deltaY);
        this.getP2().déplacerDe(deltaX, deltaY);
    }

	public double périmètre() {return Math.sqrt(Math.pow(getLargeur(),2) + Math.pow(getHauteur(),2));}

    public double aire() {return 0;}


    public boolean contient(int x, int y){
        return Math.sqrt(Math.pow(x - this.getP1().getX(), 2) + Math.pow(y - this.getP1().getY(), 2))
                + Math.sqrt(Math.pow(x - this.getP2().getX(), 2) + Math.pow(y - this.getP2().getY(), 2))
                - Math.sqrt(Math.pow(this.getP1().getX() - this.getP2().getX(), 2) + Math.pow(this.getP1().getY()- this.getP2().getY(), 2))
                < EPSILON;
    }


    public boolean contient(Point testPosition){
        return this.contient(testPosition.getX(), testPosition.getY());
    }

//    public Ligne clone () {
//        Ligne clone = (Ligne) super.clone();
//
//        clone.setP1(this.getP1().clone());
//        clone.setP2(this.getP2().clone());
//
//        return clone;
//    }
    public Ligne clone () {
        return (Ligne) super.clone();
    }



    //------------- AFFICHAGE -------------//

    public String toString() {
        return "[Ligne] p1 : "+getP1().toString()+", p2 : "+getP2().toString()+", longueur : "+périmètre();
    }
}
