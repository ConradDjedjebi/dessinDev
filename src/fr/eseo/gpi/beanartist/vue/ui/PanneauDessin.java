package fr.eseo.gpi.beanartist.vue.ui;

import java.awt.Color;
import java.awt.Dimension;

/**
 * @author Elphege
 * @date 26/04/2016
 * @project gpi_binome
 */
public class PanneauDessin extends javax.swing.JPanel {
    public final static int LARGEUR_PAR_DÉFAUT = 400;
    public final static int HAUTEUR_PAR_DÉFAUT = 400;
    public final static Color COULEUR_FOND_PAR_DÉFAUT = Color.BLUE;
    public final static Color COULEUR_LIGNE_PAR_DÉFAUT = Color.BLACK;

    private FenêtreBeAnArtist fenêtre;

    public PanneauDessin (int largeur, int hauteur) {
        this(largeur, hauteur, COULEUR_FOND_PAR_DÉFAUT);
    }

    public PanneauDessin (int largeur, int hauteur, Color couleurFond) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setBackground(couleurFond);
    }

    public void setFenêtre(FenêtreBeAnArtist newFenêtre) {
        this.fenêtre = newFenêtre;
    }
}
