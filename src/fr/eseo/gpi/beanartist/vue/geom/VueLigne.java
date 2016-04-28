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

    protected Ligne forme;

	/**
     * Créé une vue d'un Ligne
     * @param ligne L'objet Ligne à afficher
     */
    public VueLigne(Ligne ligne) {
    	super(ligne, false);
        this.forme = ligne;
    }

    /**
     * Créé une vue d'un Ligne
     * @param ligne L'objet Ligne à afficher
     * @param couleurLigne La couleur de la ligne
     */
    public VueLigne(Ligne ligne, Color couleurLigne) {
        super(ligne, couleurLigne, false);
        this.forme = ligne;
    }

    @Override
    public void affiche(Graphics2D g2D) {
        g2D.setColor(this.getCouleurLigne());

        g2D.drawLine(this.forme.getP1().getX(), this.forme.getP1().getY(), this.forme.getP2().getX(), this.forme.getP2().getY());
    }
}
