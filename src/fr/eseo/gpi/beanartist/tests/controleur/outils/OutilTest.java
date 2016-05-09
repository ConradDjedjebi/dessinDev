package fr.eseo.gpi.beanartist.tests.controleur.outils;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * @author duhamean
 * @date 09/05/16
 * @project gpi_binome
 */
public class OutilTest {
    public OutilTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilTest", 1000, 800, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilTest::new);
    }
}
