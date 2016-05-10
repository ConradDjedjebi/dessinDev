package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

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
    protected VueForme créerVueForme() {
        int largeurRectangle = this.getFin().getX() - this.getDébut().getX();
        int hauteurRectangle = this.getFin().getY() - this.getDébut().getY();

        System.out.println(getDébut());
        System.out.println(getFin());

        return new VueRectangle(
                new Rectangle(getDébut(), largeurRectangle, hauteurRectangle),
                this.getPanneauDessin().getCouleurLigne(), true);
    }
}
