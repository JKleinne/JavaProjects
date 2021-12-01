/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: PanneauIcone.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 21:18 p.m. Removed icon implementation of ITranslateElement
 * JKleinne 2021-11-27 21:14 p.m. Refactored Visitor for Translate
 * JKleinne 2021-11-26 21:27 p.m. Fixed Translation's Visitor Pattern
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package views.JPanel;

import controllers.interfaces.Injectable;
import models.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Création de l'icon contenant l'image utilisée (Cette image ne peut pas être modifiée).
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class PanneauIcone extends JPanel implements Injectable {
    private static final long serialVersionUID = 1L;

    private Perspective iconPerspective;

    private static final Color GRIS = new Color(180,180,180);

    /**
     * Contructeur PanneauIcon pour définir l'arrière-plan et la taille minimale.
     */
    public PanneauIcone(){
        setBackground(GRIS);
        Dimension d = new Dimension(200,200);
        setMinimumSize(d);
    }

    /**
     * Méthode de peinture personnalisée pour appeler la méthode de dessin personnalisée
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);

        if(iconPerspective != null) {
            BufferedImage i = iconPerspective.getBufferedImage();
            drawImage(i, this, g);
        }

    }

    /**
     * CODE EMPRUNTÉ:
     * Les lignes suivantes sont basées sur l'article par Nam Ha Minh sur la façon de dessiner une image
     * avec une mise à l'échelle automatique .
     *
     *    Titre: How to draw image with automatic scaling in Java
     *    Auteur: Nam Ha Minh
     *    Date: 2021-11-26
     *    Disponible sur: https://www.codejava.net/java-se/graphics/drawing-an-image-with-automatic-scaling
     */
    public static void drawImage(BufferedImage image, JPanel j, Graphics g){
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double imageAspect = (double) imageHeight/imageWidth;

        int panelWidth = j.getWidth();
        int panelHeight = j.getHeight();

        double panelAspect = (double) panelHeight/panelWidth;

        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;

        if(imageWidth<panelWidth && imageHeight<panelHeight){
            x1 = (panelWidth-imageWidth)/2;
            y1 = (panelHeight-imageHeight)/2;
            x2 = imageWidth + x1;
            y2 = imageHeight = y1;
        } else {
            if(panelAspect>imageAspect){
                y1 = panelHeight;
                panelHeight = (int) (panelWidth*imageAspect);
                y1 = (y1-panelHeight)/2;
            } else {
                x1 = panelWidth;
                panelWidth = (int) (panelHeight*imageAspect);
                x1 = (x1-panelWidth)/2;
            }
            x2 = panelWidth + x1;
            y2 = panelHeight + y1;
        }
        g.drawImage(image, x1, y1, x2, y2, 0, 0, imageWidth, imageHeight, null);
    }

    /**
     * Méthode concrète d'injection
     * @param o Objet à injecter
     */
    @Override
    public void inject(Object o) {
        if(o instanceof Perspective)
            iconPerspective = (Perspective) o;
    }

    /**
     * Le getter pour la perspective de l'image pour être en mesure d'enregistrer
     * @return la perspective de l'image
     */
    public Perspective getIconPerspective() {return iconPerspective;}
}
