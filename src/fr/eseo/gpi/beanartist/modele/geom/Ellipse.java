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
    public boolean contient(int x, int y) {
        double xSurA = x/this.getLargeur();
        double ySurB = y/this.getHauteur();

        return xSurA*xSurA + ySurB*ySurB < 1;
    }


    /*public boolean contient(Point point) {
        x = point.getX();
        y = point.getY();
        double xSurA = x/this.getLargeur();
        double ySurB = y/this.getHauteur();

        return xSurA*xSurA + ySurB*ySurB < 1;
    }*/

}
