package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilGomme;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.xml.EnregistreurXML;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

/**
 * @author Elphege
 * @date 20/05/16
 * @project gpi_binome
 */
public class ActionGommer extends AbstractAction  {
    private FenêtreBeAnArtist fenêtre;
    protected static final String NOM_ACTION = "Gommer";

    public ActionGommer(FenêtreBeAnArtist newFenêtre) {
        super(NOM_ACTION);
        fenêtre = newFenêtre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //Une forme est crée
        //On déplace la forme sur l'espace de travail
        //Lorsqu'un point de la forme entre en colision avec une forme de l'espace de travail, les points concernés sont supprimé de la forme

        //La gomme est automatiquement mise en sélection mais ne fait pas parmis des VueFormes.

        //Par défaut, la gomme fait 130/60 de dimensions. Mais lorsque l'on clic et drage, on redimensionne la gomme

        // Dans la classe Panneua, créer un outilForme qui permettra d'afficer la gomme


        //Le premier click de la souris génère la gomme
        //Le deuxième
        new OutilGomme(fenêtre.getPanneauDessin());

        /*EnregistreurXML enrégistreur = new EnregistreurXML();
        try {
            enrégistreur.enregistreDessin("savedFile", fenêtre.getPanneauDessin().getVueFormes());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }*/
    }
}
