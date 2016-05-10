package fr.eseo.gpi.beanartist.vue.ui;

import fr.eseo.gpi.beanartist.controleur.outils.Outil;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine du HAMEL
 * @date 26/04/2016
 * @project gpi_binome
 */
public class PanneauDessin extends javax.swing.JPanel {
    public final static int LARGEUR_PAR_DÉFAUT = 400;
    public final static int HAUTEUR_PAR_DÉFAUT = 400;
    public final static Color COULEUR_FOND_PAR_DÉFAUT = Color.GRAY;
    public final static Color COULEUR_LIGNE_PAR_DÉFAUT = Color.BLACK;

    private FenêtreBeAnArtist fenêtre;
    private List<VueForme> vueFormes;
    private Outil outilCourant;
    private Color couleurLigne;
    private boolean modeRemplissage;

    public PanneauDessin (int largeur, int hauteur) {
        this(largeur, hauteur, COULEUR_FOND_PAR_DÉFAUT);
    }

    public PanneauDessin (int largeur, int hauteur, Color couleurFond) {
        vueFormes = new ArrayList<VueForme>();
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setBackground(couleurFond);
    }

    public void setFenêtre(FenêtreBeAnArtist newFenêtre) {
        this.fenêtre = newFenêtre;
    }

    public List<VueForme> getVueFormes() {
        return vueFormes;
    }

    public void ajouterVueForme(VueForme vueForme) {
        vueFormes.add(vueForme);
        this.repaint();
        //Nécessaire de faire un repaint après ajout d'une forme?
    }

    @Override
    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2D = (Graphics2D)graphics.create();
        for (VueForme vueForme: vueFormes) {
            vueForme.affiche(g2D);
        }
        g2D.dispose();
    }

    public Outil getOutilCourant() {
        return outilCourant;
    }

    public void setOutilCourant(Outil newOutilCourant) {
        this.outilCourant = newOutilCourant;
    }

    public boolean estModeRemplissage() {
        return modeRemplissage;
    }

    public void setModeRemplissage(boolean newModeRemplissage) {
        this.modeRemplissage = newModeRemplissage;
    }

    public Color getCouleurLigne() {
        return couleurLigne;
    }

    public void setCouleurLigne(Color newCouleurLigne) {
        this.couleurLigne = newCouleurLigne;
    }
}
