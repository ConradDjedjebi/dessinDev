package fr.eseo.gpi.beanartist.vue.ui;

import java.awt.Color;

/**
 * @author Elphege
 * @date 26/04/2016
 * @project gpi_binome
 */
public class FenêtreBeAnArtist extends javax.swing.JFrame {
    private final static String TITRE_PAR_DÉFAUT = "Fenêtre Be an artist";

    private Color couleurLigne;
    private PanneauDessin panneauDessin;

    public FenêtreBeAnArtist(){
        this(TITRE_PAR_DÉFAUT, PanneauDessin.LARGEUR_PAR_DÉFAUT, PanneauDessin.HAUTEUR_PAR_DÉFAUT, PanneauDessin.COULEUR_FOND_PAR_DÉFAUT);
    }

    public FenêtreBeAnArtist(String titre){
        this(titre, PanneauDessin.LARGEUR_PAR_DÉFAUT, PanneauDessin.HAUTEUR_PAR_DÉFAUT, PanneauDessin.COULEUR_FOND_PAR_DÉFAUT);
    }

    public FenêtreBeAnArtist(int largeur, int hauteur){
        this(largeur, hauteur, PanneauDessin.COULEUR_FOND_PAR_DÉFAUT);
    }

    public FenêtreBeAnArtist(int largeur, int hauteur, Color couleurFond){
        this(TITRE_PAR_DÉFAUT, largeur, hauteur, couleurFond);
    }

    public FenêtreBeAnArtist(String titre, int largeur, int hauteur, Color fond){
        this.setTitle(titre);
        this.couleurLigne = PanneauDessin.COULEUR_LIGNE_PAR_DÉFAUT;
        this.associerPanneauDessin(largeur, hauteur, fond);
    }

    public void setPanneauDessin(PanneauDessin newPanneauDessin) {
        this.panneauDessin = newPanneauDessin;
    }

    public PanneauDessin getPanneauDessin() {
        return this.panneauDessin;
    }

    private void associerPanneauDessin (PanneauDessin panneau) {
        panneau.setFenêtre(this);
        this.setPanneauDessin(panneau);
    }

    private void associerPanneauDessin (int largeur, int hauteur, Color fond) {
        this.associerPanneauDessin(new PanneauDessin(largeur, hauteur, fond));
    }
}
