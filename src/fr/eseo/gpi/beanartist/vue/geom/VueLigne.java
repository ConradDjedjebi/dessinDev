package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Ligne;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author duhamean
 * @date 28/04/16
 * @project gpi_binome
 */
public class VueLigne extends VueForme {

	/**
     * Créé une vue d'un Ligne
     * @param ligne L'objet Ligne à afficher
     */
    public VueLigne(Ligne ligne) {
    	super(ligne, false);
    }

    /**
     * Créé une vue d'un Ligne
     * @param ligne L'objet Ligne à afficher
     * @param couleurLigne La couleur de la ligne
     */
    public VueLigne(Ligne ligne, Color couleurLigne) {
        super(ligne, couleurLigne, false);
    }

    @Override
    public void affiche(Graphics2D g2D) {
        Ligne ligne = (Ligne) this.forme;

        g2D.setColor(this.getCouleurLigne());

        g2D.drawLine(ligne.getP1().getX(), ligne.getP1().getY(), ligne.getP2().getX(), ligne.getP2().getY());
    }
}
