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

    public final static boolean DEFAULT_REMPLISSAGE_MODE = false;

    protected Forme forme;

    public OutilForme(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);
        this.getPanneauDessin().ajouterVueForme(this.créerVueForme());
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        getPanneauDessin().getLabel().setText(forme.toString());
        updateForme();
        this.getPanneauDessin().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    protected void updateForme() {
        forme.setLargeur(getDeltaX());
        forme.setHauteur(getDeltaY());
    }

    protected abstract VueForme créerVueForme();
    //protected abstract VueForme créerVueFormeParDéfaut();
}
