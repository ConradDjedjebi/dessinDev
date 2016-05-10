package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Ellipse;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * @author Elphege
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilEllipse extends OutilForme {

    public OutilEllipse(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    protected void updateForme() {
        int largeurEllipse = this.getFin().getX() - this.getDébut().getX();
        int hauteurEllipse = this.getFin().getY() - this.getDébut().getY();
         new Ellipse(this.getDébut(), largeurEllipse, hauteurEllipse);
    }
    @Override
    protected VueForme créerVueForme() {
        return new VueEllipse(
                (Ellipse)forme,
                this.getPanneauDessin().getCouleurLigne(), true);
    }

}
