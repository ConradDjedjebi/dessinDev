package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.*;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 20/05/2016
 * @project gpi_binome
 */
public class OutilGomme extends OutilForme {
    public static final Color ERASER_COLOR = new Color(255, 153, 8);
    private static final int ERASER_DIM_X = 130;
    private static final int ERASER_DIM_Y = 60;

    protected static final Cursor cursor = new Cursor(Cursor.TEXT_CURSOR);

    /**
     * Initialise un déplacement
     * @param panneauDessin Le panneau du dessin principal
     */
    public OutilGomme(PanneauDessin panneauDessin) {
        super(panneauDessin);
        forme = new Gomme ();
    }

    @Override
    protected void updateForme() {
        // On déplace la gomme
        forme.déplacerDe(getFin().getX() - getDébut().getX(), getFin().getY() - getDébut().getY());

        // On efface les formes qui sont sur le chemin de la gomme
        try {
            for (int x = forme.getMinX(), xMax = forme.getMaxX(); x < xMax; x++) {
                for (int y = forme.getMinY(), yMax = forme.getMaxY(); y < yMax; y++) {
                    // Pour les points de bordures, on vérifie si le point est bien "contenui" par la gomme
                    if (forme.contient(x, y)) {
                        for (VueForme vueForme : getPanneauDessin().getVueFormes()) {
                            if (vueForme.getForme().contient(x, y)) {
                                getPanneauDessin().getVueFormes().remove(vueForme);
                            }
                        }
                    }
                }
            }
        } catch (RuntimeException ignored) {
            // Si la forme a déjà était modifiée, le processus d'effacement s'arrête
            // (ConcurrentModificationException par exemple)
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        forme.setPosition(getDébut());

        // La forme ne faisant pas partie des VueFormes, il faut le rajouter à part
        getPanneauDessin().setGomme(créerVueForme());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        getPanneauDessin().clearLabel();
        getPanneauDessin().setGomme(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // On annule l'évenement par défaut lors du clique (la création d'une gomme)
    }

    @Override
    protected VueForme créerVueForme() {
        return new VueEllipse((Gomme)forme, ERASER_COLOR, true);
    }

    private class Gomme extends Ellipse {
        Gomme() {
            super(
                    ERASER_DIM_X,
                    ERASER_DIM_Y
            );
        }

        @Override
        public void déplacerDe(int deltaX, int deltaY) {
            super.déplacerDe(deltaX-ERASER_DIM_X/2, deltaY-ERASER_DIM_Y/2);
        }

        @Override
        public String toString() {
            return String.format("[%s] pos : %s", this.getClass().getSimpleName(), this.getPosition());
        }
    }

    public Cursor getCursor() {
        return cursor;
    }
}
