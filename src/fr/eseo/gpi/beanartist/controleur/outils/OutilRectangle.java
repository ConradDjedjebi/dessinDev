package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilRectangle extends OutilForme {
    public OutilRectangle(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        forme = new Rectangle();
        super.mousePressed(e);
        forme.setPosition(getDébut());
    }

    protected void updateForme() {
        int largeurRectangle = this.getFin().getX() - this.getDébut().getX();
        int hauteurRectangle = this.getFin().getY() - this.getDébut().getY();

        forme.setLargeur(largeurRectangle);
        forme.setHauteur(hauteurRectangle);
    }

    @Override
    protected VueForme créerVueForme() {
        return new VueRectangle(
                (Rectangle)forme,
                this.getPanneauDessin().getCouleurLigne(), true);
    }
}
