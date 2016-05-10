package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author duhamean
 * @date 28/04/16
 * @project gpi_binome
 */
public abstract class OutilForme extends Outil {

    Forme forme;

    public OutilForme(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);
        this.getPanneauDessin().ajouterVueForme(this.créerVueForme());
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        updateForme();
        this.getPanneauDessin().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    protected abstract void updateForme();
    protected abstract VueForme créerVueForme();
    //protected abstract VueForme créerVueFormeParDéfaut();
}
