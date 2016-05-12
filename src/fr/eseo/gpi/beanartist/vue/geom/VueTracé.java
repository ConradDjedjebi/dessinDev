package fr.eseo.gpi.beanartist.vue.geom;

/**
 * @author Elphege
 * @date 01/05/2016
 * @project gpi_binome
 */

import fr.eseo.gpi.beanartist.modele.geom.Ligne;
import fr.eseo.gpi.beanartist.modele.geom.Tracé;

import java.awt.Color;
import java.awt.Graphics2D;

public class VueTracé extends VueForme {

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
        Tracé tracé = (Tracé)this.forme;
        g2D.setColor(this.getCouleurLigne());
        try {
            for (Ligne ligne : tracé.getLignes()) {
                g2D.drawLine(ligne.getP1().getX(), ligne.getP1().getY(), ligne.getP2().getX(), ligne.getP2().getY());
            }
        } catch (NullPointerException e) {
            // Si il n'y a aucune ligne, on affiche rien
        }
    }

}
