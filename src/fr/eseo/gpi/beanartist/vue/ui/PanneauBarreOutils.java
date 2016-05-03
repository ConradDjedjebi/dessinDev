package fr.eseo.gpi.beanartist.vue.ui;

import java.awt.Color;
import java.awt.Dimension;

/**
 * @author duhamean
 * @date 03/05/16
 * @project gpi_binome
 */
public class PanneauBarreOutils extends javax.swing.JPanel {
    private static final int LARGEUR_PAR_DÉFAUT = 25;
    private static final int HAUTEUR_PAR_DÉFAUT = 100;
    private static final Color COULEUR_FOND = Color.GRAY;

    private FenêtreBeAnArtist fenêtreBeAnArtist;

    public PanneauBarreOutils () {
        this.setPreferredSize(new Dimension(LARGEUR_PAR_DÉFAUT, HAUTEUR_PAR_DÉFAUT));
        this.setBackground(COULEUR_FOND);
        this.initComponents();
    }

    public PanneauBarreOutils (int largeur, int hauteur, Color couleurFond) {

        this.setBackground(couleurFond);
    }

    public void initComponents() {

    }

    public FenêtreBeAnArtist getFenêtreBeAnArtist() {
        return fenêtreBeAnArtist;
    }
}
