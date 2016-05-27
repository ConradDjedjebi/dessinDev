package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.*;
import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;

/**
 * @author Elphege
 * @date 20/05/2016
 * @project gpi_binome
 */
public class OutilGomme extends Outil {
    private VueForme vueGomme;
    private Ellipse gomme;
    public static final Color ERASER_COLOR = Color.CYAN;
    private static final int ERASER_DIM_X = 130;
    private static final int ERASER_DIM_Y = 60;
    /**
     * Initialise un déplacement
     * @param panneauDessin
     */
    public OutilGomme(PanneauDessin panneauDessin) {
        super(panneauDessin);
        gomme = new Ellipse(
                ERASER_DIM_X,
                ERASER_DIM_Y
        );
        vueGomme = new VueEllipse(gomme, true);
        vueGomme.setCouleurLigne(ERASER_COLOR);
        vueGomme.setRempli(true);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setDébut(getFin());
        super.mouseDragged(e);
        gomme.déplacerDe(getFin().getX()-getDébut().getX(), getFin().getY() - getDébut().getY());
        effacer();
        this.getPanneauDessin().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        gomme.setPosition(getDébut());
        //gomme.setX(getDébut().getX()+ERASER_DIM_X/2);
        //gomme.setY(getDébut().getY()+ERASER_DIM_Y/2);

        // La gomme ne faisant pas partie des VueFormes, il faut le rajouter à part
        getPanneauDessin().setGomme(vueGomme);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        getPanneauDessin().setGomme(null);
    }

    /**
     * La méthode qui s'occupe de supprimer les formes touchées
     * Prochaines étape : effacer les points de la forme touchée, étape par étape.
     */
    public void effacer(){
        try {
            for (int x = gomme.getMinX(), xMax = gomme.getMaxX(); x < xMax; x++) {
                for (int y = gomme.getMinY(), yMax = gomme.getMaxY(); y < yMax; y++) {
                    if (gomme.contient(x, y)) {
                        for (VueForme vueForme : getPanneauDessin().getVueFormes()) {
                            if (vueForme.getForme().contient(x, y)) {
                                getPanneauDessin().getVueFormes().remove(vueForme);
                            }
                        }
                    }
                }
            }
        } catch(ConcurrentModificationException exception) {
            //Je ne sais pas ce que c'est que cette erreur
        }
    }
}
