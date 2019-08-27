package fr.eseo.gpi.tests.projet.geom;
import fr.eseo.gpi.projet.geom.*;



class CarreTest {

	public static void main (String[]args){

		Point point = new Point (2,3);

		Carre carre1 = new Carre (point,4);
		Carre carre2 = new Carre (point);

		System.out.println(carre1.toString());
		System.out.println(carre2.toString());

		carre1.setHauteur(10);
		System.out.println(carre1.toString());


	}
}