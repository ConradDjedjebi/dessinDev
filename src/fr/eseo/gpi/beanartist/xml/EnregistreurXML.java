package fr.eseo.gpi.beanartist.xml;

import java.io.FileNotFoundException;
import java.util.List;

import fr.eseo.gpi.beanartist.modele.geom.*;
import fr.eseo.gpi.beanartist.vue.geom.VueTracé;
import org.w3c.dom.Element;

import fr.eseo.gpi.beanartist.vue.geom.VueForme;

/**
 * Un enregistreur XML est un processeur DOM responsable de l'enregistrement
 * d'un dessin au format XML défini par l'application.
 * 
 * Il utilise les méthodes héritées de la classe ProcesseurDOM pour créer un
 * document DOM et l'enregistrer dans un fichier XML, ainsi que les méthodes
 * d'écriture des entiers.
 * 
 * Les méthodes enregistreDessin et créeElémentXxxx devront être complétées. Des
 * méthodes utilitaires pourront venir compléter celles définies par la classe
 * ProcesseurDOM ; elles devront dans ce cas être OBLIGATOIREMENT définies en
 * "private" à la fin de la classe EnregistreurXML.
 *
 */
public class EnregistreurXML extends Enregistreur {

	/**
	 * Lance le test d'enristrement (méthode teste) avec le fichier XML d'entrée
	 * nommé "S30-Dessin-in.xml" et le fichier XML de sortie nommé
	 * "S30-Dessin-out.xml".
	 * 
	 * Ce test MANUEL doit OBLIGATOIREMENT passer avant de commencer la gestion
	 * de l'enregistrement en SVG (classe EnregistreurSVG). Il est INDISPENSABLE
	 * de vérifier la conformité entre les fichiers d'entrée et de sortie. Il
	 * peut subsister des différentces normales entre ces deux fichiers (par
	 * exemple pour les valeurs par défaut telles que la couleur de remplissage
	 * des vues). De plus, l'affichage du fichier de sortie doit être conforme à
	 * ce qui est attendu.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		teste("S30-Dessin-in.xml", "S30-Dessin-out.xml");
	}

	/**
	 * Teste l'enregistrement du dessin dans un fichier XML. Le fichier XML
	 * d'entrée est préalablement lu, puis sauvagardé dans un fichier de sortie.
	 * Le fichier de sortie est ensuite chargé et visualisé par l'application.
	 * 
	 * @param nomFichierEntrée le nom du fichier XML d'entrée lu
	 * @param nomFichierSortie le nom du fichier XML de sortie écrit puis
	 *            affiché
	 * @throws FileNotFoundException si l'un des noms des fichiers n'est pas
	 *             valide
	 */
	public static void teste(String nomFichierEntrée, String nomFichierSortie) throws FileNotFoundException {
		LecteurXML lecteur = new LecteurXML();
		final List<VueForme> dessin = lecteur.lisDessin(nomFichierEntrée);
		EnregistreurXML enregistreur = new EnregistreurXML();
		enregistreur.enregistreDessin(nomFichierSortie, dessin);
		LecteurXML.teste(nomFichierSortie);
	}

	/**
	 * Enregistre le dessin donné dans un fichier.
	 * @param nomFichier le nom du fichier de sauvegarde
	 * @param dessin le dessin formé de la liste des vues des formes
	 * @throws FileNotFoundException si le nom du fichier n'est pas valide
	 */
	public void enregistreDessin(String nomFichier, List<VueForme> dessin) throws FileNotFoundException {
		créeDocumentXML("dessin");
		Element racine = getDocument().getDocumentElement();

		for (VueForme vueForme : dessin) racine.appendChild(créeElément(vueForme));

		enregistreDocument(nomFichier);
	}

	/**
	 * Crée un élément DOM représentant la vue donnée d'une forme et retourne
	 * cet élément. Cette méthode invoque les méthodes créeElément(Forme) en
	 * fonction du type de la vue.
	 * @param vueForme la vue d'une forme
	 * @return l'élément DOM représentant la vue d'une forme
	 */
	public Element créeElément(VueForme vueForme) {
		Element élément = vueForme instanceof VueTracé ? créeElément((Tracé)vueForme.getForme()) : créeElément(vueForme.getForme());
		élément.setAttribute("filled", vueForme.estRempli() ? TRUE_VALUE : FALSE_VALUE);
		écrisAttribut(élément, "color", vueForme.getCouleurLigne().getRGB());

		return élément;
	}

	/**
	 * Crée un élément DOM représentant n'importe quelle forme et retourne
	 * cet élément.
	 * @param forme une forme
	 * @return l'élément DOM représentant la forme
	 */
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

	/**
	 * Crée un élément DOM représentant un tracé et retourne
	 * cet élément. Cette méthode invoque la méthode créeElément(Forme).
	 * @param tracé un objet Tracé
	 * @return l'élément DOM représentant le tracé
	 */
	private Element créeElément(Tracé tracé) {
		Element element = créeElément((Forme)tracé);
		//Un element est crée pour ce type tracé. S'attendre à voir un x,y,w et h pour polyline avant de voir ceux de line
		for (Ligne ligne : tracé.getLignes()) {
			element.appendChild(créeElément(ligne));
		}
		return element;
	}
}
