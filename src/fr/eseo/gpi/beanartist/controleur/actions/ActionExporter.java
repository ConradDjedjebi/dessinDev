package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.xml.EnregistreurSVG;
import fr.eseo.gpi.beanartist.xml.EnregistreurXML;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

/**
 * @author djedjebi Elphege
 * @date 19/05/16
 * @project gpi_binome
 */
public class ActionExporter extends AbstractAction  {
    private FenêtreBeAnArtist fenêtre;
    protected static final String NOM_ACTION = "Exporter en SVG";

    public ActionExporter(FenêtreBeAnArtist newFenêtre) {
        super(NOM_ACTION);
        fenêtre = newFenêtre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        EnregistreurSVG enrégistreur = new EnregistreurSVG();
        try {
            enrégistreur.enregistreDessin("savedFile.svg", fenêtre.getPanneauDessin().getVueFormes());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
