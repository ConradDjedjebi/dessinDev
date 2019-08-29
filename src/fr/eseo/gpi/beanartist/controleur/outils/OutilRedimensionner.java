package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.actions.AbstractSelectionAction;
import fr.eseo.gpi.beanartist.controleur.actions.ActionRedimensionner;
import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.modele.geom.Tracé;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class OutilRedimensionner extends Outil {
    private OutilSélection outilSélection;

    protected static final Cursor cursor = new Cursor(Cursor.NW_RESIZE_CURSOR);

    /**
     * Initialise un déplacement
     * @param panneauDessin
     * @param outilSélection
     */
    public OutilRedimensionner(PanneauDessin panneauDessin, OutilSélection outilSélection) {
        super(panneauDessin);
        this.outilSélection = outilSélection;
    }

    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);
        setFin(getDébut());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            Forme forme = outilSélection.getForme();
            setDébut(getFin());
            super.mouseDragged(e);
            //if(forme instanceof Tracé)
            forme.setDimensions(forme.getLargeur() + getDeltaX(), forme.getHauteur() + getDeltaY());
            outilSélection.afficherFormeSélectionnée();
            this.getPanneauDessin().repaint();
        } catch (NullPointerException excpetion) {}
        catch (Error error) {
            getPanneauDessin().getLabel().setText(error.getMessage());
        }
    }

    public Cursor getCursor() {
        return cursor;
    }
}
