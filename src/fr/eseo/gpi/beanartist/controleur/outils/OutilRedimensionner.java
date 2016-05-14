package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class OutilRedimensionner extends Outil {
    private OutilSélection outilSélection;

    /**
     * Initialise un déplacement
     * @param panneauDessin
     * @param outilSélection
     */
    public OutilRedimensionner(PanneauDessin panneauDessin, OutilSélection outilSélection) {
        super(panneauDessin);
        this.outilSélection = outilSélection;
    }

//    @Override
//    public void associer(PanneauDessin newPanneauDessin) {
//        super.associer(newPanneauDessin, false);
//    }

    @Override
    public void mousePressed (MouseEvent e){
//        this.mouseClicked(e);
        super.mousePressed(e);
        setFin(getDébut());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            Forme forme = outilSélection.getForme();
            setDébut(getFin());
            super.mouseDragged(e);
            forme.setLargeur(forme.getLargeur() + getFin().getX()-getDébut().getX());
            forme.setHauteur(forme.getHauteur() + getFin().getY()-getDébut().getY());
            outilSélection.afficherFormeSélectionnée();
            this.getPanneauDessin().repaint();
        } catch (NullPointerException excpetion) {}
    }


}
