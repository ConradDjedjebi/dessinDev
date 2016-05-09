package fr.eseo.gpi.beanartist.tests.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.outils.OutilCercle;
import fr.eseo.gpi.beanartist.controleur.outils.OutilRectangle;
import fr.eseo.gpi.beanartist.controleur.outils.OutilEllipse;
import fr.eseo.gpi.beanartist.controleur.outils.OutilCarré;
import fr.eseo.gpi.beanartist.controleur.outils.OutilLigne;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.*;

/**
 * Created by Elphege on 09/05/2016.
 */
public class OutilsTest {
    private OutilCercle outilCercle;
    private OutilCercle outilRectangle;
    private OutilCercle outilEllipse;
    private OutilCercle outilCarré;
    private OutilCercle outilLigne;

    public OutilsTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        outilCercle = new OutilCercle(fen.getPanneauDessin());
        //outilCarré.associer(fen.getPanneauDessin());
        //outilRectangle = new OutilRectangle(fen.getPanneauDessin());
        //outilEllipse = new OutilEllipse(fen.getPanneauDessin());
        //outilCarré = new OutilCarré(fen.getPanneauDessin());
        //outilLigne = new OutilLigne(fen.getPanneauDessin());*/
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilsTest::new);
    }
}
