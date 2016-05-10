package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueCarré;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilCarré extends OutilForme{
    Carré carré;

    public OutilCarré(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected VueForme créerVueForme() {
        int largeurRectangle = this.getFin().getX() - this.getDébut().getX();
        int hauteurRectangle = this.getDébut().getY() - this.getFin().getY();
        int coté = Math.max(Math.abs(largeurRectangle), Math.abs(hauteurRectangle));
        carré = new Carré(this.getDébut(), coté);
        return new VueCarré(carré, this.getPanneauDessin().getCouleurLigne(), true);
    }

    @Override
    public Forme getForme(){
        return carré;
    }
}
