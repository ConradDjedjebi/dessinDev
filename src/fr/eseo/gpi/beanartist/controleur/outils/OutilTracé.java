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
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        forme = new Tracé(this.getDébut());
        this.getPanneauDessin().ajouterVueForme(this.créerVueForme());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
