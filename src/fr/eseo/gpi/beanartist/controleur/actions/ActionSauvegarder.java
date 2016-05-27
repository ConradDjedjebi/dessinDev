package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.xml.Enregistreur;
import fr.eseo.gpi.beanartist.xml.EnregistreurSVG;
import fr.eseo.gpi.beanartist.xml.EnregistreurXML;
import fr.eseo.gpi.beanartist.xml.LecteurXML;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author djedjebi Elphege
 * @date 19/05/16
 * @project gpi_binome
 */
public class ActionSauvegarder extends AbstractAction  {
    private PanneauDessin panneauDessin;
    protected static final String NOM_ACTION = "Sauvegarder";

    public ActionSauvegarder(FenêtreBeAnArtist newFenêtre) {
        super(NOM_ACTION);
        panneauDessin = newFenêtre.getPanneauDessin();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(
                "Document graphique BeAnArtist", "beanartist", "xml"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter(
                "Image SVG", "svg"));

        try {
            int returnVal = fc.showSaveDialog(panneauDessin);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Enregistreur saver;
                String fileName = getSelectedFileWithExtension(fc).getName();
                int indexOfDot = fileName.lastIndexOf(".");
                if (indexOfDot>0 && fileName.substring(indexOfDot).equals(".svg")) {
                    saver = new EnregistreurSVG();
                }
                else {
                    saver = new EnregistreurXML();
                }

                saver.enregistreDessin(fc.getCurrentDirectory()+File.separator+fileName, panneauDessin.getVueFormes());
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private static File getSelectedFileWithExtension(JFileChooser c) {
        File file = c.getSelectedFile();
        if (c.getFileFilter() instanceof FileNameExtensionFilter) {
            String[] exts = ((FileNameExtensionFilter)c.getFileFilter()).getExtensions();
            String nameLower = file.getName().toLowerCase();
            for (String ext : exts) { // check if it already has a valid extension
                if (nameLower.endsWith('.' + ext.toLowerCase())) {
                    return file; // if yes, return as-is
                }
            }
            // if not, append the first extension from the selected filter
            file = new File(file.toString() + '.' + exts[0]);
        }
        return file;
    }
}
