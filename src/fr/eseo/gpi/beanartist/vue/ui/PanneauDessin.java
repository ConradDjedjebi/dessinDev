package fr.eseo.gpi.beanartist.vue.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Elphege
 * @date 26/04/2016
 * @project gpi_binome
 */
public class PanneauDessin extends JPanel {
    final static int LARGEUR_PAR_DÉFAUT = 1;
    final static int HAUTEUR_PAR_DÉFAUT = 1;
    final static Color COULEUR_FOND_PAR_DÉFAUT = Color.BLUE;
    final static Color COULEUR_LIGNE_PAR_DÉFAUT = Color.BLACK;

    private FenêtreBeAnArtist fenêtre;

    public PanneauDessin (int largeur, int hauteur) {
        this(largeur, hauteur, COULEUR_FOND_PAR_DÉFAUT);
        }

    public PanneauDessin (int largeur, int hauteur, Color couleurFond) {
        // methode
    }

    void setFenêtre (FenêtreBeAnArtist fen) {}

}
