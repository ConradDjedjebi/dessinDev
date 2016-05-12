package fr.eseo.gpi.beanartist;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.Color;

/**
 * @author duhamean
 * @date 12/05/16
 * @project gpi_binome
 */
public class BeAnArtist {
    BeAnArtist () {
        new FenêtreBeAnArtist("BeAnArtist", 1000, 800, Color.YELLOW);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(BeAnArtist::new);
    }
}
