package fr.eseo.gpi.beanartist.tests.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.outils.OutilRectangle;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilRectangleTest {
    private OutilRectangle outilRectangle;

    public OutilRectangleTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilRectangleTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        outilRectangle = new OutilRectangle(fen.getPanneauDessin());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilRectangleTest::new);
    }
}
