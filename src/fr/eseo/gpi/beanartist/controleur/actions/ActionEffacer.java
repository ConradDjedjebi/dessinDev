package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
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
    private OutilSélection outilSélection;

    public ActionEffacer (FenêtreBeAnArtist fenetre) {
        super(NOM_ACTION);
        this.fenetre = fenetre;
    }

    public ActionEffacer (FenêtreBeAnArtist fenetre, OutilSélection outilSélection) {
        super("Effacer la sélection");
        this.fenetre = fenetre;
        this.outilSélection = outilSélection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (outilSélection == null)
                fenetre.getPanneauDessin().getVueFormes().clear();
            else
                fenetre.getPanneauDessin().getVueFormes().remove(outilSélection.getVueForme());
        } catch (NullPointerException exception) {
            // Aucune forme selectionnée
        }
        fenetre.getPanneauDessin().repaint();
    }
}
