package fr.eseo.gpi.beanartist.tests.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.outils.OutilTracé;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilTracéTest {
    private OutilTracé outilTracé;

    public OutilTracéTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilTracéTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        outilTracé = new OutilTracé(fen.getPanneauDessin());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilTracéTest::new);
    }

}
