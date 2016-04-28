package fr.eseo.gpi.beanartist.tests.vue.ui;

import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

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
        PanneauDessin p = fenêtreBeAnArtist.getPanneauDessin();

        Rectangle r = new Rectangle(30,40);
        VueRectangle vueR = new VueRectangle(r, true);

        p.ajouterVueForme(vueR);
        p.paintComponent();
    }

    public void setFenêtre() {

    }


    public void getVueFormes() {

    }

    
    public void ajouterVueForme() {

    }

}