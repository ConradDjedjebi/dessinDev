package fr.eseo.gpi.beanartist.vue.ui;

import java.awt.*;

/**
 * @author Elphege
 * @date 26/04/2016
 * @project gpi_binome
 */
public class PanneauDessin extends javax.swing.JPanel {
    final static int LARGEUR_PAR_DÉFAUT = 400;
    final static int HAUTEUR_PAR_DÉFAUT = 400;
    final static Color COULEUR_FOND_PAR_DÉFAUT = Color.BLUE;
    final static Color COULEUR_LIGNE_PAR_DÉFAUT = Color.BLACK;

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
