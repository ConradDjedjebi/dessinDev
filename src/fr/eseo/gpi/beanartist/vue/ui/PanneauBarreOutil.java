package fr.eseo.gpi.beanartist.vue.ui;

import fr.eseo.gpi.beanartist.controleur.actions.*;
import fr.eseo.gpi.beanartist.controleur.bouttons.RedimensionnageButton;
import fr.eseo.gpi.beanartist.controleur.bouttons.RemplissageButton;
import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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
        JButton choixCouleur = new JButton(new ActionChoisirCouleur(this.getFenêtre()));

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
        ActionEffacer actionEffacer = new ActionEffacer(this.getFenêtre(), outilSélection);
        JButton clear = new JButton(actionEffacer);
        actionEffacer.setJButton(clear);
        clear.setBackground(Color.RED);

        ActionCopieForme actionCopie = new ActionCopieForme(getFenêtre(), outilSélection);
        JButton copy = new JButton(actionCopie);
        actionCopie.setJButton(copy);

        ActionChoisirCouleur choisirCouleur = new ActionChoisirCouleur(this.getFenêtre());
        JButton choixCouleur = new JButton(choisirCouleur);
        choisirCouleur.setOutilSélection(outilSélection);
        choisirCouleur.setJButton(choixCouleur);

        JButton quitSelection = new JButton(new ActionSélectionner(this.getFenêtre(), false));

        this.add(clear);
        this.add(copy);
        this.add(choixCouleur);
        new RemplissageButton(this,getFenêtre(), outilSélection);
        new RedimensionnageButton(this, getFenêtre(), outilSélection);
        this.add(quitSelection);
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

        updatePanneauOutil(outilSélection);
        outilSélection.emptySelection();
    }
    public void unsetOutilSélection() {
        if(outilSélection!=null)
            this.outilSélection.libérer(true);

        this.fenêtre.getPanneauDessin().setVueFormeSélectionnée(null);
        updatePanneauOutil(null);
    }

    private void updatePanneauOutil(OutilSélection newOutilSélection) {
        this.removeAll();
        this.getFenêtre().getPanneauDessin().getLabel().setText("");
        this.outilSélection = newOutilSélection;
        if(newOutilSélection!=null) initComponentsSélection();
        else initComponents();
        this.revalidate();
        this.repaint();
    }
}
