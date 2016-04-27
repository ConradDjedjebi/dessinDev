package fr.eseo.gpi.beanartist.tests.modele.geom;

/**
 * Created by Elphege on 26/04/2016.
 */

import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.modele.geom.Ligne;

public class LigneTest {

    public static void main (String [] args) {
        /*
        * Créer des points et générer des lignes
        * Tester la création de lignes avec tous les constructeurs
        * Tester la fonction contient
        * Appeler le calcul de l'aire pour diverses lignes
        */


        //Une liste de points
        Point[] lesPoints = {
                new Point(1,1),
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
                new Ligne(lesPoints[1], 2,2),
                new Ligne(8,8,51,51),
                new Ligne(11,12),
        };

        //Affichage des différentes lignes

        for (int i = 0; i<lesLignes.length; i++){
            System.out.println(lesLignes[i].toString());
        }


        //Déplacement de la position

        Ligne ligneTest = new Ligne(188,138,24,-50);
        String s = ligneTest.toString() + " Largeur = "+ligneTest.getLargeur();
        System.out.println(s);
        ligneTest.setP1(new Point(112,41));
        String s1 = ligneTest.toString() + " Largeur = "+ligneTest.getLargeur();
        System.out.println(s1);


        //width appears wrong.
        //raw test result: expected [100] but found [24]

        //On test les méthodes contient
    }
}



