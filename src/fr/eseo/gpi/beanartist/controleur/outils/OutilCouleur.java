package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.actions.ActionChoisirCouleur;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


/**
 * @author Elphege
 * @date 10/05/2016
 * @project gpi_binome
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
