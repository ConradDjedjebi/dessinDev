package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.actions.AbstractSelectionAction;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.modele.geom.Forme;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 09/05/2016
 * @project gpi_binome
 */
public class OutilSélection extends Outil {

    private VueForme vueFormeSélectionnée;
    private ArrayList<AbstractSelectionAction> actions;

    public OutilSélection (PanneauDessin panneauDessin){
        super(panneauDessin);
        actions = new ArrayList<>();
    }

    @Override
    public void mouseClicked (MouseEvent e){
        emptySelection();
        super.mouseClicked(e);
        System.out.println(afficherFormeSélectionnée());
        this.updateButtons();
        this.getPanneauDessin().revalidate();
        this.getPanneauDessin().repaint();
    }

    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);
        setFin(getDébut());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            setDébut(getFin());
            super.mouseDragged(e);
            this.getForme().déplacerDe(getFin().getX()-getDébut().getX(), getFin().getY() - getDébut().getY());
            this.afficherFormeSélectionnée();
            this.getPanneauDessin().repaint();
        } catch (NullPointerException excpetion) {}
    }

    public VueForme getVueForme(){
        if(vueFormeSélectionnée!=null)
            return vueFormeSélectionnée;
        else if (getDébut()==null)
            return null;

        List<VueForme> vueFormes = this.getPanneauDessin().getVueFormes();
        int count = vueFormes.size();

        try {
            while (!vueFormes.get(--count).getForme().contient(getDébut())) {}
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        this.getPanneauDessin().setVueFormeSélectionnée(vueFormes.get(count));

        return vueFormeSélectionnée = vueFormes.get(count);
    }

    public Forme getForme() {
        return getVueForme().getForme();
    }

    protected String afficherFormeSélectionnée() {
        try {
            this.getPanneauDessin().getLabel().setText(this.getForme().toString());
            return this.getForme().toString();
        } catch(NullPointerException e) {
            return "Aucune forme sélectionnée";
        }
    }

    public void emptySelection() {
        setDébut(null);
        this.vueFormeSélectionnée = null;
        afficherFormeSélectionnée();
        updateButtons();
    }

    public boolean isEmptySelection() {
        return getVueForme()==null;
    }

    public void addAction(AbstractSelectionAction action) {
        this.actions.add(action);
    }

    public void updateButtons() {
        for (AbstractSelectionAction action : actions) {
            action.updateButton(this.isEmptySelection());
        }
    }

    public ArrayList<AbstractSelectionAction> getActions() {
        return actions;
    }
}
