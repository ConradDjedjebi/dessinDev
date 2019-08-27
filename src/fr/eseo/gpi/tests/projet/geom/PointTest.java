package fr.eseo.gpi.tests.projet.geom;
import fr.eseo.gpi.projet.geom.Point;



class PointTest {

	public static void main (String[]args){
	
		Point point1 = new Point (12,19);
		Point point2 = new Point ();


		point1.moveVect(4,5);
		point2.moveTo(4,2);

		System.out.println(point1.toString());
		System.out.println(point2.toString());


	}


}