package fr.eseo.gpi.beanartist.controleur.bouttons;

import fr.eseo.gpi.beanartist.controleur.actions.ActionRedimensionner;
import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauBarreOutil;

import javax.swing.JButton;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class RedimensionnageButton extends JButton {
    public RedimensionnageButton(FenêtreBeAnArtist fenêtre, OutilSélection outilSélection) {
        super(new ActionRedimensionner());
        this.setAction(new ActionRedimensionner(fenêtre));
        ((ActionRedimensionner)super.getAction()).setJButton(this);
        ((ActionRedimensionner)this.getAction()).setOutilSélection(outilSélection);
    }
}
