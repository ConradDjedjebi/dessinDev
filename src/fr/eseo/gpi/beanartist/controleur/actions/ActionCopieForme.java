package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilSélection;
import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;

import java.awt.event.ActionEvent;


/**
 * @author Antoine
 * @date 14/05/2016
 * @project gpi_binome
 */
public class ActionCopieForme extends AbstractSelectionAction {

    public static final String NOM_ACTION = "Copier la sélection";

    public ActionCopieForme() {}

    public ActionCopieForme(FenêtreBeAnArtist fenetre, OutilSélection outilSélection) {
        super(NOM_ACTION, fenetre);
        setOutilSélection(outilSélection);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            PanneauDessin panneau = fenetre.getPanneauDessin();
            VueForme vueForme = panneau.getVueFormeSélectionnée().clone();

            panneau.getVueFormeSélectionnée().getForme().déplacerDe(Forme.HAUTEUR_PAR_DÉFAUT, Forme.LARGEUR_PAR_DÉFAUT);

            panneau.ajouterVueForme(vueForme);
            panneau.repaint();
        } catch (NullPointerException e) {
        }
    }
}
