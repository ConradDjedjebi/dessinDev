package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.xml.LecteurXML;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author djedjebi Elphege
 * @date 22/05/16
 * @project gpi_binome
 */
public class ActionOuvrir extends AbstractAction  {
    private PanneauDessin panneauDessin;
    protected static final String NOM_ACTION = "Ouvrir";

    public ActionOuvrir(FenêtreBeAnArtist newFenêtre) {
        super(NOM_ACTION);
        panneauDessin = newFenêtre.getPanneauDessin();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(
                "Document graphique BeAnArtist", "beanartist", "xml"));
        LecteurXML lecteur = new LecteurXML();
        try {
            int returnVal = fc.showOpenDialog(panneauDessin);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                List<VueForme> lesVueFormes = lecteur.lisDessin(
                        fc.getSelectedFile().getAbsolutePath() +
                                (fc.getSelectedFile().exists() ?
                                        // Si le fichier n'existe pas, on ajoute l'extension par défaut
                                        "" : ".beanartist"
                                )
                );
                panneauDessin.getVueFormes().clear();
                for(VueForme vueForme : lesVueFormes){
                    panneauDessin.ajouterVueForme(vueForme);
                }
                panneauDessin.repaint();
            }
        } catch (FileNotFoundException e1) {
            panneauDessin.getLabel().setText("Fichier introuvable");
        }
    }
}
