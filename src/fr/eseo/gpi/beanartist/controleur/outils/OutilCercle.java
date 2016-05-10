package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Cercle;
import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.vue.geom.VueCercle;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilCercle extends OutilForme{
    Cercle cercle;

    public OutilCercle(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override
    protected VueForme créerVueForme() {
        int largeurCercle = this.getFin().getX() - this.getDébut().getX();
        int hauteurCercle = this.getDébut().getY() - this.getFin().getY();
        int diamètre = Math.max(Math.abs(largeurCercle), Math.abs(hauteurCercle));
        cercle = new Cercle(this.getDébut(), diamètre);
        return new VueCercle(cercle, this.getPanneauDessin().getCouleurLigne(), true);
    }

    @Override
    public Forme getForme(){
        return cercle;
    }
}
