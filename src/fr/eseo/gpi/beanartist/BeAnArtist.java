package fr.eseo.gpi.beanartist;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

/**
 * @author duhamean
 * @date 12/05/16
 * @project gpi_binome
 */
public class BeAnArtist {
//    BeAnArtist () {
//        new FenêtreBeAnArtist("BeAnArtist");
//    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(FenêtreBeAnArtist::new);
    }
}
