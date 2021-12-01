/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: ImageUtils.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package utilities;

import models.Perspective;
import controllers.interfaces.observer.IObserver;
import controllers.interfaces.observer.ISubject;
import utilities.enums.EventType;
import views.JPanel.FenetrePrincipale;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe singleton pour effectuer des actions sur la perspective et les images
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class ImageUtils implements ISubject, Serializable {
    private static final long serialVersionUID = 1L;

    private String imagePath;

    private ArrayList<IObserver> observers = new ArrayList<>();

    private ImageUtils() {}

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
     * @param payload La perspective de mettre à jour
     */
    @Override
    public void notifyObservers(EventType event, Object payload) {
        for(IObserver o: observers) {
            if(o instanceof FenetrePrincipale && event == EventType.IMAGE_UPLOAD) {
                var perspective = (Perspective) payload;
                o.update(this, perspective);
            }
        }
    }

    /**
     * CODE EMPRUNTÉ
     * Les lignes suivants sont basées sur l'article par Tom sur la façon d'initialiser un singleton à la demande
     *
     *      Titre: Fastest Thread-safe Singleton in Java
     *      Auteur: Tom
     *      Date: 2021-11-26
     *      Disponible sur: http://literatejava.com/jvm/fastest-threadsafe-singleton-jvm/
     */
    private static class InstanceHolder {
        private static final ImageUtils INSTANCE = new ImageUtils();
    }
    /**
     * CODE EMPRUNTÉ
     * Les lignes suivants sont basées sur l'article par Tom sur la façon d'initialiser un singleton à la demande
     *
     *      Titre: Fastest Thread-safe Singleton in Java
     *      Auteur: Tom
     *      Date: 2021-11-26
     *      Disponible sur: http://literatejava.com/jvm/fastest-threadsafe-singleton-jvm/
     */
    public static ImageUtils getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Renvoie le chemin de l'image
     * @return le chemin de l'image
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * Définit le chemin de l'image
     * @param imagePath le chemin de l'image
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Perspective upload() throws IOException {
        var perspective = new Perspective(imagePath);
        notifyObservers(EventType.IMAGE_UPLOAD, perspective);

        return perspective;
    }

}
