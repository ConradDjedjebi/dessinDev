package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

/**
 * @author duhamean
 * @date 03/05/16
 * @project gpi_binome
 */
public class ActionForme extends AbstractAction  {
    public static final String NOM_ACTION = "Créer une forme";

    public static final String NOM_ACTION_RECTANGLE = "Créer un rectangle";
    public static final String NOM_ACTION_ELLIPSE = "Créer une ellipse";
    public static final String NOM_ACTION_CARRÉ = "Créer un carré";
    public static final String NOM_ACTION_CERCLE = "Créer un cercle";
    public static final String NOM_ACTION_LIGNE = "Créer une ligne";
    public static final String NOM_ACTION_TRACÉ = "Créer un tracé";

    private FenêtreBeAnArtist fenetre;

    public ActionForme (FenêtreBeAnArtist fenetre) {
        super(NOM_ACTION);
        //setActionForme(this);
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fenetre.getPanneauDessin().getVueFormes().clear();
        fenetre.getPanneauDessin().repaint();
    }
}
