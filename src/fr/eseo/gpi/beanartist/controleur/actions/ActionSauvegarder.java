package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.xml.Enregistreur;
import fr.eseo.gpi.beanartist.xml.EnregistreurSVG;
import fr.eseo.gpi.beanartist.xml.EnregistreurXML;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

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
        fc.setFileFilter(EnregistreurXML.getExtensionFilter());
        fc.addChoosableFileFilter(EnregistreurSVG.getExtensionFilter());

        try {
            int returnVal = fc.showSaveDialog(panneauDessin);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String fileName = getSelectedFileAbsolutePathWithExtension(fc);
                Enregistreur saver = fileName.endsWith(".svg") ? new EnregistreurSVG() : new EnregistreurXML();

                saver.enregistreDessin(fileName, panneauDessin.getVueFormes());
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }


    private static String getSelectedFileAbsolutePathWithExtension(JFileChooser jFileChooser) {
        File file = jFileChooser.getSelectedFile();
        if (jFileChooser.getFileFilter() instanceof FileNameExtensionFilter) {
            String[] exts = ((FileNameExtensionFilter)jFileChooser.getFileFilter()).getExtensions();
            String nameLower = file.getName().toLowerCase();
            for (String ext : exts) { // check if it already has a valid extension
                if (nameLower.endsWith('.' + ext.toLowerCase())) {
                    return file.getAbsolutePath(); // if yes, return as-is
                }
            }
            // if not, append the first extension from the selected filter
            return String.format("%s.%s", file.getAbsolutePath(), exts[0]);
        }
        return file.getAbsolutePath();
    }
}
