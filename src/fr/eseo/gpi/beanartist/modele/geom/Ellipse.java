package fr.eseo.gpi.beanartist.modele.geom;

/**
 * @author duhamean
 * @date 31/03/16
 * @project gpi_binome
 */
public class Ellipse extends Forme {
    //------------- CONSTRUCTEUR -------------//

    public Ellipse() {
        this(new Point());
    }

    public Ellipse(Point center, int largeur, int hauteur) {
        super(center, largeur, hauteur);
    }

    public Ellipse(int x, int y, int largeur, int hauteur) {
        this(new Point(x,y), largeur, hauteur);
    }

    public Ellipse(int largeur, int hauteur) {
        this(new Point(), largeur, hauteur);
    }

    public Ellipse(Point center) {
        this(center, Forme.LARGEUR_PAR_DÉFAUT, Forme.HAUTEUR_PAR_DÉFAUT);
    }

    //------------- AFFICHAGE -------------//

    /**
     * Renvoie en String les informations sur Ellipse
     * @return message (String)
     **/
    public String toString(){
        return this.toString("Ellipse");
    }


    //----------- AUTRES METHODES -----------//


    /**
     * Renvoie l'air
     * @return air (int)
     **/
    public double aire(){
        return Math.PI*this.largeur*this.hauteur/4;
    }

    /**
     * Renvoie le périmètre de l'ellipse (approximation de Ramanujan)
     * @return perimeter
     * @todo L'approximation n'est pas la bonne !
     **/
    public double périmètre(){
        return Math.PI/2*(
                        3*(this.largeur+this.hauteur) -
                        Math.sqrt((3*this.largeur+this.hauteur)*(3*this.hauteur+this.largeur)));
    }

    @Override
    public boolean contient(int x, int y) {
        double xSurA = x/this.getLargeur();
        double ySurB = y/this.getHauteur();

        return xSurA*xSurA + ySurB*ySurB < 1;
    }
}
