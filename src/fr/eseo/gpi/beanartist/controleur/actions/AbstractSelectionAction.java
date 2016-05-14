package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
abstract class AbstractSelectionAction extends AbstractAction {

    private OutilSélection outilSélection;
    private JButton jButton;
    protected FenêtreBeAnArtist fenetre;


    public AbstractSelectionAction(String nomAction, FenêtreBeAnArtist fenetre) {
        super(nomAction);
        this.fenetre = fenetre;
    }

    public AbstractSelectionAction() {
        super();
    }

    public void setJButton(JButton button) {
        this.jButton = button;
    }
    public JButton getJButton() {
        return this.jButton;
    }

    public OutilSélection getOutilSélection() {
        return outilSélection;
    }

    public void setOutilSélection(OutilSélection outilSélection) {
        this.outilSélection = outilSélection;
    }
}
