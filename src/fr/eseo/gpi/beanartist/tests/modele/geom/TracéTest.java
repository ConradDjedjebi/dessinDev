package fr.eseo.gpi.beanartist.tests.modele.geom;

/**
 * Created by Elphege on 26/04/2016.
 */

import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.modele.geom.Ligne;
import fr.eseo.gpi.beanartist.modele.geom.Tracé;
import java.util.List;

public class TracéTest {
    public static void main(String []args) {


        //Une liste de points
        Point[] lesPoints = {
                new Point(1, 1),
                new Point(2, 3),
                new Point(5, 5),
                new Point(-1, 10),
                new Point(8, 17),
                new Point(0, -8),
        };

        //Création des LIGNES
        Ligne[] lesLignes = {
                new Ligne(),
                new Ligne(lesPoints[0]),
                new Ligne(lesPoints[2], lesPoints[3]),
                new Ligne(lesPoints[1], 2, 2),
                new Ligne(8, 8, 51, 51),
                new Ligne(11, 12),
        };

        // Création d'un Tracé
        Tracé tracé = new Tracé(lesPoints[0], lesPoints[1]);


        //Ajout des lignes dans le tracé
        tracé.ajouterLigneVers(lesPoints[2]);
        tracé.ajouterLigneVers(lesPoints[3]);
        tracé.ajouterLigneVers(lesPoints[4]);
        tracé.ajouterLigneVers(lesPoints[5]);

        //Affichage du tracé
        System.out.println(tracé.toString());

    }
}
