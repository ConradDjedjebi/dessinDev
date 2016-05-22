package fr.eseo.gpi.beanartist.xml;
/**
 * @author Antoine & Elphege
 * @date 22/05/16
 * @project gpi_binome
 */

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.eseo.gpi.beanartist.modele.geom.Carré;
import fr.eseo.gpi.beanartist.modele.geom.Cercle;
import fr.eseo.gpi.beanartist.modele.geom.Ellipse;
import fr.eseo.gpi.beanartist.modele.geom.Ligne;
import fr.eseo.gpi.beanartist.modele.geom.Point;
import fr.eseo.gpi.beanartist.modele.geom.Rectangle;
import fr.eseo.gpi.beanartist.modele.geom.Tracé;
import fr.eseo.gpi.beanartist.vue.geom.VueCarré;
import fr.eseo.gpi.beanartist.vue.geom.VueCercle;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueLigne;
import fr.eseo.gpi.beanartist.vue.geom.VueRectangle;
import fr.eseo.gpi.beanartist.vue.geom.VueTracé;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

/**
 * Un lecteur XML est un processeur DOM responsable du chargement d'un dessin au
 * format XML défini par l'application.
 * 
 * Il utilise les méthodes héritées de la classe ProcesseurDOM pour charger le
 * fichier XML dans un document DOM, ainsi que les méthodes de lecture des
 * entiers.
 * 
 * Les méthodes lisDessin et créeXxxx devront être complétées. Des méthodes
 * utilitaires pourront venir compléter celles définies par la classe
 * ProcesseurDOM ; elles devront dans ce cas être OBLIGATOIREMENT définies en
 * "private" à la fin de la classe LecteurXML.
 * 
 */
public class LecteurXML extends ProcesseurDOM {

	/**
	 * Lance le test de chargement (méthode teste) avec le fichier XML nommé
	 * "S30-Dessin-in.xml".
	 * 
	 * Vous aurez pris soin de le copier prélablablement dans le ficher
	 * "S30-Dessin-in.xml".
	 * 
	 * Ce test MANUEL doit OBLIGATOIREMENT passer avant de commencer la gestion
	 * de l'enregistrement en XML (classe EnregistreurXML).
	 */
	public static void main(String[] args) throws FileNotFoundException {
		teste("savedFile");
	}

	/**
	 * Teste le chargement du fichier XML. Le contenu du fichier est ensuite
	 * affiché dans la fenêtre de l'application (classe FenêtreBeAnArtist).
	 * @param nomFichier le fichier d'entrée à lire
	 * @throws FileNotFoundException si le fichier n'existe pas
	 */
	public static void teste(String nomFichier) throws FileNotFoundException {
		LecteurXML lecteur = new LecteurXML();
		final List<VueForme> dessin = lecteur.lisDessin(nomFichier);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FenêtreBeAnArtist fenêtre = new FenêtreBeAnArtist();
				for (VueForme vueForme : dessin) {
					fenêtre.getPanneauDessin().ajouterVueForme(vueForme);
				}
			}
		});
	}

	/**
	 * Charge le fichier XML donné dans un document DOM puis renvoie
	 * l'intégralité du dessin sous la forme d'une liste de vues représentant
	 * les formes stockées dans le fichier.
	 * 
	 * @param nomFichier le nom du fichier XML
	 * @return l'intégralité du dessin sous la forme d'une liste de vues
	 * @throws FileNotFoundException si le fichier n'est pas trouvé ou
	 *             accessible
	 */
	public List<VueForme> lisDessin(String nomFichier) throws FileNotFoundException {
		List<VueForme> dessin = new ArrayList<>();
		chargeDocument(nomFichier);
		Element racine = getDocument().getDocumentElement();
		NodeList childNodes = racine.getChildNodes();
		VueForme vueForme;
		for (int i = 0, length = childNodes.getLength(); i < length; i++) {
			if (childNodes.item(i).getNodeType()==Node.ELEMENT_NODE) {
				vueForme = créeVueForme((Element) childNodes.item(i));
				if (vueForme!=null)
					dessin.add(vueForme);
			}
		}
		return dessin;
	}

	/**
	 * Crée une forme et sa vue associée réprésentées par l'élément DOM donné,
	 * puis renvoie cette vue. Cette méthode invoque les méthodes crée<Forme>
	 * définies pour chacune des <Forme> considérée.
	 * @param element l'élément représentant la vue et sa forme
	 * @return la vue stockée dans l'élément considéré
	 */
	private VueForme créeVueForme(Element element) {
		VueForme vue;
		String nom = element.getNodeName();
		boolean rempli = element.hasAttribute("filled") && element.getAttribute("filled").equals(TRUE_VALUE);
		Color couleur = element.hasAttribute("color") ? new Color(lisAttribut(element, "color")) : PanneauDessin.COULEUR_LIGNE_PAR_DÉFAUT;
		switch (element.getNodeName()) {
			case Rectangle.XML_NAME:
				vue = new VueRectangle(créeRectangle(element), couleur, rempli);
			break;
			case Carré.XML_NAME:
				vue = new VueCarré(créeCarré(element), couleur, rempli);
			break;
			case Ellipse.XML_NAME:
				vue = new VueEllipse(créeEllipse(element), couleur, rempli);
			break;
			case Cercle.XML_NAME:
				vue = new VueCercle(créeCercle(element), couleur, rempli);
			break;
			case Ligne.XML_NAME:
				vue = new VueLigne(créeLigne(element), couleur);
			break;
			case Tracé.XML_NAME:
				vue = new VueTracé(créeTracé(element), couleur);
			break;

			default:
				vue = null;
				break;
		}
		return vue;
	}

	/**
	 * Renvoie un nouveau rectangle représenté par l'élément DOM donné.
	 * @param element l'élément représentant le rectangle
	 * @return le rectangle stocké dans l'élément considéré
	 */
	private Rectangle créeRectangle(Element element) {
		return new Rectangle(lisAttribut(element, "x"), lisAttribut(element, "y"), lisAttribut(element, "width"), lisAttribut(element, "height"));
	}

	/**
	 * Renvoie un nouveau carré représenté par l'élément DOM donné.
	 * @param element l'élément représentant le carré
	 * @return le carré stocké dans l'élément considéré
	 */
	private Carré créeCarré(Element element) {
		return new Carré(lisAttribut(element, "x"), lisAttribut(element, "y"), lisAttribut(element, "width"));
	}

	/**
	 * Renvoie une nouvelle ellipse représentée par l'élément DOM donné.
	 * @param element l'élément représentant l'ellipse
	 * @return l'ellipse stockée dans l'élément considéré
	 */
	private Ellipse créeEllipse(Element element) {
		return new Ellipse(lisAttribut(element, "x"), lisAttribut(element, "y"), lisAttribut(element, "width"), lisAttribut(element, "height"));
	}

	/**
	 * Renvoie un nouveau cercle représenté par l'élément DOM donné.
	 * @param element l'élément représentant le cercle
	 * @return le cercle stocké dans l'élément considéré
	 */
	private Cercle créeCercle(Element element) {
		return new Cercle(lisAttribut(element, "x"), lisAttribut(element, "y"), lisAttribut(element, "width"));
	}

	/**
	 * Renvoie la nouvelle ligne représentée par l'élément DOM donné.
	 * @param element l'élément représentant la ligne
	 * @return la ligne stockée dans l'élément considéré
	 */
	private Ligne créeLigne(Element element) {
		return new Ligne(lisAttribut(element, "x"), lisAttribut(element, "y"), lisAttribut(element, "width"), lisAttribut(element, "height"));
	}

	/**
	 * Renvoie un nouveau tracé représenté par l'élément DOM donné.
	 * @param element l'élément représentant le tracé
	 * @return le tracé stocké dans l'élément considéré
	 */
	private Tracé créeTracé(Element element) {
		// création de la liste des points du tracé
		List<Ligne> lignes= new ArrayList<>();

		NodeList childNodes = element.getChildNodes();

		for (int i = 0, length = childNodes.getLength(); i < length; i++) {
			if (childNodes.item(i).getNodeType()==Node.ELEMENT_NODE && childNodes.item(i).getNodeName().equals(Ligne.XML_NAME))
				lignes.add(créeLigne((Element) childNodes.item(i)));
		}
		// création des lignes formant le tracé
		return new Tracé(lignes);
	}

}
