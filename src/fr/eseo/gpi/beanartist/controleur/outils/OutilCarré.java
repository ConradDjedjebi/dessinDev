package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueCarré;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * @author Elphege
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilCarré extends OutilForme{
    public OutilCarré(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected void updateForme() {
        int coté = this.getFin().getY() - this.getDébut().getY();
         new Carré(this.getDébut(), coté);
    }
    @Override
    protected VueForme créerVueForme() {
        return new VueCarré((Carré)forme, this.getPanneauDessin().getCouleurLigne(), true);
    }
}
