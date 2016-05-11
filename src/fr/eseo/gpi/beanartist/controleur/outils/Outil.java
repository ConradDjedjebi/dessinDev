package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author duhamean
 * @date 28/04/16
 * @project gpi_binome
 */
public abstract class Outil implements MouseMotionListener, MouseListener {

    private static PanneauDessin dessin;
    private PanneauDessin panneauDessin;
    private Point début, fin;

    public Outil (PanneauDessin dessin) {
        this.associer(dessin);
    }

    public void associer(PanneauDessin newPanneauDessin) {
        libérer();
        setPanneauDessin(newPanneauDessin);
        newPanneauDessin.setOutilCourant(this);
        newPanneauDessin.addMouseListener(this);
        newPanneauDessin.addMouseMotionListener(this);
    }
    private void libérer() // LET IT GO
    {
        try {
            getPanneauDessin().removeMouseListener(getPanneauDessin().getOutilCourant());
            getPanneauDessin().removeMouseMotionListener(getPanneauDessin().getOutilCourant());
            getPanneauDessin().setOutilCourant(null);
            setPanneauDessin(null);
        } catch (NullPointerException e) {}
    }

    public PanneauDessin getPanneauDessin() {
        return dessin;
    }

    public void setPanneauDessin(PanneauDessin newPanneauDessin) {
        dessin = newPanneauDessin;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setDébut (new Point(e.getX(), e.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setDébut (new Point(e.getX(), e.getY()));
        setFin (new Point());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setFin (new Point(e.getX(), e.getY()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setFin (new Point(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public Point getDébut() {
        return début;
    }

    public void setDébut(Point newDébut) {
        this.début = newDébut;
    }

    public Point getFin() {
        return fin;
    }

    public void setFin(Point newFin) {
        this.fin = newFin;
    }


}
