package fr.eseo.gpi.beanartist.tests.controleur.outils;


import fr.eseo.gpi.beanartist.controleur.outils.OutilLigne;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * @author duhamean
 * @date 02/05/16
 * @project gpi_binome
 */
public class OutilLigneTest {
    private OutilLigne outilLigne;

    public OutilLigneTest () {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist("OutilLigneTest", 500, 500, Color.YELLOW);
        fen.getPanneauDessin().setCouleurLigne(Color.RED);
        outilLigne = new OutilLigne(fen.getPanneauDessin());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(OutilLigneTest::new);
    }

}