package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueCarré;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.MouseEvent;

/**
 * @author Elphege
 * @date 03/05/2016
 * @project gpi_binome
 */
public class OutilCarré extends OutilForme{
    public OutilCarré(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        forme = new Carré();
        super.mousePressed(e);
        forme.setPosition(getDébut());
    }

    @Override
    protected void updateForme() {
        ((Carré)this.forme).setLargeur(this.getFin().getY() - this.getDébut().getY(),
                getDébut().getX()<getFin().getX() && getDébut().getY()>getFin().getY() ||
                getDébut().getX()>getFin().getX() && getDébut().getY()<getFin().getY()
        );
    }
    @Override
    protected VueForme créerVueForme() {
        return new VueCarré((Carré)forme, this.getPanneauDessin().getCouleurLigne(), rempli);
    }
}
