package fr.eseo.gpi.beanartist.modele.geom;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @author duhamean
 * @date 31/03/16
 * @project gpi_binome
 */
public abstract class Forme {
    public static final int LARGEUR_PAR_DÉFAUT = 1;
    public static final int HAUTEUR_PAR_DÉFAUT = 1;
    protected int largeur, hauteur;

    private Point position;


    // =========    CONTRUCTORS ===============


    public Forme() {
        this(new Point());
    }

    public Forme(Point position, int largeur, int hauteur) {
        this.position = position;
        this.largeur = largeur;
        this.hauteur = hauteur;

    }

    public Forme(int x, int y, int largeur, int hauteur) {
        this(new Point(x,y), largeur, hauteur);
    }

    public Forme(int largeur, int hauteur) {
        this(new Point(), largeur, hauteur);
    }

    public Forme(Point position) {
        this(position, LARGEUR_PAR_DÉFAUT, HAUTEUR_PAR_DÉFAUT);
    }


    // ========================================
    // =========    GETERS      ===============

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


    // =========    GEOM        ===============

    public int getMinX() {
        return Math.min(position.getX()+this.getLargeur(), position.getX());
    }

    public int getMaxX() {
        return Math.max(position.getX()+this.getLargeur(), position.getX());
    }

    public int getMinY() {
        return Math.min(position.getY()+this.getHauteur(), position.getY());
    }

    public int getMaxY() {
        return Math.max(position.getY()+this.getHauteur(), position.getY());
    }

    // ========================================
    // =========    SETERS      ===============

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
    // =========    OTHERS      ===============

    public void déplacerVers(int x, int y) {
        this.position.déplacerVers(x,y);
    }

    /**
     * Déplacer la forme selon un vecteur de translation
     * @param deltaX Le déplacement selon l'axe X (horizontal)
     * @param deltaY Le déplacement selon l'axe Y (vertical)
     */
    public void déplacerDe(int deltaX, int deltaY) {
        this.position.déplacerDe(deltaX, deltaY);
    }


    // ========================================

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.FRANCE));
        decimalFormat.setMaximumFractionDigits(this instanceof Rectangle ? 0 : 2);

        return "["+this.getClass().getSimpleName()+']'+
                " pos : "+position.toString()+
                " dim : "+getLargeur()+" x "+getHauteur()+
                " périmètre : "+decimalFormat.format(this.périmètre())+
                " aire : "+decimalFormat.format(this.aire());
    }

    // ========================================

    public abstract double aire();
    public abstract double périmètre();

    public abstract boolean contient(int x, int y);
    /*public abstract boolean contient(Point testPosition);*/

    public abstract boolean contient(Point testPosition);

}
