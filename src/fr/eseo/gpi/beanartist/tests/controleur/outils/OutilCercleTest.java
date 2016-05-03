package fr.eseo.gpi.beanartist.tests.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.outils.OutilCercle;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilCercleTest {
    private OutilCercle outilCercle;

    public OutilCercleTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilCercleTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        outilCercle = new OutilCercle(fen.getPanneauDessin());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilCercleTest::new);
    }
}
