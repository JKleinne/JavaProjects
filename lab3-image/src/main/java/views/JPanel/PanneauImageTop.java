/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: PanneauImageTop.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * DonatienFRemoved 2021-11-28 12:20 p.m. Removed Useless method in PanneauImageTop
 * aracanelli 2021-11-28 01:32 a.m. Fixed multiple instances of setEvents() during injection
 * aracanelli 2021-11-28 01:23 a.m. Undo function now works for both panels
 * aracanelli 2021-11-28 00:45 a.m. Implementation of undo function
 * aracanelli 2021-11-27 23:22 p.m. Enabled undo after first action
 * aracanelli 2021-11-27 22:52 p.m. Merge branch 'main' into rac-dev
 * aracanelli 2021-11-27 22:45 p.m. Implemented right click menu for undo and redo placeholders
 * JKleinne 2021-11-27 21:14 p.m. Refactored Visitor for Translate
 * aracanelli 2021-11-27 20:56 p.m. Implemented zoom and translate method on top panel
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 * JKleinne 2021-11-27 01:46 a.m. Observer pattern for Image/Perspective manipulation
 * JKleinne 2021-11-26 21:27 p.m. Fixed Translation's Visitor Pattern
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 * JKleinne 2021-11-26 16:59 p.m. Merge remote-tracking branch 'origin/main' into rac-dev
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package views.JPanel;

import controllers.commands.redo.RedoMediator;
import controllers.commands.redo.interfaces.IRedoElement;
import controllers.commands.redo.interfaces.IRedoVisitor;
import controllers.commands.translation.TranslationMediator;
import controllers.commands.translation.interfaces.ITranslateElement;
import controllers.commands.translation.interfaces.ITranslateVisitor;
import controllers.commands.undo.UndoMediator;
import controllers.commands.undo.interfaces.IUndoElement;
import controllers.commands.undo.interfaces.IUndoVisitor;
import controllers.commands.zoom.RedimensionnerMediator;
import controllers.commands.zoom.interfaces.IRedimensionnerElement;
import controllers.commands.zoom.interfaces.IRedimensionnerVisitor;
import controllers.interfaces.IMediator;
import controllers.interfaces.Injectable;
import controllers.interfaces.observer.IObserver;
import controllers.interfaces.observer.ISubject;
import models.Perspective;
import org.javatuples.Pair;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Création d'un panneau dans la partie supérieur de l'application pouvant contenir une image.
 * L'utilisateur peut modifier l'image au sein de ce panneau.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class PanneauImageTop extends JPanel implements Injectable, ITranslateElement, IRedimensionnerElement,
        IUndoElement, IRedoElement, IObserver {
    private static final long serialVersionUID = 1L;

    private Perspective mainPerspective;
    private MenuClicDroit menuClicDroit;

    private IMediator translationMediator;
    private IMediator zoomMediator;
    private IMediator undoMediator;
    private IMediator redoMediator;

    private Point onClickLocation;
    private Point imageLocation;
    private boolean mouseClickedOnImage = false;

    /**
     * Méthode de peinture personnalisée pour appeler la méthode de dessin personnalisée
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (mainPerspective != null) {
            Point p = mainPerspective.getCoordinates();
            int height = mainPerspective.getImage().getHeight();
            int width = mainPerspective.getImage().getWidth();
            BufferedImage i = mainPerspective.getBufferedImage();
            g.drawImage(i, p.x, p.y, width, height, null);
        }
    }

    /**
     * Méthode de translation pour déplacer l'image
     * @param p coordonnées à transposer
     */
    private void translate(Point p) {
        if (mainPerspective != null) {
            int x = p.x - onClickLocation.x;
            int y = p.y - onClickLocation.y;
            var newCoords = new Point(
                    imageLocation.x + x,
                    imageLocation.y + y
            );
            var payload = new Pair<Component, Point>(this, newCoords);

            translationMediator.notify(PanneauImageTop.this, payload);
        }
    }

    /**
     * Méthode de zoom pour le redimensionnement d'une image
     * @param i Positif ou négatif en fonction de la rotation de la molette de la souris
     */
    private void zoom(int i) {
        if(mainPerspective != null){
            int height = mainPerspective.getImage().getHeight();
            int width = mainPerspective.getImage().getWidth();
            int gcd = gcd(height, width);

            int heightIncrement = height/gcd;
            int widthIncrement = width/gcd;

            int newHeight = height + heightIncrement*i;
            int newWidth = width + widthIncrement*i;

            if (newWidth <= 0 || newHeight <= 0){
                newHeight = height;
                newWidth = width;
            };

            var newSize = new Pair<Integer, Integer>(newWidth, newHeight);
            var payload = new Pair<Component, Pair<Integer, Integer>>(this, newSize);

            zoomMediator.notify(PanneauImageTop.this, payload);
        }
    }

    /**
     * Méthode concrète d'injection
     * @param o Objet à injecter
     */
    @Override
    public void inject(Object o) {
        if(o instanceof Perspective) {
            mainPerspective = (Perspective) o;

            mainPerspective.attach(this);
            mainPerspective.getImage().attach(this);
        }
        else if(o instanceof TranslationMediator)
            this.translationMediator = (IMediator) o;
        else if(o instanceof RedimensionnerMediator)
            this.zoomMediator = (IMediator) o;
        else if(o instanceof UndoMediator)
            this.undoMediator = (IMediator) o;
        else if(o instanceof RedoMediator) {
            this.redoMediator = (IMediator) o;
            menuClicDroit = new MenuClicDroit(this.undoMediator, this.redoMediator, this);
            add(menuClicDroit);
            setEvents();
        }
    }

    /**
     * Ajout d'événements de souris pour pouvoir exécuter des commandes
     */
    public void setEvents(){
        addMouseListener(new MouseAdapter() {
            //TODO implement command function
            @Override
            public void mousePressed(MouseEvent e) {
                if(mainPerspective != null) {
                    if (isMouseOnImage(e)) {
                        if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                            showPopup(e);
                        } else {
                            mouseClickedOnImage = true;
                            mainPerspective.save();
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(mainPerspective != null) {
                    if(isMouseOnImage(e)) {
                        if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                            showPopup(e);
                        } else {
                            if(!menuClicDroit.getMenuUndo().isEnabled()) {
                                menuClicDroit.getMenuUndo().setEnabled(true);
                            }
                            if(menuClicDroit.getMenuRedo().isEnabled()) {
                                menuClicDroit.getMenuRedo().setEnabled(false);
                            }
                            mouseClickedOnImage = false;
                        }
                    }
                }
            }

            private void showPopup(MouseEvent e) {
                if(e.isPopupTrigger()) {
                    menuClicDroit.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(mainPerspective != null) {
                    var currentMouseLocation = e.getPoint();
                    if (mouseClickedOnImage) {
                        translate(currentMouseLocation);
                    }
                }
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(mainPerspective != null) {
                    if (isMouseOnImage(e)) {
                        zoom(e.getWheelRotation());
                    }
                }
            }
        });
    }

    /**
     * Méthode de mise à jour pour repeindre le panneau
     * @param subject null
     * @param payload null
     */
    @Override
    public void update(ISubject subject, Object payload) {
        repaint();
    }

    /**
     * Méthode pour calculer si la souris est sur l'image
     * @param e Coordonnées de la souris
     * @return vrai si la souris est sur l'image
     */
    private boolean isMouseOnImage(MouseEvent e) {
        onClickLocation = e.getPoint();
        imageLocation = mainPerspective.getCoordinates();
        int height = mainPerspective.getImage().getHeight();
        int width = mainPerspective.getImage().getWidth();

        var currentMouseLocation = e.getPoint();

        Rectangle image = new Rectangle();
        image.setBounds(mainPerspective.getCoordinates().x,mainPerspective.getCoordinates().y, width, height);

        if (image.contains(currentMouseLocation)){
            return true;
        } else {return false;}
    }

    /**
     * CODE EMPRUNTÉ:
     * Les lignes suivantes sont basées sur l'article sur la façon d'obtenir le plus grand diviseur commun
     * Nous l'utilisons pour mettre les images à l'échelle lors du redimensionnement.
     *
     *    Titre: Java: get greatest common divisor
     *    Date: 2021-11-26
     *    Disponible sur: https://stackoverflow.com/questions/4009198/java-get-greatest-common-divisor
     */
    private int gcd(int a, int b) { return b==0 ? a : gcd(b, a%b); }

    /**
     * La méthode d'acceptation concrète
     * @param v l'object de l'interface IUndoVisitor
     */
    @Override
    public void accept(IUndoVisitor v) {
        v.visit(mainPerspective);
    }

    /**
     * La méthode d'acceptation concrète
     * @param v l'object de l'interface IRedoVisitor
     */
    @Override
    public void accept(IRedoVisitor v) {
        v.visit(mainPerspective);
    }

    /**
     * La méthode d'acceptation concrète
     * @param v l'object de l'interface ITranslateVisitor
     * @param coordinates coordonnées à traduire
     */
    @Override
    public void accept(ITranslateVisitor v, Point coordinates) {
        v.visit(this.mainPerspective, coordinates);
    }

    /**
     * La méthode d'acceptation concrète
     * @param v l'object de l'interface IRedimensionnerVisitor
     * @param newDimensions Dimensions à régler
     */
    @Override
    public void accept(IRedimensionnerVisitor v, Pair<Integer, Integer> newDimensions) {
        v.visit(this.mainPerspective.getImage(), newDimensions);
    }

    /**
     * Le getter pour la perspective de l'image pour être en mesure d'enregistrer
     * @return la perspective de l'image
     */
    public Perspective getMainPerspective() {return mainPerspective;}
}
