/**
 * @author Antoine du HAMEL
 * @project GPI
 */
package fr.eseo.gpi.projet.geom;

public class Point {

	int x; int y;


	/**
	* Constructeur de la classe Point (coordonnées connues)
	* @param : x, y les coordonnées du point
	**/
	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}

	/**
	* Constructeur de la classe Point (coordonnées par défaut)
	**/
	public Point (){
		this.x=0;
		this.y=0;
	}


	//------------- ACCESSEURS -------------//

	/**
	* Renvoie la coordonné x actuelle du point
	* @return : abcisse (int)
	**/
	public int getX(){
		return this.x;
	}

	/**
	* Renvoie la coordonné y actuelle du point
	* @return : ordonné (int)
	**/
	public int getY(){
		return this.y;
	}
	

	public void setX(int newPos){
		this.x = newPos;
	}

	/**
	* Renvoie la coordonné y actuelle du point
	* @return : ordonné (int)
	**/
	public void setY(int newPos){
		this.y = newPos;
	}


	/**
	* Permet de déplacer le point
	* @param : x, y les nouvelles coordonnées du point
	**/
	public void moveTo(int x, int y){
		this.x=x;
		this.y=y;
	}

	/**
	* Permet de déplacer le point selon un vecteur
	* @param : deltaX, deltaY
	**/
	public void moveVect(int deltaX, int deltaY){
		this.x+=deltaX;
		this.y+=deltaY;
	}


	//------------- AFFICHAGE -------------//

	/**
	* Renvoie en String les coordonnés actuelles du point
	* @return : message (String)
	**/
	public String toString(){
		return "Coordonnées - x : "+x+" - y : "+y;
	}


}