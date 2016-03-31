/**
 * Class de test
 * @author Antoine du HAMEL
 * @project GPI
 */

package fr.eseo.gpi.beanartist.tests.modele.geom;


import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.modele.geom.Point;

public class CarréTest {

	public static void main (String[]args){

		Point point = new Point (2,3);

		Carré carre1 = new Carré (point,4);
		Carré carre2 = new Carré (point);

		System.out.println(carre1.toString());
		System.out.println(carre2.toString());

		carre1.setHauteur(10);
		System.out.println(carre1.toString());


	}
}