/**
 * Class de test
 * @author Antoine du HAMEL
 * @project GPI
 */

package	fr.eseo.gpi.beanartist.tests.modele.geom;

import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.modele.geom.Rectangle;

public class RectangleTest {

	public static void main (String[]args){
	
		Point p1 = new Point(1,1);

		Rectangle r1 = new Rectangle (p1,2,3);
		Rectangle r2 = new Rectangle (4,8);
		Rectangle r3 = new Rectangle (p1);
		Rectangle r4 = new Rectangle ();

		System.out.println(r1.toString());
		System.out.println(r2.toString());
		System.out.println(r3.toString());
		System.out.println(r4.toString());

		System.out.println("Air du r1 :"+r1.aire());
		System.out.println("Périmètre du r1 : "+r1.périmètre());


	}


}