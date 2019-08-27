package fr.eseo.gpi.beanartist.controleur.bouttons;

import fr.eseo.gpi.beanartist.controleur.actions.ActionModeRemplissage;
import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauBarreOutil;

import javax.swing.JButton;

/**
 * @author duhamean
 * @date 12/05/16
 * @project gpi_binome
 */
public class RemplissageButton extends JButton {
    public RemplissageButton(FenêtreBeAnArtist fenêtreBeAnArtist) {
        super(new ActionModeRemplissage());
        this.setAction(new ActionModeRemplissage(fenêtreBeAnArtist.getPanneauDessin()));
        ((ActionModeRemplissage)super.getAction()).setJButton(this);
        if(fenêtreBeAnArtist.getPanneauDessin().estModeRemplissage()) {
            ((ActionModeRemplissage)super.getAction()).setRemplissageState(true);
            ((ActionModeRemplissage)super.getAction()).updateButton();
        }
    }

    public RemplissageButton(FenêtreBeAnArtist fenêtreBeAnArtist, OutilSélection outilSélection) {
        this(fenêtreBeAnArtist);
        ((ActionModeRemplissage)this.getAction()).setOutilSélection(outilSélection);
    }
}
