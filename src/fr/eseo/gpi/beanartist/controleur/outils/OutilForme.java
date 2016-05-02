package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Forme;
//import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author duhamean
 * @date 28/04/16
 * @project gpi_binome
 */
public abstract class OutilForme extends Outil {

    public OutilForme(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    public void mouseClicked (MouseEvent e){
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        this.getPanneauDessin().ajouterVueForme(this.créerVueForme());
    }

    protected abstract VueForme créerVueForme();
}
