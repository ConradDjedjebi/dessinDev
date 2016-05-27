package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.xml.EnregistreurXML;
import fr.eseo.gpi.beanartist.xml.LecteurXML;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author djedjebi Elphege
 * @date 22/05/16
 * @project gpi_binome
 */
public class ActionOuvrir extends AbstractAction  {
    private FenêtreBeAnArtist fenêtre;
    protected static final String NOM_ACTION = "Ouvrir";

    public ActionOuvrir(FenêtreBeAnArtist newFenêtre) {
        super(NOM_ACTION);
        fenêtre = newFenêtre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        LecteurXML lecteur = new LecteurXML();
        try {
            List<VueForme> lesVueFormes = lecteur.lisDessin("savedFile.xml");
            for(VueForme vueForme : lesVueFormes){
                fenêtre.getPanneauDessin().ajouterVueForme(vueForme);
            }
            fenêtre.getPanneauDessin().repaint();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
