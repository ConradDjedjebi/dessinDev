package fr.eseo.gpi.beanartist.vue.ui;

import fr.eseo.gpi.beanartist.controleur.actions.ActionChoisirCouleur;
import fr.eseo.gpi.beanartist.controleur.actions.ActionEffacer;
import fr.eseo.gpi.beanartist.controleur.actions.ActionForme;
import fr.eseo.gpi.beanartist.controleur.actions.ActionModeRemplissage;
import fr.eseo.gpi.beanartist.controleur.actions.ActionSélectionner;
import fr.eseo.gpi.beanartist.controleur.bouttons.RemplissageButton;
import fr.eseo.gpi.beanartist.controleur.outils.OutilForme;
import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;

import javax.swing.JButton;
import java.awt.*;
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
    private OutilSélection outilSélection;

    public PanneauBarreOutil(FenêtreBeAnArtist fenêtre) {
        this.fenêtre = fenêtre;
        this.setPreferredSize(new Dimension(LARGEUR_PAR_DÉFAUT, HAUTEUR_PAR_DÉFAUT));
        this.setBackground(COULEUR_FOND);
        this.setLayout(new GridLayout(0,1));
        initComponents();
    }

    protected void initComponents() {
        JButton clearAll = new JButton(new ActionEffacer(this.getFenêtre()));
        clearAll.setBackground(Color.RED);
        JButton choixCouleur = new JButton(new ActionChoisirCouleur(this.getFenêtre().getPanneauDessin()));

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
        new RemplissageButton(this,getFenêtre());
        for (int forme : createFormes) {
            JButton jB = new JButton(new ActionForme(this.getFenêtre(), forme));
            jB.setBackground(Color.CYAN);
            this.add(jB);
        }
        this.add(outilSelection);
    }

    private void initComponentsSélection () {
        JButton clear = new JButton(new ActionEffacer(this.getFenêtre(), outilSélection));
        clear.setBackground(Color.RED);
        JButton choixCouleur = new JButton(new ActionChoisirCouleur(this.getFenêtre().getPanneauDessin()));

        JButton outilSelection = new JButton(new ActionSélectionner(this.getFenêtre(), false));

        this.add(clear);
        this.add(choixCouleur);
        new RemplissageButton(this,getFenêtre());
        this.add(outilSelection);
    }

    public FenêtreBeAnArtist getFenêtre() {
        return fenêtre;
    }

    public void setFenêtre(FenêtreBeAnArtist fenêtreBeAnArtist) {
        this.fenêtre = fenêtreBeAnArtist;
    }

    public void setOutilSélection(OutilSélection outilSélection) {
        if(this.outilSélection!=null)
            unsetOutilSélection();

        this.outilSélection = outilSélection;
        this.removeAll();
        initComponentsSélection();
        this.revalidate();
        this.repaint();
    }
    public void unsetOutilSélection() {
        this.outilSélection = null;
        this.removeAll();
        initComponents();
        this.revalidate();
        this.repaint();
        this.getFenêtre().getPanneauDessin().setOutilCourant(null);
    }
}
