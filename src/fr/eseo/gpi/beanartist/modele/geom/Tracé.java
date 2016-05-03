package fr.eseo.gpi.beanartist.modele.geom;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

/**
 * @author Elphege
 * @date 13/04/2016
 * @project gpi_binome
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


    public void ajouterLigneVers(Point point) {
        Ligne  ligne = new Ligne();
        ligne.setP1(this.lignes.get(lignes.size() - 1).getP2());
        ligne.setP2(point);
        this.lignes.add(ligne);
        this.setPosition(findPosition());
    }


    public void setX(int newX) {
        this.déplacerVers(newX, this.getPosition().getY());
    }

    public void setY(int newY) {
        this.déplacerVers(this.getPosition().getX(), newY);
    }

    public void setPosition(Point point) {
        Point p1 = this.getPosition();
        int deltaX = point.getX() - p1.getX(),
                deltaY = point.getY() - p1.getY();

        this.déplacerDe(deltaX, deltaY);
        super.setPosition(point);
    }

    public void setDimensions(int newLargeur, int newHauteur) {
        setLargeur(newLargeur);
        setHauteur(newHauteur);
    }


    public void setHauteur(int newHauteur){
        int minY = getMinY();
        int minYSecure = getMinY();
        int minYGhost = getLignes().get(0).getP1().getY();
        double facteur = (double) newHauteur/ getHauteur();
        for(int i = 0; i< getLignes().size(); i++){
            Ligne currentLine = getLignes().get(i);
            int newHauteurLigne = (int) Math.round(currentLine.getHauteur() * facteur);
            currentLine.setHauteur(newHauteurLigne);
            minYGhost = Math.min(currentLine.getP2().getY(), minYGhost);
            if(i + 1 < lignes.size()) {
                lignes.get(i + 1).setPosition(currentLine.getP2());
            }
        }
        déplacerVers(0, getMinY() - minYGhost);
        minY = minYSecure;
        int maxY = minY + newHauteur;
        super.setPosition(new Point(getMinX(), maxY));
        super.setHauteur(hauteur);
    }

    public void setLargeur(int newLargeur){
        int maxX = getMaxX();
        int maxXSecure = maxX;
        int maxXGhost = getLignes().get(0).getP1().getX();
        double facteur = (double) newLargeur/ getLargeur();
        for(int i = 0; i< getLignes().size(); i++){
            Ligne currentLine = getLignes().get(i);
            int newLargeurLigne = (int) Math.round(currentLine.getLargeur() * facteur);
            currentLine.setLargeur(newLargeurLigne);
            maxXGhost = Math.max(currentLine.getP2().getX(), maxXGhost);
            if(i + 1 < lignes.size()) {
                lignes.get(i + 1).setPosition(currentLine.getP2());
            }
        }
        déplacerVers(0, maxX - maxXGhost);
        maxX = maxXGhost;
        int minX = maxX + newLargeur;
        super.setPosition(new Point(minX, getMaxY()));
        super.setHauteur(hauteur);
    }

    // --------- GETTERS ----------- //

    public int findLargeur() {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;

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
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

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
        int xMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

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


    public int getMinY() {
        int yMin = Integer.MIN_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMinY() < yMin) {
                yMin = ligne.getMinY();
            }
        }
        return yMin;
    }

    public int getMaxY() {
        int yMax = Integer.MAX_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMaxY() < yMax) {
                yMax = ligne.getMaxY();
            }
        }
        return yMax;
    }

    public int getMinX() {
        int xMin = Integer.MIN_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMinX() < xMin) {
                xMin = ligne.getMinX();
            }
        }
        return xMin;
    }

    public int getMaxX() {
        int xMax = Integer.MAX_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMaxX() < xMax) {
                xMax = ligne.getMaxX();
            }
        }
        return xMax;
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

    /*public double périmètre() {
        ListIterator<Ligne> listI = this.lignes.listIterator();
        double returnedPerimetre = 0;

        while (listI.hasNext())
            returnedPerimetre += listI.next().périmètre();

        return returnedPerimetre;
    }*/


    public double périmètre() {
        double périmètre = 0;
        for (Ligne ligne : lignes){
            périmètre += ligne.périmètre();
        }
        return périmètre;
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