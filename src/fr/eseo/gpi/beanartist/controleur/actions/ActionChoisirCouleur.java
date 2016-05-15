package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * @author Elphege
 * @date 10/05/2016
 * @project gpi_binome
 */
public class ActionChoisirCouleur extends AbstractSelectionAction {
    public static final String CHOISIR_COULEUR = "Couleur";
    private PanneauDessin panneau;

    public Color couleurLigneChoisie;


    public ActionChoisirCouleur (FenêtreBeAnArtist fenêtreBeAnArtist) {
        super(CHOISIR_COULEUR, fenêtreBeAnArtist);
        this.panneau = fenêtreBeAnArtist.getPanneauDessin();
    }

    public ActionChoisirCouleur(PanneauDessin panneau){
        this(panneau.getFenêtre());
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        VueForme formeSélectionnée =  panneau.getVueFormeSélectionnée();
        Color ancienneCouleur = formeSélectionnée.getCouleurLigne();
        couleurLigneChoisie = JColorChooser.showDialog(null, "Choisir couleur", ancienneCouleur);
        if(couleurLigneChoisie==null){
            couleurLigneChoisie = ancienneCouleur;
        }
        formeSélectionnée.setCouleurLigne(couleurLigneChoisie);
        panneau.repaint();
    }
}

