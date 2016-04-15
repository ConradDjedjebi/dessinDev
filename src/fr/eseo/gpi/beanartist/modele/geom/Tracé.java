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
    private ArrayList<Ligne> lignes;
    private List<Ligne> lignes1;

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
        this.setPosition(findPosition(lignes));
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

    public void setDimensions(int largeur, int hauteur) {
        setLargeur(largeur);
        setHauteur(hauteur);
    }


    public void setLargeur(int newLargeur){
        //
    }

    public void setHauteur(int newHauteur){
        //
    }

    // --------- GETTERS ----------- //

    public int findLargeur(ArrayList<Ligne> lignes) {
        int xMin = lignes.get(0).getMinX();
        int xMax = lignes.get(0).getMaxY();
        for (int i = 1; i < lignes.size(); i++) {
            if (xMin > lignes.get(i).getMinX()) {
                xMin = lignes.get(i).getMinX();
            }
        }
        for (int i = 1; i < lignes.size(); i++) {
            if (xMax < lignes.get(i).getMaxX()) {
                xMax = lignes.get(i).getMaxX();
            }
        }
        return xMax - xMin;
    }


    public int findHauteur(ArrayList<Ligne> lignes) {
        int yMin = lignes.get(0).getMinY();
        int yMax = lignes.get(0).getMaxY();
        for (int i = 1; i < lignes.size(); i++) {
            if (yMin > lignes.get(i).getMinY()) {
                yMin = lignes.get(i).getMinY();
            }
        }
        for (int i = 1; i < lignes.size(); i++) {
            if (yMax < lignes.get(i).getMaxY()) {
                yMax = lignes.get(i).getMaxY();
            }
        }
        return yMax - yMin;
    }


    public Point findPosition(ArrayList<Ligne> lignes) {
        int xMin = lignes.get(0).getMinX();
        int yMax = lignes.get(0).getMaxY();
        for (int i = 1; i < lignes.size(); i++) {
            if (xMin > lignes.get(i).getMinX()) {
                xMin = lignes.get(i).getMinX();
            }
            if (yMax < lignes.get(i).getMaxY()) {
                yMax = lignes.get(i).getMaxY();
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
        boolean l = false;
        int i = 0;
        while(l==false && i <lignes.size()){
            l= lignes.get(i).contient(x,y);
        }
        return l;
    }

    public boolean contient(Point testPosition) {
        return this.contient(testPosition.getX(), testPosition.getY());
    }

}