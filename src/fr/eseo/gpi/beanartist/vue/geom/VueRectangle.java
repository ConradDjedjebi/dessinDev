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
    public VueRectangle(Rectangle rectangle, boolean rempli) {
    	super(rectangle, rempli);
    }

    public VueRectangle(Rectangle rectangle, Color couleurLigne, boolean rempli) {
    	super(rectangle, couleurLigne, rempli);
    }

    @Override
    public void affiche(Graphics2D g2D) {
        g2D.drawRect(forme.getX(), forme.getY(), forme.getLargeur(), forme.getHauteur());
    }
}
