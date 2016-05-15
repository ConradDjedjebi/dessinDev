package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Rectangle;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author duhamean
 * @date 26/04/16
 * @project gpi_binome
 */
public class VueRectangle extends VueForme {
    /**
     * Créé une vue d'un Rectangle
     * @param rectangle L'objet Rectangle à afficher
     * @param rempli Indique si le rectangle doit être remplissage
     */
    public VueRectangle(Rectangle rectangle, boolean rempli) {
    	super(rectangle, rempli);
    }

    /**
     * Créé une vue d'un Rectangle
     * @param rectangle L'objet Rectangle à afficher
     * @param couleurLigne La couleur de la ligne
     * @param rempli Indique si le rectangle doit être remplissage
     */
    public VueRectangle(Rectangle rectangle, Color couleurLigne, boolean rempli) {
    	super(rectangle, couleurLigne, rempli);
    }

    @Override
    public void affiche(Graphics2D g2D) {
        g2D.setColor(this.getCouleurLigne());
        if (estRempli())
                g2D.fillRect(forme.getMinX(), forme.getMinY(), forme.getLargeur(), forme.getHauteur());
        else
            g2D.drawRect(forme.getMinX(), forme.getMinY(), forme.getLargeur(), forme.getHauteur());
    }
}
