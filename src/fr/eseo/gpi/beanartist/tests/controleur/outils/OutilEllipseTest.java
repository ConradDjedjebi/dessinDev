package fr.eseo.gpi.beanartist.tests.controleur.outils;

import fr.eseo.gpi.beanartist.controleur.outils.OutilEllipse;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * Created by Elphege on 03/05/2016.
 */
public class OutilEllipseTest {
    private OutilEllipse outilEllipse;

    public OutilEllipseTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilEllipseTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        outilEllipse = new OutilEllipse(fen.getPanneauDessin());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilEllipseTest::new);
    }
}
