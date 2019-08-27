package fr.eseo.gpi.tests.projet.geom;
import fr.eseo.gpi.projet.geom.*;



class CercleTest {

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