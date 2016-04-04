package fr.eseo.gpi.beanartist.modele.geom;

/**
 * @author duhamean
 * @date 31/03/16
 * @project gpi_binome
 */
public class Point {
    int x, y;


    /**
     * Constructeur de la classe Point (coordonnées connues)
     * @param x les coordonnées du point
     * @param y les coordonnées du point
     **/
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Constructeur de la classe Point (coordonnées par défaut)
     **/
    public Point (){
        this.x=0;
        this.y=0;
    }


    //------------- ACCESSEURS -------------//

    /**
     * Renvoie la coordonné x actuelle du point
     * @return : abcisse (int)
     **/
    public int getX(){
        return this.x;
    }

    /**
     * Renvoie la coordonné y actuelle du point
     * @return : ordonné (int)
     **/
    public int getY(){
        return this.y;
    }


    public void setX(int newPos){
        this.x = newPos;
    }

    /**
     * Renvoie la coordonné y actuelle du point
     **/
    public void setY(int newPos){
        this.y = newPos;
    }


    /**
     * Permet de déplacer le point
     **/
    public void déplacerVers(int newX, int newY){
        this.x=newX;
        this.y=newY;
    }

    /**
     * Permet de déplacer le point selon un vecteur
     * @param deltaX
     * @param deltaY
     **/
    public void déplacerDe(int deltaX, int deltaY){
        this.x+=deltaX;
        this.y+=deltaY;
    }


    //------------- AFFICHAGE -------------//

    /**
     * Renvoie en String les coordonnés actuelles du point
     * @return : message (String)
     **/
    public String toString(){
        return String.format("(%d,%d)", x, y);
    }

}
