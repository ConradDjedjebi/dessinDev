package fr.eseo.gpi.beanartist.controleur.outils;

import fr.eseo.gpi.beanartist.modele.geom.Forme;
import fr.eseo.gpi.beanartist.vue.geom.VueForme;
import fr.eseo.gpi.beanartist.vue.geom.VueEllipse;
import fr.eseo.gpi.beanartist.vue.ui.PanneauDessin;
import fr.eseo.gpi.beanartist.modele.geom.Ellipse;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;

/**
 * @author Elphege
 * @date 20/05/2016
 * @project gpi_binome
 */
public class OutilGomme extends Outil {
    private VueForme gomme;
    private boolean premierClick = true;
    /**
     * Initialise un déplacement
     * @param panneauDessin
     */
    public OutilGomme(PanneauDessin panneauDessin) {
        super(panneauDessin);
    }

    @Override

    public void mouseClicked(MouseEvent e){
        if(premierClick ==true) {
            super.mouseClicked(e);
            gomme = new VueEllipse(new Ellipse(getDébut().getX(), getDébut().getY(), 130, 60), true);
            gomme.setCouleurLigne(new Color(-6737152));
            gomme.setRempli(true);
            getPanneauDessin().setGomme(gomme);
            getPanneauDessin().repaint();
            premierClick = false;
        }
        else{
            libérer(true);
        }
    }

    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);
        setFin(getDébut());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            if(!gommeSelected())
                throw new Error("La gomme n'est pas prise");
            setDébut(getFin());
            super.mouseDragged(e);
            this.gomme.getForme().déplacerDe(getFin().getX()-getDébut().getX(), getFin().getY() - getDébut().getY());
            effacer();
            this.getPanneauDessin().repaint();
        } catch (NullPointerException | Error excpetion) {
        }
    }

    // Vérifier que le point début détecté par mouse Pressed est bien sur la gomme.
    public boolean gommeSelected(){
        boolean resp = false;
        if(gomme.getForme().contient(getDébut())){
            resp = true;
        }
        return resp;
    }

    //La méthode qui s'occupe de supprimer les formes touchées
    //Prochaines étape : effacer les points de la forme touchée, étape par étape.

    public void effacer(){
        try {
            for (VueForme vueForme : getPanneauDessin().getVueFormes()) {
                //Les points à prendre en compte, les points du périmètre de la gomme
                int xMin = gomme.getForme().getMinX();
                int xMax = gomme.getForme().getMaxX();
                int yMin = gomme.getForme().getMinY();
                int yMax = gomme.getForme().getMaxY();
                for (int x = xMin - 1; x < xMax + 1; x++) {
                    for (int y = yMin - 1; y < yMax + 1; y++) {
                        if (gomme.getForme().contient(x, y) && vueForme.getForme().contient(x, y)) {
                            getPanneauDessin().getVueFormes().remove(vueForme);
                        }
                    }
                }
            }
        }catch(ConcurrentModificationException exception){
            //Je ne sais pas ce que c'est que cette erreur
        }
    }
}
