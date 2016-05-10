package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;
import fr.eseo.gpi.beanartist.vue.ui.PanneauBarreOutil;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.modele.geom.Forme;

import java.util.List;
import java.awt.event.MouseEvent;

/**
 * Created by Elphege on 09/05/2016.
 */
public class OutilSélection extends Outil {

    private int size = this.getPanneauDessin().getVueFormes().size();
    private int compteur;
    private int max = -1;
    private Forme formeSélectionnée;
    private int [] lesFormesConcernées = new int [size] ;



    public OutilSélection (PanneauDessin panneauDessin){
        super(panneauDessin);
    }

    @Override
    public void mouseClicked (MouseEvent e){
        super.mouseClicked(e);
        this.afficherFormeSélectionnée();
    }

    protected String afficherFormeSélectionnée(){
        List<VueForme> vueFormes = this.getPanneauDessin().getVueFormes();
        //On initialise le tableau avec des valeurs qui ne référenciant pas une VueForme
        for(int i = 0; i<size; i++){
            lesFormesConcernées[i] = -1;
        }
        //On récupère tous les indices qui référencient une VueForme contenant le point
        for(int i =0; i<size; i++) {
            if (vueFormes.get(i).getForme().contient(getUnPoint())) {
                lesFormesConcernées[i] = i;
            }
        }
        //On recherche la forme à afficher
        for(int i =0; i<size; i++) {
            if (lesFormesConcernées[i] != -1) {
                compteur++;
                if(lesFormesConcernées[i]>max){
                    max = lesFormesConcernées[i];
                }
            }
        }
        if(compteur>1){
            formeSélectionnée = vueFormes.get(max).getForme();
        }
        else{
            for(int i = 0; i<size; i++) {
                if (lesFormesConcernées[i] != -1) {
                    formeSélectionnée = vueFormes.get(i).getForme();
                }
            }
        }

        //On affiche la dite forme et on réinitialise le compteur
        return formeSélectionnée.toString();
    }

}
