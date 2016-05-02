package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Ellipse;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Created by Elphege on 01/05/2016.
 */
public class VueEllipse extends VueForme {

    /**
     * Créé une vue d'une ellipse
     * @param ellipse L'objet Ellipse à afficher
     * @param rempli Indique si l'ellipse doit être rempli
     */
    public VueEllipse(Ellipse ellipse, boolean rempli) {
        super(ellipse, rempli);
    }

    /**
     * Créé une vue d'une ellipse
     * @param ellipse L'objet Ellipse à afficher
     * @param couleurLigne La couleur de la ligne
     * @param rempli Indique si le rectangle doit être rempli
     */
    public VueEllipse(Ellipse ellipse, Color couleurLigne, boolean rempli) {
        super(ellipse, couleurLigne, rempli);
    }

    @Override
    public void affiche(Graphics2D g2D) {
        g2D.setColor(this.getCouleurLigne());

        if (estRempli())
            g2D.fillRect(forme.getX(), forme.getY(), forme.getLargeur(), forme.getHauteur());
        else
            g2D.drawRect(forme.getX(), forme.getY(), forme.getLargeur(), forme.getHauteur());
    }
}
