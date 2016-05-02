package fr.eseo.gpi.beanartist.vue.geom;

/**
 * Created by Elphege on 01/05/2016.
 */

import fr.eseo.gpi.beanartist.modele.geom.Ligne;
import fr.eseo.gpi.beanartist.modele.geom.Tracé;

import java.awt.Color;
import java.awt.Graphics2D;

public class VueTracé extends VueForme {

    protected Tracé tracé;

    /**
     * Créé une vue d'un Tracé
     * @param tracé L'objet Tracé à afficher
     */
    public VueTracé(Tracé tracé) {
        super(tracé, false);
        this.forme = tracé;
    }

    /**
     * Créé une vue d'un Tracé
     * @param tracé L'objet Tracé à afficher
     * @param couleurLigne La couleur du Tracé
     */
    public VueTracé(Tracé tracé, Color couleurLigne) {
        super(tracé, couleurLigne, false);
        this.forme = tracé;
    }

    @Override
    public void affiche(Graphics2D g2D) {
        g2D.setColor(this.getCouleurLigne());
        for (Ligne ligne : tracé.getLignes()) {
            g2D.drawLine(ligne.getP1().getX(), ligne.getP1().getY(), ligne.getP2().getX(), ligne.getP2().getY());
        }
    }

}
