package fr.eseo.gpi.beanartist.vue.ui;

import java.awt.Color;

/**
 * @author Elphege
 * @date 26/04/2016
 * @project gpi_binome
 */
public class FenêtreBeAnArtist extends javax.swing.JFrame {
    public final static String TITRE_PAR_DÉFAUT = "Fenêtre Be an artist";

    private Color couleurLigne;
    private PanneauDessin panneauDessin;

    public FenêtreBeAnArtist(){
        this(TITRE_PAR_DÉFAUT);
    }

    public FenêtreBeAnArtist(String titre){
        this(titre, PanneauDessin.LARGEUR_PAR_DÉFAUT, PanneauDessin.HAUTEUR_PAR_DÉFAUT);
    }

    public FenêtreBeAnArtist(int largeur, int hauteur){
        this(TITRE_PAR_DÉFAUT, largeur, hauteur);
    }

    public FenêtreBeAnArtist(String titre, int largeur, int hauteur){
        this(titre, largeur, hauteur, PanneauDessin.COULEUR_FOND_PAR_DÉFAUT);
    }

    public FenêtreBeAnArtist(String titre, int largeur, int hauteur, Color fond){
        super(titre);
        this.couleurLigne = PanneauDessin.COULEUR_LIGNE_PAR_DÉFAUT;
        this.associerPanneauDessin(largeur, hauteur, fond);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(largeur, hauteur);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setPanneauDessin(PanneauDessin newPanneauDessin) {
        if(panneauDessin!=null)
            this.remove(panneauDessin);

        this.panneauDessin = newPanneauDessin;
        this.add(newPanneauDessin);
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

    private void associerPanneauDessin (int largeur, int hauteur) {
        this.associerPanneauDessin(largeur, hauteur, PanneauDessin.COULEUR_FOND_PAR_DÉFAUT);
    }
}
