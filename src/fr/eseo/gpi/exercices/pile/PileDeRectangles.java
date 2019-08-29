/**
 * @author Antoine du HAMEL
 * @section 1.7
 * @exercice 2
 */
package fr.eseo.gpi.exercices.pile;
import fr.eseo.gpi.projet.geom.Rectangle;


/**
* La classe PileDeRectangles hérite de la classe ArrayList et de toutes ses méthodes qu'il est
* alors inutile de redéfinir (add, remove, size, isEmpty...)
**/
public class PileDeRectangles extends java.util.ArrayList<Rectangle>{

	/**
	 * @return int Retourne l'index du dernier élément inséré
	 */
	int getLastIndex(){
		return this.size()-1;
	}

	/**
	 * @return Rectangle Retourne le rectangle en haut de la pile
	 */
	public Rectangle pop (){
		try {
			Rectangle ans = this.get(getLastIndex());
			this.remove(getLastIndex());
			return ans;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

}