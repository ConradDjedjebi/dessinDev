package fr.eseo.gpi.beanartist.tests.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.outils.OutilCarré;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.*;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilCarréTest {
    private OutilCarré outilCarré;

    public OutilCarréTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilCarréTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        outilCarré = new OutilCarré(fen.getPanneauDessin());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilCarréTest::new);
    }

}
