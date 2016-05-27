package fr.eseo.gpi.beanartist.xml;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author duhamean
 * @date 27/05/16
 * @project gpi_binome
 */
public abstract class Enregistreur extends ProcesseurDOM {
    public abstract void enregistreDessin(String nomFichier, List<VueForme> dessin) throws FileNotFoundException;
}
