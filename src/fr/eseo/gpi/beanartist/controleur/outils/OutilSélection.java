package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.actions.ActionEffacer;
import fr.eseo.gpi.beanartist.controleur.actions.ActionModeRemplissage;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.modele.geom.Forme;

import java.util.List;
import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 09/05/2016
 * @project gpi_binome
 */
public class OutilSélection extends Outil {

    private ActionModeRemplissage actionModeRemplissage;
    private ActionEffacer actionEffacer;

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
            actionModeRemplissage.getJButton().setEnabled(!this.isEmptySelection());
            actionModeRemplissage.setRemplissageState(
                    this.getVueForme().estRempli());
            actionModeRemplissage.updateButton();
        } catch (NullPointerException exception) {
        }
        try {
            actionEffacer.getJButton().setEnabled(!this.isEmptySelection());
        } catch (NullPointerException exception) {
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

    public void emptySelection() {
        setDébut(null);
        this.getPanneauDessin().getLabel().setText(afficherFormeSélectionnée());
        actionModeRemplissage.getJButton().setEnabled(false);
        actionEffacer.getJButton().setEnabled(false);
    }

    public boolean isEmptySelection() {
        return getVueForme()==null;
    }

    public void setActionModeRemplissage(ActionModeRemplissage modeRemplissage) {
        this.actionModeRemplissage = modeRemplissage;
    }

    public void setActionEffacer(ActionEffacer actionEffacer) {
        this.actionEffacer = actionEffacer;
    }
}
