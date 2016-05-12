package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;


/**
 * @author Elphege
 * @date 11/05/2016
 * @project gpi_binome
 */
public class ActionModeRemplissage extends AbstractAction {

    private PanneauDessin panneau;
    public static final String NOM_ACTION_REMPLI   = "Forme remplie";
    public static final String NOM_ACTION_CONTOURS = "Contours uniquement";

    public static final boolean REMPLIE = true;
    public static final boolean CONTOURS = false;
    private boolean actionCommand;
    private JButton jButton;

    public ActionModeRemplissage () {}

    public ActionModeRemplissage (PanneauDessin panneauDessin) {
        super();
        this.actionCommand = OutilForme.DEFAULT_REMPLISSAGE_MODE;
        this.panneau = panneauDessin;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        actionCommand = !actionCommand;
        panneau.setModeRemplissage(actionCommand);
        updateButton();
    }

    private void updateButton() {
        getJButton().setText(actionCommand ? NOM_ACTION_REMPLI : NOM_ACTION_CONTOURS);
        getJButton().setBackground(actionCommand ? Color.GREEN : Color.LIGHT_GRAY);
    }

    public JButton getJButton() {
        return jButton;
    }

    public void setJButton(JButton jButton) {
        this.jButton = jButton;
        updateButton();
    }
}
