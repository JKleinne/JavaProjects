/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Image.java
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
 * JKleinne 2021-11-26 16:59 p.m. Merge remote-tracking branch 'origin/main' into rac-dev
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package models;

import com.google.common.base.Objects;
import controllers.interfaces.observer.IObserver;
import controllers.interfaces.observer.ISubject;
import controllers.snapshots.History;
import controllers.snapshots.interfaces.Originator;
import utilities.enums.EventType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import static utilities.enums.EventType.IMAGE_SIZE_CHANGED;

/**
 * La classe d'image pour sauvegarder les informations pertinentes sur l'image chargée
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class Image implements ISubject, Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private BufferedImage srcImg;
    private ArrayList<IObserver> observers = new ArrayList<>();

    private int height;
    private int width;

    /**
     * Méthode de clonage pour définir des images distinctes pour chaque panneau
     * @return Clone de l'image
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Image clone = (Image) super.clone();
        return clone;
    }

    /**
     * Constructeur d'image pour définir les paramètres appropriés
     * @param srcImg Image tampon sélectionnée
     */
    public Image(BufferedImage srcImg) {
        this.srcImg = srcImg;
        this.height = srcImg.getHeight();
        this.width = srcImg.getWidth();
    }

    /**
     * Méthode personnalisée de lecture d'objet pour permettre la sauvegarde de l'image en mémoire tampon
     * @param in ObjectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        width = (Integer)in.readObject();
        height = (Integer)in.readObject();
        observers = (ArrayList<IObserver>)in.readObject();

        srcImg = ImageIO.read(ImageIO.createImageInputStream(in));

    }

    /**
     * Méthode personnalisée d'écriture d'objet pour permettre la lecture d'une image en mémoire tampon
     * @param out ObjectOutputStream
     * @throws IOException
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {

        out.writeObject(width);
        out.writeObject(height);
        out.writeObject(observers);

        ImageIO.write(srcImg,"png",ImageIO.createImageOutputStream(out));

    }

    /**
     * Renvoie l'image mise en mémoire tampon
     * @return l'image mise en mémoire tampon
     */
    public BufferedImage getSrcImg() {
        return srcImg;
    }

    /**
     * Renvoie la hauteur de l'image
     * @return la hauteur de l'image
     */
    public int getHeight() {
        return height;
    }

    /**
     * Définit la hauteur de l'image
     * @param height Hauteur à définir
     */
    public void setHeight(int height) {
        this.height = height;
        notifyObservers(IMAGE_SIZE_CHANGED, null);
    }

    /**
     * Renvoie la largeur de l'image
     * @return la largeur de l'image
     */
    public int getWidth() {
        return width;
    }

    /**
     * Définit la largeur de l'image
     * @param width largeur à définir
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Classe personnalisée pour déterminer si deux images sont égales
     * @param o Image à comparer
     * @return  vrai si les objets sont égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return getHeight() == image.getHeight() && getWidth() == image.getWidth() && Objects.equal(getSrcImg(), image.getSrcImg());
    }

    /**
     * Méthode écrasée pour retourner un code de hachage correct
     * @return code de hachage correct
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getSrcImg(), getHeight(), getWidth());
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
}
