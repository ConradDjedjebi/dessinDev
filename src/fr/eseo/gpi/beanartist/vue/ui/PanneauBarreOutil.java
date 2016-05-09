package fr.eseo.gpi.beanartist.vue.ui;

import fr.eseo.gpi.beanartist.controleur.actions.ActionEffacer;
import fr.eseo.gpi.beanartist.controleur.actions.ActionForme;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author duhamean
 * @date 03/05/16
 * @project gpi_binome
 */
public class PanneauBarreOutil extends javax.swing.JPanel {
    protected static final int LARGEUR_PAR_DÉFAUT = 255;
    protected static final int HAUTEUR_PAR_DÉFAUT = PanneauDessin.HAUTEUR_PAR_DÉFAUT;
    protected static final Color COULEUR_FOND = Color.GRAY;

    private FenêtreBeAnArtist fenêtre;

    public PanneauBarreOutil(FenêtreBeAnArtist fenêtre) {
        this.fenêtre = fenêtre;
        this.setPreferredSize(new Dimension(LARGEUR_PAR_DÉFAUT, HAUTEUR_PAR_DÉFAUT));
        this.setBackground(COULEUR_FOND);
        this.initComponents();
    }

    private void initComponents() {
        JButton clearAll = new JButton(new ActionEffacer(this.getFenêtre()));
        JButton [] createFormes = {
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.RECTANGLE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.CARRÉ)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.ELLIPSE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.CERCLE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.LIGNE)),
                new JButton(new ActionForme(this.getFenêtre(), ActionForme.TRACÉ))
        };

        this.add(clearAll);
        for (JButton jB :
                createFormes) {
            this.add(jB);
        }
    }

    public FenêtreBeAnArtist getFenêtre() {
        return fenêtre;
    }

    public void setFenêtre(FenêtreBeAnArtist fenêtreBeAnArtist) {
        this.fenêtre = fenêtreBeAnArtist;
    }
}
