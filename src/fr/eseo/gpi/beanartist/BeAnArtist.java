package fr.eseo.gpi.beanartist;

/**
 * @author duhamean
 * @date 12/05/16
 * @project gpi_binome
 */
public class BeAnArtist {
   public BeAnArtist () {
       main(null);
   }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist::new);
    }
}
