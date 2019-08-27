package fr.eseo.gpi.beanartist;

/**
 * @author Antoine du HAMEL
 * @author Conrad Djedjebi
 * @date 15/04/17
 * @project dessin
 */
public class BeAnArtist {
   public BeAnArtist () {
       main(null);
   }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist::new);
    }
}
