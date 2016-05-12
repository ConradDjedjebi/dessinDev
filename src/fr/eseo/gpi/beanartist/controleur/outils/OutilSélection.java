package fr.eseo.gpi.beanartist.controleur.outils;

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

    private Forme formeSélectionnée;

    public OutilSélection (PanneauDessin panneauDessin){
        super(panneauDessin);
    }

    @Override
    public void mouseClicked (MouseEvent e){
        super.mouseClicked(e);
        System.out.println(afficherFormeSélectionnée());
    }

    protected VueForme getSélection(){
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

    protected String afficherFormeSélectionnée() {
        try {
            return this.getSélection().getForme().toString();
        } catch(NullPointerException e) {
            return "Aucune forme trouvée";
        }
    }

}
