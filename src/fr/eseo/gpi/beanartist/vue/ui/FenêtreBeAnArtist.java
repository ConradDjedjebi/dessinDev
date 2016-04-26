package fr.eseo.gpi.beanartist.vue.ui;

import javax.swing.JFrame;
import java.awt.Color;

/**
 * @author Elphege
 * @date 26/04/2016
 * @project gpi_binome
 */
public class FenêtreBeAnArtist extends JFrame {
    private final static String TITRE_PAR_DÉFAUT = "Fenêtre Be an artist";

    private int largeur;
    private int hauteur;
    private Color couleurFond;
    private Color couleurLigne;
    private String titre;
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
        this.titre = titre;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.couleurFond = fond;
        this.couleurLigne = PanneauDessin.COULEUR_LIGNE_PAR_DÉFAUT;
        this.panneauDessin = new PanneauDessin(largeur, hauteur);
    }

    public void setPanneauDessin(PanneauDessin newPanneauDessin) {
        this.panneauDessin = newPanneauDessin;
    }

    public PanneauDessin getPanneauDessin() {
        return this.panneauDessin;
    }
}
