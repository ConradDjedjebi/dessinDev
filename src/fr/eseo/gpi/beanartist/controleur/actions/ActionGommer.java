package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilGomme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Elphege
 * @date 20/05/16
 * @project gpi_binome
 */
public class ActionGommer extends AbstractAction  {
    private PanneauDessin panneauDessin;
    protected static final String NOM_ACTION = "Gommer";

    public ActionGommer(FenêtreBeAnArtist newFenêtre) {
        super(NOM_ACTION);
        panneauDessin = newFenêtre.getPanneauDessin();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new OutilGomme(panneauDessin);
    }
}
