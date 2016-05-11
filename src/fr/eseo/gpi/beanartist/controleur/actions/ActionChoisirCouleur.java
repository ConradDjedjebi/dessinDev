package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilCouleur;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Elphege on 10/05/2016.
 */
public class ActionChoisirCouleur extends AbstractAction {
    public static final String CHOISIR_COULEUR = "Couleur";
    private PanneauDessin panneau;
    private  JComponent panneauCouleur;
    protected Color couleurChoisie;

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

    public void setCouleurChoisie(Color couleur){this.couleurChoisie = couleur;}

    /*public void fermer() {
        fenetre.getPanneauDessin().setOutilCourant(null);
        fenetre.getPanneauDessin().repaint();
    }*/
}

