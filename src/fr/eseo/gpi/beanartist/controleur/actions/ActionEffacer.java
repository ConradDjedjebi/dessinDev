package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import java.awt.event.ActionEvent;

/**
 * @author duhamean
 * @date 02/05/16
 * @project gpi_binome
 */
public class ActionEffacer extends AbstractSelectionAction {
    public static final String NOM_ACTION = "Effacer tout";
    public static final String NOM_ACTION_SINGLE = "Effacer la sélection";

    /**
     * Constructeur de l'outil de remise à "blanc"
     * @param fenetre La fenêtre associée
     */
    public ActionEffacer (FenêtreBeAnArtist fenetre) {
        super(NOM_ACTION, fenetre);
    }


    /**
     * Constructeur de l'outil de suppression de forme sélectionnée actuelement
     * @param fenetre La fenêtre associée
     * @param outilSélection L'outil sélection pour récupérer la forme sélectionnée
     */
    public ActionEffacer (FenêtreBeAnArtist fenetre, OutilSélection outilSélection) {
        super(NOM_ACTION_SINGLE, fenetre);
        setOutilSélection(outilSélection);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (getOutilSélection() == null)
                fenetre.getPanneauDessin().getVueFormes().clear();
            else
                fenetre.getPanneauDessin().getVueFormes().remove(getOutilSélection().getVueForme());
                getOutilSélection().emptySelection();
        } catch (NullPointerException exception) {
            // Aucune forme selectionnée
        }
        fenetre.getPanneauDessin().repaint();
    }

}
