package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Ellipse;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilEllipse extends OutilForme {
    Ellipse ellipse;

    public OutilEllipse(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected VueForme créerVueForme() {
        int largeurEllipse = this.getFin().getX() - this.getDébut().getX();
        int hauteurEllipse = this.getDébut().getY() - this.getFin().getY();
        return new VueEllipse(new Ellipse(this.getDébut(), largeurEllipse, hauteurEllipse), this.getPanneauDessin().getCouleurLigne(), true);
    }
}
