package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Cercle;

import java.awt.Color;

/**
 * @author Elphege
 * @date 01/05/2016
 * @project gpi_binome
 */
public class VueCercle extends VueEllipse {
    /**
     * Créé une vue d'un Cercle
     * @param cercle L'objet Cercle à afficher
     * @param rempli Indique si le cercle doit être remplissage
     */
    public VueCercle(Cercle cercle, boolean rempli) {
        super(cercle, rempli);
    }

    /**
     * Créé une vue d'un Cercle
     * @param cercle L'objet Cercle à afficher
     * @param couleurLigne La couleur de la ligne
     * @param rempli Indique si le cercle doit être remplissage
     */
    public VueCercle(Cercle cercle, Color couleurLigne, boolean rempli) {
        super(cercle, couleurLigne, rempli);
    }
}
