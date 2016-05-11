package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Ligne;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueLigne;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

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
    public void mousePressed(MouseEvent e) {
        forme = new Ligne();
        super.mousePressed(e);
        forme.setPosition(getDébut());
    }

    @Override
    protected void updateForme() {
        forme.setP2(getFin().getX(), getFin().getY());
    }

    @Override
    protected VueForme créerVueForme() {
        return new VueLigne((Ligne)forme, this.getPanneauDessin().getCouleurLigne());
    }

}
