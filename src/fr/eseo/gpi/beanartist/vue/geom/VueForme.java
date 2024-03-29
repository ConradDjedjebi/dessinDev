package fr.eseo.gpi.beanartist.vue.geom;

import fr.eseo.gpi.beanartist.modele.geom.Forme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author duhamean
 * @date 26/04/16
 * @project gpi_binome
 */
abstract public class VueForme implements Cloneable {
    private static final Color COULEUR_LIGNE_PAR_DÉFAULT = Color.BLACK;

    private Color couleurLigne;
    protected Forme forme;
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

    public void affiche(Graphics2D g2D) {
        g2D.setColor(new Color(getCouleurLigne().getRGB()));
    }

    public VueForme clone () {
        try {
            VueForme vueForme = (VueForme) super.clone();
            vueForme.forme =  vueForme.getForme().clone();
            vueForme.couleurLigne = new Color(this.couleurLigne.getRGB());
            return vueForme;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
