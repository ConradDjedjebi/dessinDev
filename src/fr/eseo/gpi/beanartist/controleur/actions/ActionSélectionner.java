package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

    import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;


/**
 * @author Elphege
 * @date 09/05/2016
 * @project gpi_binome
 */
public class ActionSélectionner extends AbstractAction {
    public static final String NOM_ACTION = "Sélectionner";
    private FenêtreBeAnArtist fenetre;

    public ActionSélectionner (FenêtreBeAnArtist fenetre) {
        super(NOM_ACTION);
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new OutilSélection(this.fenetre.getPanneauDessin());
    }

}
