package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class OutilDéplacer extends Outil {
    private OutilSélection outilSélection;

    /**
     * Initialise un déplacement
     * @param panneauDessin
     * @param outilSélection
     */
    public OutilDéplacer(PanneauDessin panneauDessin, OutilSélection outilSélection) {
        super(panneauDessin);
        this.outilSélection = outilSélection;
    }

    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            super.mouseDragged(e);
            outilSélection.getForme().déplacerDe(getFin().getX()-getDébut().getX(), getFin().getY() - getDébut().getX());
            this.getPanneauDessin().repaint();
        } catch (NullPointerException excpetion) {}
    }
}
