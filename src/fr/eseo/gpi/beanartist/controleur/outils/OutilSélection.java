package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.actions.ActionModeRemplissage;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.modele.geom.Forme;

import javax.swing.*;
import java.util.List;
import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 09/05/2016
 * @project gpi_binome
 */
public class OutilSélection extends Outil {

    private Forme formeSélectionnée;
    private ActionModeRemplissage actionModeRemplissage;

    public OutilSélection (PanneauDessin panneauDessin){
        super(panneauDessin);
    }

    @Override
    public void mouseClicked (MouseEvent e){
        super.mouseClicked(e);
        System.out.println(afficherFormeSélectionnée());
        this.getPanneauDessin().getLabel().setText(afficherFormeSélectionnée());
        this.getPanneauDessin().revalidate();
        this.getPanneauDessin().repaint();

        try {
            actionModeRemplissage.setRemplissageState(
                    this.getVueForme().estRempli());
            actionModeRemplissage.updateButton();
        } catch (NullPointerException exception) {
            System.out.print(actionModeRemplissage.toString());
        }
    }

    public VueForme getVueForme(){
        List<VueForme> vueFormes = this.getPanneauDessin().getVueFormes();
        int count = vueFormes.size();

        try {
            while (!vueFormes.get(--count).getForme().contient(getDébut())) {}
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        formeSélectionnée = vueFormes.get(count).getForme();
        this.getPanneauDessin().setVueFormeSélectionnée(vueFormes.get(count));

        return vueFormes.get(count);
    }

    public Forme getForme() {
        return getVueForme().getForme();
    }

    protected String afficherFormeSélectionnée() {
        try {
            return this.getForme().toString();
        } catch(NullPointerException e) {
            return "Aucune forme sélectionnée";
        }
    }

    public void setActionModeRemplissage(ActionModeRemplissage modeRemplissage) {
        this.actionModeRemplissage = modeRemplissage;
        modeRemplissage.getJButton().setEnabled(false);
    }
}
