package fr.eseo.gpi.beanartist.modele.geom;

/**
 * @author Elphege
 * @date 12/04/2016
 * @project gpi_binome
 */
import java.lang.Math;

public class Ligne extends Forme {

    public final static double EPSILON = 0.00001;

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
                this.getX()+this.getLargeur(),
                this.getY()+this.getHauteur()
        );
    }

    //------------- SETTERS -------------//

    public void setP1(Point p1) {
        int deltaX = getX() - p1.getX();
        int deltaY = getY() - p1.getY();
        this.setPosition(p1);
        this.setLargeur(getLargeur() + deltaX);
        this.setHauteur(getHauteur() + deltaY);
    }

    public void setP2(Point p2) {
        this.setLargeur(p2.getX()-this.getX());
        this.setHauteur(p2.getY()-this.getY());
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


    //------------- AFFICHAGE -------------//

    public String toString() {
        return "[Ligne] p1 : "+getP1().toString()+", p2 : "+getP2().toString()+", longueur : "+périmètre();
    }
}
