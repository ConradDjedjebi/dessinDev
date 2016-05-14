package fr.eseo.gpi.beanartist.modele.geom;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @author duhamean
 * @date 31/03/16
 * @project gpi_binome
 */
public abstract class Forme implements Cloneable {
    public static final int LARGEUR_PAR_DÉFAUT = 70;
    public static final int HAUTEUR_PAR_DÉFAUT = 50;
    protected int largeur, hauteur;

    private Point position;
    private boolean modeRemplissage;


    // =========    CONTRUCTORS ===============


    public Forme() {
        this(new Point());
    }

    public Forme(boolean modeRemplissage) {
        this.modeRemplissage = modeRemplissage;
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
        return getMaxX()-getMinX();
    }

    public int getHauteur() {
        return getMaxY()-getMinY();
    }


    // =========    GEOM        ===============

    public int getMinX() {
        return Math.min(position.getX()+this.largeur, position.getX());
    }

    public int getMaxX() {
        return Math.max(position.getX()+this.largeur, position.getX());
    }

    public int getMinY() {
        return Math.min(position.getY()+this.hauteur, position.getY());
    }

    public int getMaxY() {
        return Math.max(position.getY()+this.hauteur, position.getY());
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

    public void setP2(int p2X, int p2Y) {
        setLargeur(p2X-getX());
        setHauteur(p2Y-getY());
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

    public abstract boolean contient(Point testPosition);

    public boolean estModeRemplissage() {
        return modeRemplissage;
    }

    public void setModeRemplissage(boolean modeRemplissage) {
        this.modeRemplissage = modeRemplissage;
    }

    public Forme clone () {
        try {
            Forme forme = (Forme) super.clone();
            forme.setPosition(forme.getPosition().clone());
            return forme;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDefaults() {
        this.setHauteur(HAUTEUR_PAR_DÉFAUT);
        this.setLargeur(LARGEUR_PAR_DÉFAUT);
    }
}
