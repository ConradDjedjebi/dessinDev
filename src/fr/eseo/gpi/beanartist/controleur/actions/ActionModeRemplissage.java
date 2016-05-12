package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;


/**
 * @author Elphege
 * @date 11/05/2016
 * @project gpi_binome
 */
public class ActionModeRemplissage extends AbstractAction {

    private PanneauDessin panneau;
    public static final String [] NOM_ACTION = {"Remplie", "Contours"};

    public static final boolean REMPLIE = true;
    public static final boolean CONTOURS = false;
    private boolean actionCommand;

    public ActionModeRemplissage (boolean actionCommand) {
        super(NOM_ACTION[actionCommand ? 0:1]);
        this.actionCommand = actionCommand;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        OutilForme.setRemplissage(actionCommand);
    }
}
