package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Cercle;
import fr.eseo.gpi.beanartist.vue.geom.VueCercle;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilCercle extends OutilForme{
    Cercle cercle;

    public OutilCercle(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected VueForme créerVueForme() {
        int largeurCercle = this.getFin().getX() - this.getDébut().getX();
        int hauteurCercle = this.getDébut().getY() - this.getFin().getY();
        int diamètre = Math.max(largeurCercle, hauteurCercle);
        return new VueCercle(new Cercle(this.getDébut(), diamètre), this.getPanneauDessin().getCouleurLigne(), true);
    }
}
