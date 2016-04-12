package fr.eseo.gpi.beanartist.modele.geom;

/**
 * @author duhamean
 * @date 31/03/16
 * @project gpi_binome
 */
public class Rectangle extends Forme {
    //------------- CONSTRUCTEUR -------------//

    public Rectangle() {
        this(new Point());
    }

    public Rectangle(Point position, int largeur, int hauteur) {
        super(position, largeur, hauteur);
    }

    public Rectangle(int x, int y, int largeur, int hauteur) {
        this(new Point(x,y), largeur, hauteur);
    }

    public Rectangle(int largeur, int hauteur) {
        this(new Point(), largeur, hauteur);
    }

    public Rectangle(Point position) {
        this(position, Forme.LARGEUR_PAR_DÉFAUT, Forme.HAUTEUR_PAR_DÉFAUT);
    }


    //----------- AUTRES METHODES -----------//


    /**
     * Renvoie l'air du rectangle
     * @return air (int)
     **/
    public double aire(){
        return this.getLargeur()*this.getHauteur();
    }

    /**
     * Renvoie le périmètre du rectangle
     * @return perimeter (int)
     **/
    public double périmètre(){
        return 2*this.getLargeur()+2*this.getHauteur();
    }


    @Override
    public boolean contient(int x, int y) {
        return this.getMinX()<x && this.getMaxX()>x && this.getMinY()<y && this.getMaxY()>y;
    }

    public boolean contient(Point point) {
        int x = point.getX();
        int y = point.getY();
        return this.getMinX()<x && this.getMaxX()>x && this.getMinY()<y && this.getMaxY()>y;
    }
}

