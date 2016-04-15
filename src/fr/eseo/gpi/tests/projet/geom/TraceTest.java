/**
 * Created by Djedjebi Elphege on 03/04/2016.
 */

package fr.eseo.gpi.tests.projet.geom;

import fr.eseo.gpi.projet.geom.Ligne;
import fr.eseo.gpi.projet.geom.Point;
import fr.eseo.gpi.projet.geom.Trace;

public class TraceTest {
    public static void main (String[]args){

        Point point1 = new Point (2,2);
        Point point2 = new Point (5,4);
        Point point3 = new Point (5,9);
        Point point4 = new Point (7,12);
        Point point5 = new Point (10,3);

        Point [] lesPoints = new Point[5];
        lesPoints[0] = point1;
        lesPoints[1] = point2;
        lesPoints[2] = point3;
        lesPoints[3] = point4;
        lesPoints[4] = point5;

        Trace trace = new Trace(point1, point2);

        for(int i=2; i<lesPoints.length; i++){
            trace.addLineTo(lesPoints[i]);
        }

        for(int i=0; i<lesPoints.length; i++){
            System.out.println(trace.getLignes().get(i).toString());
        }


    }
}
