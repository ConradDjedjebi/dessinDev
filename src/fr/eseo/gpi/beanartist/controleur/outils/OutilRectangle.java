package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilRectangle extends OutilForme {
    Rectangle rectangle;

    public OutilRectangle(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected VueForme créerVueForme() {
        int largeurRectangle = this.getFin().getX() - this.getDébut().getX();
        int hauteurRectangle = this.getDébut().getY() - this.getFin().getY();
        rectangle = new Rectangle(this.getDébut(), largeurRectangle, hauteurRectangle);
        return new VueRectangle(rectangle, this.getPanneauDessin().getCouleurLigne(), true);
    }

    @Override
    public Forme getForme(){
        return rectangle;
    }
}
