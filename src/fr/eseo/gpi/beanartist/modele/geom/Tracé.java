package fr.eseo.gpi.beanartist.modele.geom;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

/**
 * Created by Elphege on 13/04/2016.
 */
public class Tracé extends Forme {
    private List<Ligne> lignes;

    //---------- CONSTRUCTORS ----------//


    public Tracé(Point p1, Point p2) {
        super();
        this.lignes = new ArrayList<Ligne>();
        int xMin = Math.min(p1.getX(), p2.getX());
        int xMax = Math.max(p1.getX(), p2.getX());
        int yMin = Math.min(p1.getY(), p2.getY());
        int yMax = Math.max(p1.getY(), p2.getY());
        setLargeur(xMax - xMin);
        setHauteur(yMax - yMin);
        setPosition(new Point(xMin, yMax));
    }


    //------------ SETTERS -------------//


    public void ajouterLigneVers(Point p) {
        Ligne  ligne = new Ligne();
        ligne.setP1(this.lignes.get(lignes.size() - 1).getP2());
        ligne.setP2(p);
        this.lignes.add(ligne);
        this.setPosition(findPosition());
    }


    public void setX(int newX) {
        this.déplacerVers(newX, this.getPosition().getY());
    }

    public void setY(int newY) {
        this.déplacerVers(this.getPosition().getX(), newY);
    }

    public void setPosition(Point p) {
        Point p1 = this.getPosition();
        int deltaX = p.getX() - p1.getX(),
                deltaY = p.getY() - p1.getY();

        this.déplacerDe(deltaX, deltaY);
        super.setPosition(p);
    }

    public void setDimensions(int newLargeur, int newHauteur) {
        setLargeur(newLargeur);
        setHauteur(newHauteur);
    }


    public void setLargeur(int newLargeur){
        int DeltaX = newLargeur - this.getLargeur();
        for(int i=0; i< lignes.size(); i++){
            int xP2 = lignes.get(i).getP2().getX() + DeltaX;
            lignes.get(i).getP2().setX(xP2);
        }
    }

    public void setHauteur(int newHauteur){
        int DeltaY = newHauteur - this.getHauteur();
        for(int i=0; i< lignes.size(); i++){
            int yP2 = lignes.get(i).getP2().getY() + DeltaY;
            lignes.get(i).getP2().setY(yP2);
        }
    }

    // --------- GETTERS ----------- //

    public int findLargeur() {
        int xMin = Integer.MIN_VALUE;
        int xMax = Integer.MAX_VALUE;

        for (Ligne ligne :
                lignes) {
            if (ligne.getMinX() < xMin){
                xMin = ligne.getMinX();
            }
            if (xMax < ligne.getMaxX()) {
                xMax = ligne.getMaxX();
            }
        }
        return xMax - xMin;
    }


    public int findHauteur() {
        int yMin = Integer.MIN_VALUE;
        int yMax = Integer.MAX_VALUE;

        for (Ligne ligne :
                lignes) {
            if (ligne.getMinY() < yMin){
                yMin = ligne.getMinY();
            }
            if (yMax < ligne.getMaxY()) {
                yMax = ligne.getMaxY();
            }
        }
        return yMax - yMin;
    }


    public Point findPosition() {
        int xMin = Integer.MIN_VALUE;
        int yMax = Integer.MAX_VALUE;

        for (Ligne ligne :
                lignes) {
            if (ligne.getMinX() < xMin){
                xMin = ligne.getMinX();
            }
            if (yMax < ligne.getMaxY()) {
                yMax = ligne.getMaxY();
            }
        }
        return new Point(xMin, yMax);
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    //----------- METHODS -----------//


    public void déplacerDe(int deltaX, int deltaY) {

        for (Ligne ligne : this.lignes) ligne.déplacerDe(deltaX, deltaY);
    }

    public void déplacerVers(int newX, int newY) {
        this.setPosition(new Point(newX, newY));
    }


    //------------- PRINTS -------------//

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.FRANCE));
        decimalFormat.setMaximumFractionDigits(2);
        double l = 0.00;
        for(int i=0; i<lignes.size(); i++){
            l = l + lignes.get(i).périmètre();
        }
        return "["+this.getClass().getSimpleName()+']'+
                " pos : "+this.getPosition().toString()+
                " dim : "+getLargeur()+" x "+getHauteur()+
                " longueur : "+decimalFormat.format(l)+
                " nbLignes : "+lignes.size();
    }


    //------------- OTHERS -------------//

    public double aire() {
        return 0;
    }

    public double périmètre() {
        ListIterator<Ligne> listI = this.lignes.listIterator();
        double returnedPerimetre = 0;

        while (listI.hasNext())
            returnedPerimetre += listI.next().périmètre();

        return returnedPerimetre;
    }

    public boolean contient(int x, int y) {
        for (Ligne ligne : lignes) {
            if (ligne.contient(x, y)) {
                return true;
            }
        }

        return false;
    }

    public boolean contient(Point testPosition) {
        return this.contient(testPosition.getX(), testPosition.getY());
    }

}