package fr.eseo.gpi.beanartist.vue.ui;

import fr.eseo.gpi.beanartist.controleur.outils.Outil;
import fr.eseo.gpi.beanartist.controleur.outils.OutilForme;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;

import javax.swing.JLabel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine du HAMEL
 * @date 26/04/2016
 * @project gpi_binome
 */
public class PanneauDessin extends javax.swing.JPanel {
    public final static int LARGEUR_PAR_DÉFAUT = 1000;
    public final static int HAUTEUR_PAR_DÉFAUT = 800;
    public final static Color COULEUR_FOND_PAR_DÉFAUT = Color.WHITE;
    public final static Color COULEUR_LIGNE_PAR_DÉFAUT = Color.BLACK;

    private FenêtreBeAnArtist fenêtre;
    private List<VueForme> vueFormes;
    private Outil outilCourant;
    private Color couleurLigne;
    private VueForme vueFormeSélectionnée;
    private boolean modeRemplissage = OutilForme.DEFAULT_REMPLISSAGE_MODE;
    private JLabel label;

    public PanneauDessin (int largeur, int hauteur) {
        this(largeur, hauteur, COULEUR_FOND_PAR_DÉFAUT);
    }

    public PanneauDessin (int largeur, int hauteur, Color couleurFond) {
        vueFormes = new ArrayList<>();
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setBackground(couleurFond);
        label = new JLabel("");
        this.add(label);
    }

    public void setFenêtre(FenêtreBeAnArtist newFenêtre) {
        this.fenêtre = newFenêtre;
    }

    public List<VueForme> getVueFormes() {
        return vueFormes;
    }

    public void ajouterVueForme(VueForme vueForme) {
        vueFormes.add(vueForme);
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

    public Color getCouleurLigne() {
        return couleurLigne;
    }

    public void setCouleurLigne(Color newCouleurLigne) {
        this.couleurLigne = newCouleurLigne;
    }

    public void setVueFormeSélectionnée(VueForme vueFormeChoisie){
        this.vueFormeSélectionnée = vueFormeChoisie;
    }

    public VueForme getVueFormeSélectionnée(){
        return this.vueFormeSélectionnée;
    }

    public boolean estModeRemplissage() {
        return modeRemplissage;
    }

    public void setModeRemplissage(boolean modeRemplissage) {
        this.modeRemplissage = modeRemplissage;
    }

    public JLabel getLabel() {
        return label;
    }

    public void clearLabel() {
        label.setText(new String());
    }
}
