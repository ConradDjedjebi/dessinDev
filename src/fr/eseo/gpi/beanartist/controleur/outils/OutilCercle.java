package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Cercle;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * @author Elphege
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilCercle extends OutilEllipse{

    public OutilCercle(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected void updateForme() {
        int coté = this.getFin().getY() - this.getDébut().getY();
         new Cercle(this.getDébut(), coté);
    }
//    @Override
//    protected VueForme créerVueForme() {
//        return super.créerVueForme();
//    }

}
