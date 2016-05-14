package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Ellipse;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilEllipse extends OutilForme {

    public OutilEllipse(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        forme = new Ellipse();
        super.mousePressed(e);
        forme.setPosition(getDébut());
    }


    @Override
    protected VueForme créerVueForme() {
        return new VueEllipse(
                (Ellipse)forme,
                this.getPanneauDessin().getCouleurLigne(),  this.getPanneauDessin().estModeRemplissage());
    }

}
