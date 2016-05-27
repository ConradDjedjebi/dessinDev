package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.*;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;

/**
 * @author Elphege
 * @date 20/05/2016
 * @project gpi_binome
 */
public class OutilGomme extends OutilForme {
    private VueForme vueGomme;
    public static final Color ERASER_COLOR = new Color(255, 153, 8);
    private static final int ERASER_DIM_X = 130;
    private static final int ERASER_DIM_Y = 60;
    /**
     * Initialise un déplacement
     * @param panneauDessin
     */
    public OutilGomme(PanneauDessin panneauDessin) {
        super(panneauDessin);
        forme = new Ellipse(
                ERASER_DIM_X,
                ERASER_DIM_Y
        );
        vueGomme = créerVueForme();
    }

    @Override
    protected void updateForme() {
        forme.déplacerDe(getFin().getX()-getDébut().getX(), getFin().getY() - getDébut().getY());
        effacer();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        forme.setPosition(getDébut());
        forme.déplacerDe(ERASER_DIM_X/2, ERASER_DIM_Y/2);

        // La forme ne faisant pas partie des VueFormes, il faut le rajouter à part
        getPanneauDessin().setGomme(vueGomme);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        getPanneauDessin().setGomme(null);
    }

    @Override
    protected VueForme créerVueForme() {
        VueEllipse vueGomme;
        vueGomme = new VueEllipse((Ellipse)forme, true);
        vueGomme.setCouleurLigne(ERASER_COLOR);
        return vueGomme;
    }

    /**
     * La méthode qui s'occupe de supprimer les formes touchées
     * Prochaines étape : effacer les points de la forme touchée, étape par étape.
     */
    public void effacer(){
        try {
            for (int x = forme.getMinX(), xMax = forme.getMaxX(); x < xMax; x++) {
                for (int y = forme.getMinY(), yMax = forme.getMaxY(); y < yMax; y++) {
                    if (forme.contient(x, y)) {
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
