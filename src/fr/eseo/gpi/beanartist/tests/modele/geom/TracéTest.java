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
                new Point(219, -100),
                new Point(222, 50),
                new Point(500, 400),
                new Point(-1, 10),
                new Point(8, 107),
                new Point(70, -8),
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
        System.out.println(tracé.toString() + "\n");

        //Ajout des lignes dans le tracé

        tracé.ajouterLigneVers(lesPoints[2]);
        System.out.println(tracé.toString() + "\n");




        //Testons la méthode setHauteur
        tracé.setLargeur(100);
        System.out.println(tracé.toString() + "\n");

        /*tracé.ajouterLigneVers(lesPoints[3]);
        System.out.println(tracé.toString() + "\n");

        tracé.ajouterLigneVers(lesPoints[4]);
        System.out.println(tracé.toString() + "\n");*/

        //tracé.setHauteur(32);
        //System.out.println(tracé.toString() + "\n");

        /*tracé.ajouterLigneVers(lesPoints[3]);
        tracé.ajouterLigneVers(lesPoints[4]);*/

        //tracé.ajouterLigneVers(lesPoints[5]);

        //Affichage du tracé
        //System.out.println(tracé.toString());

        //Testons la méthode contient

        /*boolean bool = tracé.contient(lesPoints[0]);
        for(int i = 0; i<7; i++){
            if(tracé.contient(lesPoints[i])){
                System.out.println("Le point : " + lesPoints[i].toString() + " appartient au tracé");
            }
            else{
                System.out.println("Le point : " + lesPoints[i].toString()+ " n'appartient pas au tracé");
            }
        }*/




    }
}
