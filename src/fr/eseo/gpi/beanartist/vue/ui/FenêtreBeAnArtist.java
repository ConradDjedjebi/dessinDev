package fr.eseo.gpi.beanartist.vue.ui;

/**
 * Created by Elphege on 26/04/2016.
 */
public class FenêtreBeAnArtist {
    final static String TITRE_PAR_DÉFAUT = "Fenêtre_Par_DÉFAUT";

    private int largeur;
    private int hauteur;
    private String Couleur_Fond;
    private String Couleur_Ligne;
    private String titre;

    public FenêtreBeAnArtist(){
        titre = TITRE_PAR_DÉFAUT.
        largeur = PanneauDessin.LARGEUR_PAR_DÉFAUT;
        hauteur = PanneauDessin.HAUTEUR_PAR_DÉFAUT;
        Couleur_Fond = PanneauDessin.COULEUR_FOND_PAR_DÉFAUT;
        Couleur_Ligne = PanneauDessin.COULEUR_LIGNE_PAR_DÉFAUT;
    }
}
