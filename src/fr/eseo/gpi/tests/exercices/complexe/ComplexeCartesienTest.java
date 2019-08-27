/**
 * @author  Antoine du HAMEL
 * @section 1.7
 * @exercice 4
 */
package fr.eseo.gpi.tests.exercices.complexe;

import fr.eseo.gpi.exercices.complexe.cartesien.Complexe;

class ComplexeCartesienTest {
	public static void main(String[] args) {
		(new Complexe()).afficheInfos();

		Complexe [] liste = {
			new Complexe(false, 1,2),
			new Complexe(false, 0,2),
			new Complexe(false, -5,0),
			new Complexe(true, 5,0),
			new Complexe(true, 5,3),
			new Complexe(true, 0,10)
		};

		for (Complexe Z : liste) {
			Z.afficheInfos();
			System.out.println("");
		}
	}
}