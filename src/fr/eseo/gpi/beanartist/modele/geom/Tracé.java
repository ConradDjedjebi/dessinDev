package fr.eseo.gpi.beanartist.modele.geom;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Elphege
 * @date 13/04/2016
 * @project gpi_binome
 */
public class Tracé extends Forme {
    private List<Ligne> lignes;
    private boolean falseOrigin = false;

    //---------- CONSTRUCTORS ----------//


    public Tracé(Point p1, Point p2) {
        super(new Point(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY())), Math.max(p1.getX(), p2.getX())
                - Math.min(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()) - Math.min(p1.getY(), p2.getY()) );
        lignes = new ArrayList<>();
        lignes.add(new Ligne(p1, p2));
    }

    public Tracé(Point p1) {
        this(p1, p1);
        this.falseOrigin = true;
    }

    public Tracé() {
        this (new Point());
    }


    //------------ SETTERS -------------//


    public void ajouterLigneVers(Point point) {
        if(this.falseOrigin)
        {
            this.lignes.get(0).setP1(this.lignes.get(0).getP2());
            this.lignes.get(0).setP2(point);
            this.falseOrigin = false;
        }
        else
            this.lignes.add(new Ligne(this.lignes.get(lignes.size() - 1).getP2(), point));
        super.setHauteur(findHauteur());
        super.setLargeur(findLargeur());
        super.setPosition(findPosition());
    }



    public void setX(int newX) {
        this.déplacerVers(newX, this.getPosition().getY());
    }

    public void setY(int newY) {
        this.déplacerVers(this.getPosition().getX(), newY);
    }

    public void setPosition(Point point) {
        Point p1 = this.getPosition();
        int deltaX = point.getX() - p1.getX();
        int deltaY = point.getY() - p1.getY();

        this.déplacerDe(deltaX, deltaY);
        super.setPosition(point);
    }

    public void setDimensions(int newLargeur, int newHauteur) {
        setLargeur(newLargeur);
        setHauteur(newHauteur);
    }



    public void setHauteur(int newHauteur){
        int newY1;
        int minY = getMinY();
        double coeff = (double) newHauteur / getHauteur();
        for (Ligne ligne : lignes){
            newY1 = minY + (int) Math.round((ligne.getP1().getY() - minY) * coeff);
            ligne.getP1().setY(newY1);
            ligne.setHauteur((int) Math.round(ligne.getHauteur()*coeff));
        }
        super.setHauteur(newHauteur);
    }

    public void setLargeur(int newLargeur){
        int newX1;
        int minX = getMinX();
        double coeff = (double) newLargeur / getLargeur();
        for (Ligne ligne : lignes){
            newX1 = minX + (int) Math.round((ligne.getP1().getX() - minX) * coeff);
            ligne.getP1().setX(newX1);
            ligne.setLargeur((int) Math.round(ligne.getLargeur()*coeff));
        }
        super.setLargeur(newLargeur);
    }




    // --------- GETTERS ----------- //

    public int getMinY() {
        int yMin = Integer.MAX_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMinY() < yMin) {
                yMin = ligne.getMinY();
            }
        }
        return yMin;
    }

    public int getMaxY() {
        int yMax = Integer.MIN_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMaxY() > yMax) {
                yMax = ligne.getMaxY();
            }
        }
        return yMax;
    }

    public int getMinX() {
        int xMin = Integer.MAX_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMinX() < xMin) {
                xMin = ligne.getMinX();
            }
        }
        return xMin;
    }

    public int getMaxX() {
        int xMax = Integer.MIN_VALUE;
        for (Ligne ligne : lignes) {
            if (ligne.getMaxX() > xMax) {
                xMax = ligne.getMaxX();
            }
        }
        return xMax;
    }


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
        return new Point(xMin, yMax-getHauteur());
    }





    public List<Ligne> getLignes() {
        return lignes;
    }

    //----------- METHODS -----------//

    //Lors de la création d'un tracé, il n'y a pas de ligne. Or cette méthode est appelée

    public void déplacerDe(int deltaX, int deltaY) {

        for (Ligne ligne : lignes) ligne.déplacerDe(deltaX, deltaY);
        super.setPosition(lignes.get(0).getP1());
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
        double périmètre = 0;
        for (Ligne ligne : lignes){
            périmètre += ligne.périmètre();
        }
        return périmètre;
    }

    @Override
    public boolean contient(int x, int y) {
        int count=0;
        try {
            while(!lignes.get(count++).contient(x,y)) {}
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public Forme clone() {
        Tracé clone = (Tracé) super.clone();

        clone.lignes.clear();
        clone.lignes.addAll(this.lignes.stream().map(Ligne::clone).collect(Collectors.toList()));

        return clone;
    }

    public boolean contient(Point testPosition) {
        return this.contient(testPosition.getX(), testPosition.getY());
    }

}