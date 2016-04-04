package fr.eseo.gpi.tests.projet.geom;
/**
 * Created by Elphege on 04/04/2016.
 */

import fr.eseo.gpi.projet.geom.Point;
import fr.eseo.gpi.projet.geom.Trace;
import fr.eseo.gpi.projet.geom.FormeComposee;
import fr.eseo.gpi.projet.geom.Rectangle;

public class FormeComposeeTest {
    public static void main (String[]args){

        Rectangle rectangle1 = new Rectangle ();
        Rectangle rectangle2 = new Rectangle ();
        Rectangle rectangle3 = new Rectangle ();
        Rectangle rectangle4 = new Rectangle ();
        Rectangle rectangle5 = new Rectangle ();


        Rectangle [] lesRectangles = new Rectangle[5];
        lesRectangles[0] = rectangle1;
        lesRectangles[1] = rectangle2;
        lesRectangles[2] = rectangle3;
        lesRectangles[3] = rectangle4;
        lesRectangles[4] = rectangle5;

        FormeComposee formeComposee = new FormeComposee(lesRectangles);


        System.out.println(formeComposee.toString());
    }
}
