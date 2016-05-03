package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

/**
 * @author duhamean
 * @date 02/05/16
 * @project gpi_binome
 */
public class ActionEffacer extends AbstractAction {
    public static final String NOM_ACTION = "Effacer tout";
    private FenêtreBeAnArtist fenetre;

    public ActionEffacer (FenêtreBeAnArtist fenetre) {
        super(NOM_ACTION);
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fenetre.getPanneauDessin().getVueFormes().clear();
        fenetre.getPanneauDessin().repaint();
    }
}
