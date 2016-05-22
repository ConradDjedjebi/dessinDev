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
    private Point début, fin;

    public Outil (PanneauDessin dessin) {
        this.associer(dessin);
    }

    // ======== ASSOCIATION DU PANNEAU DESSIN =============

    public void associer(PanneauDessin newPanneauDessin) {
        libérer();

        setPanneauDessin(newPanneauDessin);
        newPanneauDessin.setOutilCourant(this);
        newPanneauDessin.addMouseListener(this);
        newPanneauDessin.addMouseMotionListener(this);
    }

    /**
     * Public access to the libérer method -- which have to be private, thanks to the Assignment Center
     * @param falseValue Useless value to overload the private method
     */
    public void libérer(boolean falseValue) {
        this.libérer();
    }

    private void libérer() // LET IT GO
    {
        try {
            getPanneauDessin().removeMouseListener(getPanneauDessin().getOutilCourant());
            getPanneauDessin().removeMouseMotionListener(getPanneauDessin().getOutilCourant());
            getPanneauDessin().setOutilCourant(null);
            getPanneauDessin().setGomme(null);
            getPanneauDessin().repaint();
            setPanneauDessin(null);
        } catch (NullPointerException e) {}
    }

    public PanneauDessin getPanneauDessin() {
        return dessin;
    }

    public void setPanneauDessin(PanneauDessin newPanneauDessin) {
        dessin = newPanneauDessin;
    }


    // ================== MOUSE EVENTS =====================

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


    // ============== ACCESSEURS ==============

    public Point getDébut() {
        return début;
    }
    public Point getFin() {
        return fin;
    }

    public void setDébut(Point newDébut) {
        this.début = newDébut;
    }
    public void setFin(Point newFin) {
        this.fin = newFin;
    }

    protected int getDeltaX() {
        return this.getFin().getX() - this.getDébut().getX();
    }
    protected int getDeltaY() {
        return this.getFin().getY() - this.getDébut().getY();
    }

}
