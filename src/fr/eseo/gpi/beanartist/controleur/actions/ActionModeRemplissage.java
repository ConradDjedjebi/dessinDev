package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * Created by Elphege on 11/05/2016.
 */
public class ActionModeRemplissage extends AbstractAction {

    private PanneauDessin panneau;
    public static final String NOM_ACTION_CONTOUR = "Contours";
    public static final String NOM_ACTION_REMPLI = "Rempli";
    private String actionCommand;

    public ActionModeRemplissage (PanneauDessin panneauDessin) {
        this(panneauDessin, ActionModeRemplissage.NOM_ACTION_CONTOUR);
    }

    public ActionModeRemplissage (PanneauDessin panneauDessin, String actionCommand) {
        super(actionCommand);
        this.panneau = panneauDessin;
        this.actionCommand = actionCommand;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (this.actionCommand)
        {
            case NOM_ACTION_REMPLI:
                panneau.setModeRemplissage(true);
                break;

            case NOM_ACTION_CONTOUR:
                panneau.setModeRemplissage(false);
                break;

            default:
        }
    }
}
