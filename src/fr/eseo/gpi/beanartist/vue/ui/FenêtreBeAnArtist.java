package fr.eseo.gpi.beanartist.vue.ui;

import java.awt.BorderLayout;
import java.awt.Color;

/**
 * @author duhamean
 * @date 26/04/2016
 * @project gpi_binome
 */
public class FenêtreBeAnArtist extends javax.swing.JFrame {
    public final static String TITRE_PAR_DÉFAUT = "Fenêtre Be an artist";

    private Color couleurLigne;
    private PanneauDessin panneauDessin;
    private PanneauBarreOutil panneauBarreOutil;

    public FenêtreBeAnArtist(){
        this(TITRE_PAR_DÉFAUT);
    }

    public FenêtreBeAnArtist(String titre){
        this(titre, PanneauDessin.LARGEUR_PAR_DÉFAUT+PanneauBarreOutil.LARGEUR_PAR_DÉFAUT, PanneauDessin.HAUTEUR_PAR_DÉFAUT);
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
        this.associerPanneauDessin(largeur-PanneauBarreOutil.LARGEUR_PAR_DÉFAUT, hauteur, fond);
        this.associerBarreOutil();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(largeur, hauteur);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setPanneauDessin(PanneauDessin newPanneauDessin) {
        if(panneauDessin!=null)
            this.remove(panneauDessin);

        this.panneauDessin = newPanneauDessin;
        this.add(newPanneauDessin, BorderLayout.WEST);
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


    private void associerBarreOutil() {
        this.setPanneauBarreOutil(new PanneauBarreOutil(this));
    }

    public PanneauBarreOutil getPanneauBarreOutil() {
        return panneauBarreOutil;
    }

    public void setPanneauBarreOutil(PanneauBarreOutil newPanneauBarreOutil1) {
        if(panneauBarreOutil!=null)
            this.remove(panneauBarreOutil);

        this.panneauBarreOutil = newPanneauBarreOutil1;
        this.add(newPanneauBarreOutil1, BorderLayout.EAST);
    }
}
