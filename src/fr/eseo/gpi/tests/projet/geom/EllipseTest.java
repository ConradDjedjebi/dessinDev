package fr.eseo.gpi.tests.projet.geom;
import fr.eseo.gpi.projet.geom.*;



class EllipseTest {

	public static void main (String[]args){

		Point point = new Point (2,3);

		Ellipse ellipse1 = new Ellipse (point,4,2);
		Ellipse ellipse2 = new Ellipse (point,2,7);

		System.out.println(ellipse1.toString());
		System.out.println(ellipse2.toString());

	}
}