package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * @author Elphege
 * @date 10/05/2016
 * @project gpi_binome
 */
public class ActionChoisirCouleur extends AbstractSelectionAction {
    public static final String CHOISIR_COULEUR = "Couleur";


    public ActionChoisirCouleur (FenêtreBeAnArtist fenêtreBeAnArtist) {
        super(CHOISIR_COULEUR, fenêtreBeAnArtist);
    }

    public ActionChoisirCouleur(PanneauDessin panneau){
        this(panneau.getFenêtre());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color couleurLigneChoisie = JColorChooser.showDialog(null, "Choisir couleur", Color.WHITE);

        if(couleurLigneChoisie ==null)
            return;
        if(getOutilSélection().isEmptySelection()) {
            super.fenetre.getPanneauDessin().setCouleurLigne(couleurLigneChoisie);
        }
        else {
            getOutilSélection().getVueForme().setCouleurLigne(couleurLigneChoisie);
            super.fenetre.repaint();
        }
    }
}

