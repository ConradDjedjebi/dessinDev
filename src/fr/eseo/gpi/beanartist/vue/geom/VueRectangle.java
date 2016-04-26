package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Rectangle;

import java.awt.*;

/**
 * @author duhamean
 * @date 26/04/16
 * @project gpi_binome
 */
public class VueRectangle extends VueForme {
    public VueForme(Rectangle rectangle, boolean rempli) {
    	super(rectangle, rempli);
    }

    public VueForme(Rectangle rectangle, Color couleurLigne, boolean rempli) {
    	super(rectangle, couleurLigne, rempli);
    }
}
