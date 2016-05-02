package fr.eseo.gpi.beanartist.modele.geom;
import java.text.NumberFormat;
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

    public Ellipse(Point position, int largeur, int hauteur) {
        super(position, largeur, hauteur);
    }

    public Ellipse(int x, int y, int largeur, int hauteur) {
        this(new Point(x,y), largeur, hauteur);
    }

    public Ellipse(int largeur, int hauteur) {
        this(new Point(), largeur, hauteur);
    }

    public Ellipse(Point position) {
        this(position, Forme.LARGEUR_PAR_DÉFAUT, Forme.HAUTEUR_PAR_DÉFAUT);
    }


    //------------- VARIABLES D'INSTANCE -------//

    double xCenter = getPosition().getX() + getLargeur()/2;
    double yCenter = getPosition().getY() - getHauteur()/2;


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
    /*public double périmètre(){
        //return Math.PI/2*(
                //    3*(this.largeur+this.hauteur) -
                   // Math.sqrt((3*this.largeur+this.hauteur)*(3*this.hauteur+this.largeur)));
        double a = this.hauteur/2;
        double b = this.largeur/2;
        double h = ((a-b)*(a-b))/((a+b)*(a+b));
        return Math.PI*(a+b)*(1+(3*h/(10+ Math.sqrt(4-3*h))));
    }*/


    public double périmètre (){
        double h = (Math.pow(((getHauteur()/2d)-(getLargeur()/2d)),2)) / (Math.pow(((getHauteur()/2d)+(getLargeur()/2d)),2));
        return Math.PI *((getHauteur()+getLargeur())/2d) * (1+((3*h)/(10 + Math.sqrt(4-3*h))));
    }

    @Override
    public boolean contient(int x, int y){
        double xMilieu = this.getX() + this.getLargeur()/2.0;
        double yMilieu = this.getY() + this.getHauteur()/2.0;
        double disque = Math.pow(x - xMilieu, 2)/Math.pow(this.getLargeur()/2.0, 2)
                +Math.pow(y - yMilieu, 2)/Math.pow(this.getHauteur()/2.0, 2);
        return disque <= 1;
    }


    public boolean contient(Point point) {
        return this.contient(point.getX(), point.getY());
    }

}
