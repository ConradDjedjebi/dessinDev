package fr.eseo.gpi.beanartist.vue.ui;

import fr.eseo.gpi.beanartist.controleur.actions.ActionChoisirCouleur;
import fr.eseo.gpi.beanartist.controleur.actions.ActionEffacer;
import fr.eseo.gpi.beanartist.controleur.actions.ActionForme;
import fr.eseo.gpi.beanartist.controleur.actions.ActionModeRemplissage;
import fr.eseo.gpi.beanartist.controleur.actions.ActionSélectionner;
import fr.eseo.gpi.beanartist.controleur.outils.OutilForme;

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

        JButton clearAll = new JButton(new ActionEffacer(this.getFenêtre()));
        JButton choixCouleur = new JButton(new ActionChoisirCouleur(this.getFenêtre().getPanneauDessin()));

        ButtonGroup bouttonGroupe = new ButtonGroup();
        boolean [] remplissageModes = {ActionModeRemplissage.REMPLIE, ActionModeRemplissage.CONTOURS};

        int [] createFormes = {
                ActionForme.RECTANGLE,
                ActionForme.CARRÉ,
                ActionForme.ELLIPSE,
                ActionForme.CERCLE,
                ActionForme.LIGNE,
                ActionForme.TRACÉ,
        };
        JButton outilSelection = new JButton(new ActionSélectionner(this.getFenêtre()));

        this.add(clearAll);
        this.add(choixCouleur);
        for (boolean remplissageMode : remplissageModes) {
            JRadioButton jRadioButton = new JRadioButton(new ActionModeRemplissage(fenêtre.getPanneauDessin(), remplissageMode));
            jRadioButton.setSelected(remplissageMode==OutilForme.DEFAULT_REMPLISSAGE_MODE);
            bouttonGroupe.add(jRadioButton);
            this.add(jRadioButton);
        }
        for (int forme : createFormes) {
            this.add(new JButton(new ActionForme(this.getFenêtre(), forme)));
        }
        this.add(outilSelection);
    }

    public FenêtreBeAnArtist getFenêtre() {
        return fenêtre;
    }

    public void setFenêtre(FenêtreBeAnArtist fenêtreBeAnArtist) {
        this.fenêtre = fenêtreBeAnArtist;
    }
}
