package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.modele.geom.Ligne;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueLigne;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * @author duhamean
 * @date 02/05/16
 * @project gpi_binome
 */
public class OutilLigne extends OutilForme {

    public OutilLigne(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected VueForme créerVueForme() {
        return new VueLigne(new Ligne(this.getDébut(), this.getFin()), this.getPanneauDessin().getCouleurLigne());
    }

}
