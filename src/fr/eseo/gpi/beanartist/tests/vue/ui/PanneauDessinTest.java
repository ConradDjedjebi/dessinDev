package fr.eseo.gpi.beanartist.tests.vue.ui;

import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.modele.geom.Ligne;
import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueLigne;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.Color;

/**
 * @author duhamean
 * @date 28/04/16
 * @project gpi_binome
 */
public class PanneauDessinTest {
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(PanneauDessinTest::new);
	}

    public PanneauDessinTest() {
        FenêtreBeAnArtist fenêtreBeAnArtist = new FenêtreBeAnArtist("Test PanneauDessin");
        PanneauDessin panneauDessin = fenêtreBeAnArtist.getPanneauDessin();

        Rectangle [] rectangles = {
                new Rectangle(100,40),
                new Rectangle(),
                new Rectangle(300,300,50,50),
                new Carré(25)
        };


        for (Rectangle rectangle :
                rectangles) {
            panneauDessin.ajouterVueForme(new VueRectangle(rectangle, Color.WHITE, true));
        }

        // La croix et la banière
        panneauDessin.ajouterVueForme(new VueLigne(new Ligne(68,255), Color.GREEN));
        panneauDessin.ajouterVueForme(new VueLigne(new Ligne(0, 255, 68, -255), Color.GREEN));

        panneauDessin.ajouterVueForme(new VueLigne(new Ligne(100, 100, 0, 150), Color.BLACK));


        // Les cercles et ellipses
        // panneauDessin.ajouterVueForme(new VueEllipse(new Ellipse(100, 100, 0, 150), Color.YELLOW));
        // panneauDessin.ajouterVueForme(new VueEllipse(new Ellipse(), Color.YELLOW));
        // panneauDessin.ajouterVueForme(new VueCercle(new Cercle(25), Color.BLUE));

        // // Test des tracés
        // panneauDessin.ajouterVueForme(new VueTracé(new Tracé(), Color.BLUE));

        rectangles[0].déplacerVers(100,100);
        rectangles[1].déplacerVers(200,100);

        System.out.println("-- End of TEST");
    }

}