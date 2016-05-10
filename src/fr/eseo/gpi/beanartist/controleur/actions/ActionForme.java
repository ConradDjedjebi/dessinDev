package fr.eseo.gpi.beanartist.controleur.actions;

import fr.eseo.gpi.beanartist.controleur.outils.OutilCarré;
import fr.eseo.gpi.beanartist.controleur.outils.OutilCercle;
import fr.eseo.gpi.beanartist.controleur.outils.OutilEllipse;
import fr.eseo.gpi.beanartist.controleur.outils.OutilLigne;
import fr.eseo.gpi.beanartist.controleur.outils.OutilRectangle;
import fr.eseo.gpi.beanartist.controleur.outils.OutilTracé;
import fr.eseo.gpi.beanartist.vue.ui.FenêtreBeAnArtist;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.security.InvalidParameterException;

/**
 * @author duhamean
 * @date 03/05/16
 * @project gpi_binome
 */
public class ActionForme extends AbstractAction  {
    public static final String NOM_ACTION_RECTANGLE = "Créer un rectangle";
    public static final String NOM_ACTION_CARRÉ = "Créer un carré";
    public static final String NOM_ACTION_ELLIPSE = "Créer une ellipse";
    public static final String NOM_ACTION_CERCLE = "Créer un cercle";
    public static final String NOM_ACTION_LIGNE = "Créer une ligne";
    public static final String NOM_ACTION_TRACÉ = "Créer un tracé";

    public static final int RECTANGLE = 1;
    public static final int CARRÉ =     1<<1;
    public static final int ELLIPSE =   1<<2;
    public static final int CERCLE =    1<<3;
    public static final int LIGNE =     1<<4;
    public static final int TRACÉ =     1<<5;

    private final int forme;
    String actionCommand;
    private FenêtreBeAnArtist fenetre;

    public ActionForme (FenêtreBeAnArtist fenetre) {
        this(fenetre, RECTANGLE);
    }

    public ActionForme (FenêtreBeAnArtist fenetre, int typeAction) {
        switch (typeAction)
        {
            case RECTANGLE:
                actionCommand = NOM_ACTION_RECTANGLE;
                break;

            case CARRÉ:
                actionCommand = NOM_ACTION_CARRÉ;
                break;

            case ELLIPSE:
                actionCommand = NOM_ACTION_ELLIPSE;
                break;

            case CERCLE:
                actionCommand = NOM_ACTION_CERCLE;
                break;

            case LIGNE:
                actionCommand = NOM_ACTION_LIGNE;
                break;

            case TRACÉ:
                actionCommand = NOM_ACTION_TRACÉ;
                break;

            default:
                throw new InvalidParameterException();
        }
        putValue(javax.swing.Action.NAME, actionCommand);
        this.fenetre = fenetre;
        this.forme = typeAction;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (this.forme)
        {
            case RECTANGLE:
                new OutilRectangle(this.fenetre.getPanneauDessin());
                    break;

            case CARRÉ:
                new OutilCarré(this.fenetre.getPanneauDessin());
                break;

            case ELLIPSE:
                new OutilEllipse(this.fenetre.getPanneauDessin());
                break;

            case CERCLE:
                new OutilCercle(this.fenetre.getPanneauDessin());
                break;

            case LIGNE:
                new OutilLigne(this.fenetre.getPanneauDessin());
                break;

            case TRACÉ:
                new OutilTracé(this.fenetre.getPanneauDessin());
                break;

            default:
        }
    }
}
