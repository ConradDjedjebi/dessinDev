package fr.eseo.gpi.beanartist.xml;

import java.io.FileNotFoundException;
import java.util.List;

import fr.eseo.gpi.beanartist.modele.geom.*;
import fr.eseo.gpi.beanartist.vue.geom.*;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import org.w3c.dom.Element;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Un enregistreur SVG est un processeur DOM responsable de l'enregistrement
 * d'un dessin au format SVG standard.
 * 
 * Il suit exactement les mêmes principes que ceux de l'enregistreur XML (classe
 * EnregistreurXML). Les méthodes enregistreDessin et créeElémentXxxx devront
 * être complétées. Des méthodes utilitaires pourront venir compléter celles
 * définies par la classe ProcesseurDOM ; elles devront dans ce cas être
 * OBLIGATOIREMENT définies en "private" à la fin de la classe EnregistreurSVG.
 *
 */
public class EnregistreurSVG extends Enregistreur {

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
	 * Obtenir le filtre d'extension pour cet enregistreur
	 * @return Le filtre d'extension ad hoc
     */
	public static FileNameExtensionFilter getExtensionFilter() {
		return new FileNameExtensionFilter("Image SVG", "svg");
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
		écrisAttribut(racine, "height", PanneauDessin.HAUTEUR_PAR_DÉFAUT);
		écrisAttribut(racine, "width", PanneauDessin.LARGEUR_PAR_DÉFAUT);

		for (VueForme vueForme : dessin) racine.appendChild(créeElément(vueForme));

		enregistreDocument(nomFichier);
	}

	/**
	 * Crée un élément DOM au format SVG représentant la vue donnée d'une forme
	 * et retourne cet élément. Cette méthode invoque les méthodes
	 * créeElément(<Forme>) en fonction du type de la vue.
	 * @param vueForme la vue d'une forme
	 * @return l'élément DOM représentant la vue d'une forme
	 */
	public Element créeElément(VueForme vueForme) {
		Element élément;
		if(vueForme instanceof VueTracé) {
			vueForme.setRempli(false);
			élément = créeElément((Tracé) vueForme.getForme());
		}
		else if (vueForme instanceof VueLigne)
		{
			vueForme.setRempli(false);
			élément = créeElément((Ligne)vueForme.getForme());
		}
		else if (vueForme instanceof VueCercle)
			élément = créeElément((Cercle)vueForme.getForme());
		else if (vueForme instanceof VueEllipse)
		élément = créeElément((Ellipse)vueForme.getForme());
		else if (vueForme instanceof VueRectangle)
			élément = créeElément((Rectangle)vueForme.getForme());
		else
			throw new Error("Vue non gérée");

		String hexColor = "#" + Integer.toHexString(vueForme.getCouleurLigne().getRGB()).substring(2);
		if(vueForme.estRempli()) {
			élément.setAttribute("fill", hexColor);
			élément.setAttribute("stroke", EMPTY_VALUE);
		}
		else {
			élément.setAttribute("stroke", hexColor);
			élément.setAttribute("fill", EMPTY_VALUE);
		}

		return élément;
	}

    /**
     * Crée un élément DOM au format SVG représentant un rectangle ou un carré.
     * @param forme un objet Rectangle ou Carré
     * @return l'élément DOM représentant la vue d'un rectangle
     */
	private Element créeElément(Rectangle forme) {
		Element élément = getDocument().createElement(Rectangle.XML_NAME);

		écrisAttribut(élément, "x", forme.getMinX());
		écrisAttribut(élément, "y", forme.getMinY());
		écrisAttribut(élément, "width", forme.getLargeur());
		écrisAttribut(élément, "height", forme.getHauteur());

		return élément;
	}

    /**
     * Crée un élément DOM au format SVG représentant une ellipse.
     * @param ellipse un objet Ellipse
     * @return l'élément DOM représentant la vue d'une ellipse
     */
	private Element créeElément(Ellipse ellipse) {
		Element element = getDocument().createElement(Ellipse.XML_NAME);

		écrisAttribut(element, "cx", (ellipse.getMaxX()+ellipse.getMinX())/2);
		écrisAttribut(element, "cy", (ellipse.getMaxY()+ellipse.getMinY())/2);
		écrisAttribut(element, "rx", (ellipse.getMaxX()-ellipse.getMinX())/2);
		écrisAttribut(element, "ry", (ellipse.getMaxY()-ellipse.getMinY())/2);

		return element;
	}

    /**
     * Crée un élément DOM au format SVG représentant un cercle.
     * @param cercle un objet Cercle
     * @return l'élément DOM représentant la vue d'un cercle
     */
	private Element créeElément(Cercle cercle) {
		Element element = getDocument().createElement(Cercle.XML_NAME);

		écrisAttribut(element, "cx", (cercle.getMaxX()+cercle.getMinX())/2);
		écrisAttribut(element, "cy", (cercle.getMaxY()+cercle.getMinY())/2);
		écrisAttribut(element, "r", cercle.getHauteur()/2);

		return element;
	}

    /**
     * Crée un élément DOM au format SVG représentant une ligne.
     * @param ligne un objet Ligne
     * @return l'élément DOM représentant la vue d'une ligne
     */
	private Element créeElément(Ligne ligne) {
		Element element = getDocument().createElement(Ligne.XML_NAME);

		écrisAttribut(element, "x1", ligne.getP1().getX());
		écrisAttribut(element, "x2", ligne.getP2().getX());
		écrisAttribut(element, "y1", ligne.getP1().getY());
		écrisAttribut(element, "y2", ligne.getP2().getY());

		return element;
	}

    /**
     * Crée un élément DOM au format SVG représentant un tracé.
     * @param tracé un objet Tracé
     * @return l'élément DOM représentant la vue d'un tracé
     */
	private Element créeElément(Tracé tracé) {
		Element element = getDocument().createElement(Tracé.XML_NAME);
		char coordinatesSeparator = ',', pointsSeparator = ' ';
		String format = String.format("%%d%c%%d%c", coordinatesSeparator, pointsSeparator);

		Point firstPoint = tracé.getLignes().get(0).getP1();
		String points = String.format(format, firstPoint.getX(), firstPoint.getY());
		for (Ligne ligne : tracé.getLignes()) {
			points += String.format(format, ligne.getP2().getX(), ligne.getP2().getY());
		}

		element.setAttribute("points", points);
		return element;
	}
}
