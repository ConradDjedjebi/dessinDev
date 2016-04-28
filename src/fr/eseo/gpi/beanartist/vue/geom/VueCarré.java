package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Carré;

import java.awt.Color;

/**
 * @author duhamean
 * @date 28/04/16
 * @project gpi_binome
 */
public class VueCarré extends VueRectangle {
	/**
     * Créé une vue d'un Carré
     * @param carre L'objet Carré à afficher
     * @param rempli Indique si le carre doit être rempli
     */
    public VueCarré(Carré carre, boolean rempli) {
    	super(carre, rempli);
    }

    /**
     * Créé une vue d'un Carré
     * @param carre L'objet Carré à afficher
     * @param couleurLigne La couleur de la ligne
     * @param rempli Indique si le carre doit être rempli
     */
    public VueCarré(Carré carre, Color couleurLigne, boolean rempli) {
    	super(carre, couleurLigne, rempli);
    }
}
