package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Ellipse;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Elphege
 * @date 01/05/2016
 * @project gpi_binome
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
    //Attention : super(ellipse, rempli) crée une Forme remplie et non une Ellipse remplie

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
        //Vérifier si pour la méthode fillOval(), x et y sont les cooedonnées du point supérieur gauche ou du centre
        if (estRempli())
            g2D.fillOval(forme.getX(), forme.getY(), forme.getLargeur(), forme.getHauteur());
        else
            g2D.drawOval(forme.getX(), forme.getY(), forme.getLargeur(), forme.getHauteur());
    }
}
