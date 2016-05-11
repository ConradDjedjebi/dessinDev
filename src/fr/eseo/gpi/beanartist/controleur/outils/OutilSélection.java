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

    private int size = this.getPanneauDessin().getVueFormes().size();
    private Forme formeSélectionnée;

    public OutilSélection (PanneauDessin panneauDessin){
        super(panneauDessin);
    }

    @Override
    public void mouseClicked (MouseEvent e){
        super.mouseClicked(e);
        this.afficherFormeSélectionnée();
    }

    protected VueForme getSélection(){
        List<VueForme> vueFormes = this.getPanneauDessin().getVueFormes();
        int count = size;

        while (count > 0 && !vueFormes.get(--count).getForme().contient(getDébut())) {}

        if(count==0)
            return null;

        formeSélectionnée = vueFormes.get(count).getForme();

        //On affiche la dite forme et on réinitialise le compteur
        return vueFormes.get(count);
    }

    protected String afficherFormeSélectionnée() {
        return this.getSélection().getForme().toString();
    }

}
