package fr.eseo.gpi.beanartist.controleur.bouttons;

import fr.eseo.gpi.beanartist.controleur.actions.ActionModeRemplissage;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauBarreOutil;

import javax.swing.*;

/**
 * @author duhamean
 * @date 12/05/16
 * @project gpi_binome
 */
public class RemplissageButton extends JButton {
    public RemplissageButton(PanneauBarreOutil panneauBarreOutil, FenêtreBeAnArtist fenêtreBeAnArtist) {
        super(new ActionModeRemplissage());
        this.setAction(new ActionModeRemplissage(fenêtreBeAnArtist.getPanneauDessin()));
        ((ActionModeRemplissage)super.getAction()).setJButton(this);
        panneauBarreOutil.add(this);
    }
}
