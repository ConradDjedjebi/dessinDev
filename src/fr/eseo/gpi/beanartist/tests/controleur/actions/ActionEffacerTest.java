package fr.eseo.gpi.beanartist.tests.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilLigne;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * @author duhamean
 * @date 03/05/16
 * @project gpi_binome
 */
public class ActionEffacerTest {

    public ActionEffacerTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("ActionEffacerTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        new OutilLigne(fen.getPanneauDessin());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(ActionEffacerTest::new);
    }
}
