/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: PerspectiveSnapshot.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package controllers.snapshots;

import controllers.snapshots.interfaces.Memento;
import models.Image;
import models.Perspective;

import java.awt.*;
import java.io.Serializable;

/**
 * Memento concret pour pouvoir sauvegarder un état
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class PerspectiveSnapshot implements Memento, Serializable {
    private static final long serialVersionUID = 1L;

    private Perspective originator;

    private Image image;
    private Point coordinates;

    /**
     * Prendre un instantané de toutes les informations
     * @param originator Perspective de l'image qui est sauvegardée
     */
    public PerspectiveSnapshot(Perspective originator) {
        this.originator = originator;

        this.image = originator.getImage();
        this.coordinates = originator.getCoordinates();
    }

    /**
     * Méthode de restauration pour définir la perspective de l'image en instantané
     */
    @Override
    public void restore() {
        originator.setImage(this.image);
        originator.setCoordinates(this.coordinates);
    }
}
