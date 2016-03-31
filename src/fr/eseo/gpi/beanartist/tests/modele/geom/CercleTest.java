/**
 * Class de test
 * @author Antoine du HAMEL
 * @project GPI
 */

package fr.eseo.gpi.beanartist.tests.modele.geom;

import fr.eseo.gpi.beanartist.modele.geom.Cercle;
import fr.eseo.gpi.beanartist.modele.geom.Point;

/**
 * @author duhamean
 * @date 31/03/16
 * @project gpi_binome
 */
public class CercleTest {
    public static void main (String[]args){

        Point point = new Point (2,3);

        Cercle cercle1 = new Cercle (point,4);
        Cercle cercle2 = new Cercle (point);

        System.out.println(cercle1.toString());
        System.out.println(cercle2.toString());
        cercle1.setHauteur(10);
        System.out.println(cercle1.toString());

    }
}
