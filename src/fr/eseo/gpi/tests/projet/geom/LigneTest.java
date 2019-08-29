package fr.eseo.gpi.tests.projet.geom;
import fr.eseo.gpi.projet.geom.*;



class LigneTest {

	public static void main (String[]args){

		Point point = new Point (2,3);

		Ligne ligne1 = new Ligne (point,4,3);
		Ligne ligne2 = new Ligne (point);
		


		System.out.println(ligne1.toString());
		System.out.println(ligne2.toString());

		ligne2.setP2(point);

		System.out.println(ligne2.toString());


	}
}