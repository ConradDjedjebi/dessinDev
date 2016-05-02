/**
 * Class de test
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.beanartist.tests.modele.geom;

import fr.eseo.gpi.beanartist.modele.geom.Ellipse;
import fr.eseo.gpi.beanartist.modele.geom.Point;

/**
 * @author duhamean
 * @date 31/03/16
 * @project gpi_binome
 */
public class EllipseTest {
	public static void main(String[] args) {
		Point point = new Point(2,3);

		Ellipse ellipse1 = new Ellipse (point,75,21);
		Ellipse ellipse2 = new Ellipse (point,2,7);

		//System.out.println(ellipse1.toString());
		//System.out.println(ellipse2.toString());
	}
}
