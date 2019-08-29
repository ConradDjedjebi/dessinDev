package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilRedimensionner;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class ActionRedimensionner extends AbstractSelectionAction {

    private static final String LABEL = "Redimensionner la forme";

    private boolean actionCommand = false;

    public ActionRedimensionner(FenêtreBeAnArtist fenêtre) {
        super(LABEL, fenêtre);
    }

    public ActionRedimensionner() {
        super();
    }

    @Override
    public void updateButton(boolean emptySelection) {
        super.updateButton(emptySelection);
        updateButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionCommand = !actionCommand;
        if (actionCommand) {
            new OutilRedimensionner(fenetre.getPanneauDessin(), getOutilSélection());
            getOutilSélection().getActions().stream().filter(action -> !(action instanceof ActionRedimensionner)).forEach(action -> action.updateButton(true));
        }
        else {
            getOutilSélection().getActions().stream().forEach(action -> action.updateButton(false));
            getOutilSélection().associer(fenetre.getPanneauDessin());
        }
        updateButton();
    }

    public void updateButton() {
        getJButton().setBackground(actionCommand ? Color.GREEN : Color.LIGHT_GRAY);
    }
}
