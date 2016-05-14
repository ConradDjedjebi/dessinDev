package fr.eseo.gpi.beanartist.controleur.bouttons;

import fr.eseo.gpi.beanartist.controleur.actions.ActionDéplacer;
import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauBarreOutil;

import javax.swing.JButton;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class DéplacerButton extends JButton {
    public DéplacerButton(PanneauBarreOutil panneauBarreOutil, FenêtreBeAnArtist fenêtre, OutilSélection outilSélection) {
        super(new ActionDéplacer());
        this.setAction(new ActionDéplacer(fenêtre));
        ((ActionDéplacer)super.getAction()).setJButton(this);
        ((ActionDéplacer)this.getAction()).setOutilSélection(outilSélection);
        panneauBarreOutil.add(this);
    }
}
