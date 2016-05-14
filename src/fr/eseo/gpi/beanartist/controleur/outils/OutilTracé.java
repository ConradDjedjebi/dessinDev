package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Tracé;
import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueTracé;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author Elphege & Antoine
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilTracé extends OutilForme {

    public OutilTracé(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        forme = new Tracé();
        super.mousePressed(e);
        forme.setPosition(this.getDébut());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    protected void updateForme() {
        ((Tracé)forme).ajouterLigneVers(new Point(getFin().getX(), getFin().getY()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    protected VueForme créerVueForme() {
        return new VueTracé((Tracé)forme, this.getPanneauDessin().getCouleurLigne());
    }
}
