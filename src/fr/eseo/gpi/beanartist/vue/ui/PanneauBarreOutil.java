package fr.eseo.gpi.beanartist.vue.ui;

import fr.eseo.gpi.beanartist.controleur.actions.*;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
/**
 * @author duhamean
 * @date 03/05/16
 * @project gpi_binome
 */
public class PanneauBarreOutil extends javax.swing.JPanel {
    protected static final int LARGEUR_PAR_DÉFAUT = 255;
    protected static final int HAUTEUR_PAR_DÉFAUT = PanneauDessin.HAUTEUR_PAR_DÉFAUT;
    protected static final Color COULEUR_FOND = Color.GRAY;

    private FenêtreBeAnArtist fenêtre;

    public PanneauBarreOutil(FenêtreBeAnArtist fenêtre) {
        this.fenêtre = fenêtre;
        this.setPreferredSize(new Dimension(LARGEUR_PAR_DÉFAUT, HAUTEUR_PAR_DÉFAUT));
        this.setBackground(COULEUR_FOND);
        this.initComponents();
    }

    private void initComponents() {
        ButtonGroup bouttonGroupe = new ButtonGroup();

        JRadioButton rempli, contour;
        rempli = new JRadioButton(new ActionModeRemplissage(this.getFenêtre().getPanneauDessin(), ActionModeRemplissage.NOM_ACTION_REMPLI));
        contour = new JRadioButton(new ActionModeRemplissage(this.getFenêtre().getPanneauDessin(), ActionModeRemplissage.NOM_ACTION_CONTOUR));
        bouttonGroupe.add(rempli);
        bouttonGroupe.add(contour);


        JButton clearAll = new JButton(new ActionEffacer(this.getFenêtre()));
        JButton choixCouleur = new JButton(new ActionChoisirCouleur(this.getFenêtre().getPanneauDessin()));
        JButton [] createFormes = {
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.RECTANGLE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.CARRÉ)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.ELLIPSE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.CERCLE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.LIGNE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.TRACÉ)),
                new JButton(new ActionSélectionner(this.getFenêtre()))
        };

        this.add(clearAll);
        this.add(choixCouleur);
        this.add(rempli);
        this.add(contour);
        for (JButton jB :
                createFormes) {
            this.add(jB);
        }
    }

    public FenêtreBeAnArtist getFenêtre() {
        return fenêtre;
    }

    public void setFenêtre(FenêtreBeAnArtist fenêtreBeAnArtist) {
        this.fenêtre = fenêtreBeAnArtist;
    }
}
