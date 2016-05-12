package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilCouleur;
import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * @author Elphege
 * @date 10/05/2016
 * @project gpi_binome
 */
public class ActionChoisirCouleur extends AbstractAction {
    public static final String CHOISIR_COULEUR = "Couleur";
    private PanneauDessin panneau;
    private  JComponent panneauCouleur;
    protected Color couleurChoisie;

    private OutilSélection outilSélection;
    private JButton jButton;


    public ActionChoisirCouleur (PanneauDessin panneauDessin) {
        super(CHOISIR_COULEUR);
        this.panneau = panneauDessin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Create and set up the window.
        JFrame frame = new JFrame("Panneau Couleurs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        panneauCouleur = new OutilCouleur(this);
        panneauCouleur.setOpaque(true); //content panes must be opaque
        frame.setContentPane(panneauCouleur);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        panneau.getVueFormeSélectionnée().setCouleurLigne(couleurChoisie);
        panneau.getVueFormeSélectionnée().setCouleurRemplissage(couleurChoisie);
        panneau.repaint();
    }

    public void setCouleurChoisie(Color couleur){
        this.couleurChoisie = couleur;
    }


    public JButton getJButton() {
        return jButton;
    }

    public void setJButton(JButton button) {
        this.jButton = button;
    }

    public void setOutilSélection(OutilSélection sélection) {
        this.outilSélection = sélection;
    }

    /*public void fermer() {
        fenetre.getPanneauDessin().setOutilCourant(null);
        fenetre.getPanneauDessin().repaint();
    }*/
}

