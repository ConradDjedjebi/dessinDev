package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilDéplacer;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class ActionDéplacer extends AbstractSelectionAction {

    private static final String LABEL = "Déplacer la forme";

    public ActionDéplacer(FenêtreBeAnArtist fenêtre) {
        super(LABEL, fenêtre);
    }

    public ActionDéplacer() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new OutilDéplacer(fenetre.getPanneauDessin(), getOutilSélection());
        getJButton().setBackground(Color.DARK_GRAY);
    }

}
