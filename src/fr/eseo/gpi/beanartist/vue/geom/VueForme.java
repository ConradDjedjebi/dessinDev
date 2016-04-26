package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Forme;

import java.awt.*;

/**
 * @author duhamean
 * @date 26/04/16
 * @project gpi_binome
 */
public class VueForme {
    private static final Color COULEUR_LIGNE_PAR_DÉFAULT = Color.BLACK;

    private Color couleurLigne;
    private Forme forme;
    private boolean rempli;

    public VueForme(Forme forme, boolean rempli) {
        this(forme, COULEUR_LIGNE_PAR_DÉFAULT, rempli);
        }

    public VueForme(Forme forme, Color couleurLigne, boolean rempli) {
        this.forme = forme;
        setRempli(rempli);
        setCouleurLigne(couleurLigne);
    }

    public Color getCouleurLigne() {
        return couleurLigne;
    }

    public void setCouleurLigne(Color newCouleurLigne) {
        this.couleurLigne = newCouleurLigne;
    }

    public Forme getForme() {
        return forme;
    }

    public boolean estRempli() {
        return rempli;
    }

    public void setRempli(boolean newRempli) {
        this.rempli = newRempli;
    }

    public void affiche(Graphics2D g2D) {}
}
