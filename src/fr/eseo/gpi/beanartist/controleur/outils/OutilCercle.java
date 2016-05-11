package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Cercle;
import fr.eseo.gpi.beanartist.vue.geom.VueCercle;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilCercle extends OutilForme{

    public OutilCercle(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        forme = new Cercle();
        super.mousePressed(e);
        forme.setPosition(getDébut());
    }

    @Override
    protected void updateForme() {
        this.forme.setHauteur(this.getFin().getY() - this.getDébut().getY());
    }

    @Override
    protected VueForme créerVueForme() {
        return new VueCercle(
                (Cercle) forme,
                this.getPanneauDessin().getCouleurLigne(), true);
    }

}
