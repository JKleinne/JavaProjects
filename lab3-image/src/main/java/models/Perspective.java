/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Perspective.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-27 22:00 p.m. Implememted Zoom ICommand, IVisitor, IMediator
 * aracanelli 2021-11-27 19:18 p.m. Implemented translate method on bottom panel
 * JKleinne 2021-11-27 18:41 p.m. Code cleanup
 * JKleinne 2021-11-27 18:29 p.m. Implemented Zoom ICommand, IMediator and IVisitor
 * JKleinne 2021-11-27 16:24 p.m. Cloneable -> top/bottom panels not linked
 * JKleinne 2021-11-27 01:46 a.m. Observer pattern for Image/Perspective manipulation
 * JKleinne 2021-11-26 17:16 p.m. Fixed class name typos
 * JKleinne 2021-11-26 16:34 p.m. hashcode: (Java 7+) -> Google Guava
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package models;

import com.google.common.base.Objects;
import controllers.commands.enregistrement.interfaces.ISaveElement;
import controllers.commands.enregistrement.interfaces.ISaveVisitor;
import controllers.interfaces.observer.IObserver;
import controllers.interfaces.observer.ISubject;
import controllers.snapshots.History;
import controllers.snapshots.PerspectiveSnapshot;
import controllers.snapshots.interfaces.Memento;
import controllers.snapshots.interfaces.Originator;
import utilities.enums.EventType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static utilities.enums.EventType.IMAGE_MOVED;

/**
 * Classe permettant de manipuler différentes variables d'une image
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class Perspective implements Originator, ISubject, Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private Image image;
    private ArrayList<IObserver> observers = new ArrayList<>();
    private Point coordinates = new Point(0, 0);

    private History history;

    /**
     * Constructeur pour créer une nouvelle image et définir l'historique.
     * @param imagePath Chemin de l'image
     * @throws IOException
     */
    public Perspective(String imagePath) throws IOException {
        this.image = new Image(
                ImageIO.read(new File(imagePath))
        );

        history = new History();
    }

    /**
     * Renvoie les coordonnées actuelles de l'image
     * @return les coordonnées
     */
    public Point getCoordinates() {
        return this.coordinates;
    }

    /**
     * Définir les coordonnées et notifier les observateurs d'une image déplacée
     * @param coordinates Coordonnées à définir
     */
    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
        notifyObservers(IMAGE_MOVED, null);
    }

    /**
     * Retourne l'image
     * @return l'image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Définit l'image
     * @param image Image à définir
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Accès direct à l'image en mémoire tampon
     * @return l'image en mémoire tampon
     */
    public BufferedImage getBufferedImage() {
        if(image != null)
            return image.getSrcImg();

        return null;
    }

    /**
     * Classe personnalisée pour déterminer si deux perspectives sont égales
     * @param o Perspective à comparer
     * @return vrai si les objets sont égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perspective that = (Perspective) o;
        return image.getHeight() == that.image.getHeight() && image.getWidth() == that.image.getWidth() && Objects.equal(getImage(), that.getImage()) && Objects.equal(getCoordinates(), that.getCoordinates()) && Objects.equal(history, that.history);
    }

    /**
     * Méthode de clonage pour définir les perspectives distinctes pour chaque panneau
     * @return Clone de la perspective
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Perspective perspectiveClone = (Perspective) super.clone();
        Image clonedImage = (Image) perspectiveClone.getImage().clone();

        perspectiveClone.setImage(clonedImage);
        perspectiveClone.setObservers(new ArrayList<>());
        perspectiveClone.setHistory(new History());

        return perspectiveClone;
    }

    /**
     * Méthode écrasée pour retourner un code de hachage correct
     * @return code de hachage correct
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getImage(), getCoordinates(), history, image.getHeight(), image.getWidth());
    }

    /**
     * Méthode de sauvegarde concrète pour prendre un instantané de la perspective et le pousser vers l'historique.
     * @return un instantané de la perspective
     */
    @Override
    public Memento save() {
        Memento snapshot = new PerspectiveSnapshot(this);
        history.pushSnapshot(snapshot);

        return snapshot;
    }

    /**
     * Méthode de sauvegarde concrète pour prendre un instantané de la perspective et le pousser vers l'historique utilisé pour le redo.
     * @return un instantané de la perspective
     */
    @Override
    public Memento redoSave() {
        Memento snapshot = new PerspectiveSnapshot(this);
        history.pushRedoSnapshot(snapshot);

        return snapshot;
    }

    /**
     * Méthode undo pour sauvegarder un état de refaire et annuler l'historique.
     */
    public void undo() {
        redoSave();
        history.undo();
    }

    /**
     * Méthode Redo pour sauvegarder un état d'annulation et refaire l'historique.
     */
    public void redo() {
        save();
        history.redo();
    }

    /**
     * La méthode concrète de l'observateur attaché
     * @param o l'observateur attaché
     */
    @Override
    public void attach(IObserver o) {
        observers.add(o);
    }

    /**
     * La méthode concrète de notification des observateurs
     * @param event Type d'événement basé sur un enum
     * @param payload null
     */
    @Override
    public void notifyObservers(EventType event, Object payload) {
        for(IObserver o: observers)
            o.update(this, null);
    }

    /**
     * Méthode pour définir les observateurs pour les clones
     * @param observers Liste des observateurs
     */
    private void setObservers(ArrayList<IObserver> observers) {
        this.observers = new ArrayList<>();
    }

    /**
     * Méthode pour définir l'historique
     * @param history Histoire à définir
     */
    private void setHistory(History history) {
        this.history = history;
    }
}
