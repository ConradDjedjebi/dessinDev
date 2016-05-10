package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Elphege on 10/05/2016.
 */
public class ActionChoisirCouleur extends AbstractAction {
    public static final String NOM_ACTION = "Couleur";
    private FenêtreBeAnArtist fenetre;

    public ActionChoisirCouleur (FenêtreBeAnArtist fenetre) {
        super(NOM_ACTION);
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Met à jour la couleur

        //fenetre.getPanneauDessin().getVueFormes().clear();
        //fenetre.get
    }
}
