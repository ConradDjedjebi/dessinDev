package fr.eseo.gpi.beanartist.tests.vue.ui;


import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

/**
 * @author duhamean
 * @date 26/04/16
 * @project gpi_binome
 */
public class FenêtreBeAnArtistTest {
    public FenêtreBeAnArtistTest()
    {
        testDefaultConstructor();
    }

    public static void testDefaultConstructor() {
        FenêtreBeAnArtist fen = new FenêtreBeAnArtist();
        PanneauDessin panneauDessin = fen.getPanneauDessin();


        System.out.println("End of test");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(FenêtreBeAnArtistTest::new);
    }
}