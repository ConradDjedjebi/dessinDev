package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;


/**
 * @author Elphege
 * @date 11/05/2016
 * @project gpi_binome
 */
public class ActionModeRemplissage extends AbstractSelectionAction {

    public static final String NOM_ACTION_REMPLI   = "Forme remplie";
    public static final String NOM_ACTION_CONTOURS = "Contours uniquement";

    public static final boolean REMPLIE = true;
    public static final boolean CONTOURS = false;

    private boolean actionCommand;
    private PanneauDessin panneau;

    public ActionModeRemplissage () {}

    public ActionModeRemplissage (PanneauDessin panneauDessin) {
        this(panneauDessin, OutilForme.DEFAULT_REMPLISSAGE_MODE);
    }

    @Override
    public void updateButton(boolean emptySelection) {
       super.updateButton(emptySelection);
        if(!emptySelection)
            this.setRemplissageState(this.getOutilSélection().getVueForme().estRempli());
        this.updateButton();
    }

    public ActionModeRemplissage (PanneauDessin panneauDessin, boolean actionC) {
        super();
        this.actionCommand = actionC;
        this.panneau = panneauDessin;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        actionCommand = !actionCommand;
        updateButton();
        try {
            panneau.getVueFormeSélectionnée().setRempli(actionCommand);
            panneau.repaint();
        } catch (NullPointerException e) {
            panneau.setModeRemplissage(actionCommand);
        }
    }

    @Override
    public void setJButton(JButton button) {
        super.setJButton(button);
        updateButton();
    }

    public void updateButton() {
        getJButton().setText(actionCommand ? NOM_ACTION_REMPLI : NOM_ACTION_CONTOURS);
        getJButton().setBackground(actionCommand ? Color.GREEN : Color.LIGHT_GRAY);
    }

    public void setRemplissageState(boolean remplissageState) {
        this.actionCommand = remplissageState;
    }
}
