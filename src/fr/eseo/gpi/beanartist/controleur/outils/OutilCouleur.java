package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.actions.ActionChoisirCouleur;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;
import java.awt.Dimension;


/**
 * Created by Elphege on 10/05/2016.
 */

//Retaper tout ca à la main

public class OutilCouleur extends JPanel implements ChangeListener {

    protected JColorChooser tcc;
    protected JLabel banner;
    private ActionChoisirCouleur actionCouleur;

    public OutilCouleur(ActionChoisirCouleur actionChoisirCouleur) {
        super(new BorderLayout());
        actionCouleur = actionChoisirCouleur;
        //Set up the banner at the top of the window
        banner = new JLabel("Choix d'une couleur",
                JLabel.CENTER);
        banner.setForeground(Color.yellow);
        banner.setBackground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Be An Artist"));

        //Set up color chooser for setting text color
        tcc = new JColorChooser(banner.getForeground());
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder(
                "Modes :"));

        add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }

    public void stateChanged(ChangeEvent e) {
        Color newCouleur = tcc.getColor();
        banner.setForeground(newCouleur);
        actionCouleur.setCouleurChoisie(newCouleur);
    }
}
