package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Tracé;
import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueTracé;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilTracé extends OutilForme {
    Tracé tracé;

    public OutilTracé(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    public void mouseClicked(MouseEvent e) {super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {super.mouseReleased(e);}

    @Override
    public void mouseDragged(MouseEvent e) {super.mouseDragged(e);}

    @Override
    protected VueForme créerVueForme() {
        Tracé tracé = new Tracé(this.getDébut(), this.getLesPoints().get(0));
        for(Point point : getLesPoints()){
            tracé.ajouterLigneVers(point);
        }
        tracé.ajouterLigneVers(getFin());
        return new VueTracé(tracé, this.getPanneauDessin().getCouleurLigne());
    }

    /*protected VueForme créerVueFormeParDéfaut(){
        return new VueTracé(new Tracé(new Point(), new Point()), this.getPanneauDessin().getCouleurLigne());
    }*/
}
