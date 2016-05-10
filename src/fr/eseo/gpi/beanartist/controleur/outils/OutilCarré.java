package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.modele.geom.Forme;
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
    protected VueForme créerVueForme() {
        int coté = this.getFin().getY() - this.getDébut().getY();
        return new VueCarré(new Carré(this.getDébut(), coté), this.getPanneauDessin().getCouleurLigne(), true);
    }
}
