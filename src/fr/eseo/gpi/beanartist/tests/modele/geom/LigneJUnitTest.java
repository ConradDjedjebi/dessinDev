package fr.eseo.gpi.beanartist.tests.modele.geom;

import org.junit.Test;
import fr.eseo.gpi.beanartist.modele.geom.*;
import static org.junit.Assert.*;

/**
 * Created by Elphege on 16/05/2016.
 */
public class LigneJUnitTest extends Object {
    @Test
    public void setPosition() throws Exception {
        Ligne ligne = new Ligne(56,89,5,9);
        Point point = new Point(3,3);
        ligne.setPosition(point);
        String s = "[Ligne] p1 : (3,3), p2 : (8,12), longueur : 10.295630140987";
        assertEquals("L'affichage attendu", s, ligne.toString());
    }

    @Test
    public void setP1() throws Exception {
        Ligne ligne = new Ligne(526,98,7,52);
        Point point = new Point(72,3);
        double longueur = Math.sqrt(Math.pow(533 - 72,2) + Math.pow(150 - 3,2));
        ligne.setP1(point);
        String s = "[Ligne] p1 : (72,3), p2 : (533,150), longueur : " + longueur;
        assertEquals("L'affichage attendu", s, ligne.toString());
    }

    @Test
    public void setP2() throws Exception {
        Ligne ligne = new Ligne(526,98,7,52);
        Point point = new Point(72,3);
        double longueur = Math.sqrt(Math.pow(72 - 526,2) + Math.pow(3 - 98,2));
        ligne.setP2(point);
        String s = "[Ligne] p1 : (526,98), p2 : (72,3), longueur : " + longueur;
        assertEquals("L'affichage attendu", s, ligne.toString());
    }

    @Test
    public void setDimensions() throws Exception {
        Ligne ligne = new Ligne(9, 56,7,52);
        ligne.setDimensions(2,6);
        double longueur = Math.sqrt(Math.pow(2,2) + Math.pow(6,2));
        String s = "[Ligne] p1 : (9,56), p2 : (11,62), longueur : " + longueur;
        assertEquals("L'affichage attendu", s, ligne.toString());
    }

    @Test
    public void déplacerDe() throws Exception {
        Ligne ligne = new Ligne();
        ligne.déplacerDe(2,2);
        String s = "[Ligne] p1 : (2,2), p2 : (72,52), longueur : 86.02325267042627";
        assertEquals("Affichage attendu", s, ligne.toString());
    }

    @Test
    public void contient() throws Exception {
        Ligne ligne = new Ligne(9, 56,7,52);
        Point point1 = new Point(9,63);
        assertTrue("ligne contient point1", ligne.contient(point1));
    }

    @Test
    public void clone2() throws Exception {
        Ligne ligne = new Ligne(80,9,72,154);
        Ligne ligne1 = ligne.clone();
        assertEquals("La ligne attendue", ligne1.toString(), ligne.toString());
    }

    @Test
    public void toString2() throws Exception {
        Ligne ligne = new Ligne();
        Ligne ligne1 = new Ligne(new Point(1,1), new Point(2,2));
        Ligne ligne2 = new Ligne(new Point(80,2), 24, 40);
        Ligne ligne3 = new Ligne(10, 80);
        Ligne ligne4 = new Ligne(4, 6, 80,90);
        Ligne ligne5 = new Ligne(new Point(70,245));
        String s ="[Ligne] p1 : (0,0), p2 : (70,50), longueur : 86.02325267042627";
        String s1 = "[Ligne] p1 : (1,1), p2 : (2,2), longueur : 1.4142135623730951";
        String s2 ="[Ligne] p1 : (80,2), p2 : (104,42), longueur : 46.647615158762406";
        String s3 = "[Ligne] p1 : (0,0), p2 : (10,80), longueur : 80.62257748298549";
        String s4 = "[Ligne] p1 : (4,6), p2 : (84,96), longueur : 120.41594578792295";
        String s5 = "[Ligne] p1 : (70,245), p2 : (140,295), longueur : 86.02325267042627";
        assertEquals("Affichage attendu", s, ligne.toString());
        assertEquals("Affichage attendu", s1, ligne1.toString());
        assertEquals("Affichage attendu", s2, ligne2.toString());
        assertEquals("Affichage attendu", s3, ligne3.toString());
        assertEquals("Affichage attendu", s4, ligne4.toString());
        assertEquals("Affichage attendu", s5, ligne5.toString());
    }

}