package fr.eseo.gpi.beanartist.tests.vue.ui;

import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.*;

/**
 * @author duhamean
 * @date 28/04/16
 * @project gpi_binome
 */
public class PanneauDessinTest {
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(PanneauDessinTest::testFenêtre);
	}

    public static void testFenêtre() {
        FenêtreBeAnArtist fenêtreBeAnArtist = new FenêtreBeAnArtist("Test PanneauDessin");
        PanneauDessin panneauDessin = fenêtreBeAnArtist.getPanneauDessin();

        Rectangle [] rectangles = {
                new Rectangle(30,40),
                new Rectangle(),
                new Rectangle(300,300,50,50),
                new Carré(25)
        };


        for (Rectangle rectangle :
                rectangles) {
            panneauDessin.ajouterVueForme(new VueRectangle(rectangle, Color.WHITE, true));
        }

        rectangles[0].déplacerVers(100,100);

        System.out.println("-- End of TEST");
    }

    public void setFenêtre() {

    }


    public void getVueFormes() {

    }

    
    public void ajouterVueForme() {

    }

}