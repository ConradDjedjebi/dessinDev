/**
 * @author Antoine du HAMEL
 * @section 1.7
 * @exercice 2
 */
package fr.eseo.gpi.tests.exercices.pile;
import fr.eseo.gpi.exercices.pile.PileDeRectangles;
import fr.eseo.gpi.projet.geom.*;

class PileDeRectanglesTest{

	public static void main (String[]args){

		PileDeRectangles pile = new PileDeRectangles ();
		// Rectangle [] rectangles = new Rectangle[5];

		for (int i=1; i<=5; ++i) {
		 	// rectangles[i-1] = ;
		 	pile.add(new Rectangle (new Point(i,i), 100*i,10*i));
		 }

		while (!pile.isEmpty())
			System.out.println(pile.pop().toString());

	}
	
}