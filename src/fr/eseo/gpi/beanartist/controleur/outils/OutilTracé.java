package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Tracé;
import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueTracé;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Elphege & Antoine
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilTracé extends OutilForme {
    private Tracé tracé;

    public OutilTracé(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        tracé = new Tracé(this.getDébut());
        this.getPanneauDessin().ajouterVueForme(this.créerVueForme());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        tracé.ajouterLigneVers(new Point(e.getX(), e.getY()));
        this.getPanneauDessin().repaint();
    }

    @Override
    protected VueForme créerVueForme() {
//        this.lesPoints.forEach(tracé::ajouterLigneVers);
        return new VueTracé(tracé, this.getPanneauDessin().getCouleurLigne());
    }


    /*protected VueForme créerVueFormeParDéfaut(){
        return new VueTracé(new Tracé(new Point(), new Point()), this.getPanneauDessin().getCouleurLigne());
    }*/
}
