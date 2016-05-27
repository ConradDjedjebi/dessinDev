package fr.eseo.gpi.beanartist.xml;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.List;

import fr.eseo.gpi.beanartist.modele.geom.*;
import fr.eseo.gpi.beanartist.vue.geom.VueTracé;
import org.w3c.dom.Element;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;

/**
 * Un enregistreur SVG est un processeur DOM responsable de l'enregistrement
 * d'un dessin au format SVG standard.
 * 
 * Il suit exactement les mêmes principes que ceux de l'enregistreur XML (classe
 * EnregistreurXML). Les méthodes enregistreDessin et créeElémentXxxx devront
 * être complétées. Des méthodes utilitaires pourront venir compléter celles
 * définies par la classe ProcesseurDOM ; elles devront dans ce cas être
 * OBLIGATOIREMENT définies en "private" à la fin de la classe EnregistreurXML.
 *
 */
public class EnregistreurSVG extends ProcesseurDOM {

	/**
	 * Lance le test d'enristrement (méthode teste) avec le fichier XML d'entrée
	 * nommé "S30-Dessin-in.xml" et le fichier SVG de sortie nommé
	 * "S30-Dessin-out.svg".
	 * 
	 * Vérifier la conformité du fichier de sortie en l'affichant avec un
	 * navigateur Web et en comparant cet affichage avec celui fourni par la
	 * méthode LecteurXML.main.
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		teste("S30-Dessin-in.xml", "S30-Dessin-out.svg");
	}

	/**
	 * Teste l'enregistrement du dessin dans un fichier SVG. Le fichier XML
	 * d'entrée est préalablement lu, puis sauvagardé dans un fichier de sortie
	 * au format SVG.
	 * 
	 * @param nomFichierEntrée le nom du fichier XML d'entrée lu
	 * @param nomFichierSortie le nom du fichier SVG de sortie écrit
	 * @throws FileNotFoundException si l'un des noms des fichiers n'est pas
	 *             valide
	 */
	public static void teste(String nomFichierEntrée, String nomFichierSortie) throws FileNotFoundException {
		LecteurXML lecteur = new LecteurXML();
		final List<VueForme> dessin = lecteur.lisDessin(nomFichierEntrée);
		EnregistreurSVG enregistreur = new EnregistreurSVG();
		enregistreur.enregistreDessin(nomFichierSortie, dessin);
	}

	/**
	 * Enregistre le dessin donné dans un fichier SVG.
	 * @param nomFichier le nom du fichier SVG de sauvegarde
	 * @param dessin le dessin formé de la liste des vues des formes
	 * @throws FileNotFoundException si le nom du fichier n'est pas valide
	 */
	public void enregistreDessin(String nomFichier, List<VueForme> dessin) throws FileNotFoundException {
		créeDocumentSVG();
		Element racine = getDocument().getDocumentElement();
		for (VueForme vueForme : dessin) racine.appendChild(créeElément(vueForme));
		enregistreDocument(nomFichier);
	}

	/**
	 * Crée un élément DOM au format SVG représentant la vue donnée d'une forme
	 * et retourne cet élément. Cette méthode invoque les méthodes
	 * créeElément<Forme> en fonction du type de la vue.
	 * @param vueForme la vue d'une forme
	 * @return l'élément DOM représentant la vue d'une forme
	 */
	public Element créeElément(VueForme vueForme) {
		Element élément = vueForme instanceof VueTracé ? créeElément((Tracé)vueForme.getForme()) : créeElément(vueForme.getForme());
		élément.setAttribute("filled", vueForme.estRempli() ? TRUE_VALUE : FALSE_VALUE);
		écrisAttribut(élément, "color", vueForme.getCouleurLigne().getRGB());

		return élément;
	}

	private Element créeElément(Forme forme) {
		Element élément;
		try {
			élément = getDocument().createElement((String) forme.getClass().getDeclaredField("XML_NAME").get(String.class));
			//Pourquoi ne pas faire un forme.getClasse.getShortName()?
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new Error("Vue non gérée", e);
		}

		écrisAttribut(élément, "x", forme.getX());
		écrisAttribut(élément, "y", forme.getY());
		écrisAttribut(élément, "width", forme.getLargeur());
		écrisAttribut(élément, "height", forme.getHauteur());

		return élément;
	}

	private Element créeElément(Tracé tracé) {
		Element element = créeElément((Forme)tracé);
		//Un element est crée pour ce type tracé. S'attendre à voir un x,y,w et h pour polyline avant de voir ceux de line
		for (Ligne ligne : tracé.getLignes()) {
			element.appendChild(créeElément(ligne));
		}
		return element;
	}

}
