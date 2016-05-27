package fr.eseo.gpi.beanartist.xml;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author duhamean
 * @date 27/05/16
 * @project gpi_binome
 */


/**
 * Classe regroupant les méthodes d'enregistrement des dessins indépendamment du format de sortie
 */
public abstract class Enregistreur extends ProcesseurDOM {

    static final String TRUE_VALUE = "true";
    static final String FALSE_VALUE = "false";
    static final String EMPTY_VALUE = "none";

    /**
     * Enregistre le dessin donné.
     * @param nomFichier le nom du fichier de sauvegarde
     * @param dessin le dessin formé de la liste des vues des formes
     * @throws FileNotFoundException si le nom du fichier n'est pas valide
     */
    public abstract void enregistreDessin(String nomFichier, List<VueForme> dessin) throws FileNotFoundException;
}
