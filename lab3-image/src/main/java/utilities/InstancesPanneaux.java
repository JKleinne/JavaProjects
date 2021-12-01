/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: InstancesPanneaux.java
 * Date créé: 2021-11-28
 * Date dern. modif. 2021-11-29
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-29 8:52 p.m. Prettified codebase
 * aracanelli 2021-11-29 7:51 p.m. Cleaned up classes and methods. Added serialVersionUID = 1L to each serializable class
 * Yulia Bakaleinik 2021-11-28 11:36 p.m. Made Deserialization work
 * Yulia Bakaleinik 2021-11-28 10:29 p.m. Making Serilization Work
 *******************************************************/
package utilities;

import views.JPanel.PanneauIcone;
import views.JPanel.PanneauImageBas;
import views.JPanel.PanneauImageTop;
import views.JPanel.PanneauInfo;

import java.io.Serializable;

/**
 * Classe singleton pour l'accès à chaque panneau
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class InstancesPanneaux implements Serializable {
    private static final long serialVersionUID = 1L;

    private PanneauImageBas panneauImageBas;
    private PanneauImageTop panneauImageTop;
    private PanneauInfo panneauInfo;
    private PanneauIcone panneauIcone;

    /**
     * Getter pour le panneau inférieur
     * @return le panneau inférieur
     */
    public PanneauImageBas getPanneauImageBas() {
        return panneauImageBas;
    }
    /**
     * Getter pour le panneau supérieur
     * @return le panneau supérieur
     */
    public PanneauImageTop getPanneauImageTop() {
        return panneauImageTop;
    }
    /**
     * Getter pour le panneau d'information
     * @return le panneau d'information
     */
    public PanneauInfo getPanneauInfo() {
        return panneauInfo;
    }
    /**
     * Getter pour le panneau d'icônes
     * @return le panneau d'icônes
     */
    public PanneauIcone getPanneauIcone() {
        return panneauIcone;
    }

    /**
     * Getter pour le panneau d'icônes
     * @param panneauImageBas le panneau d'icônes
     */
    public void setPanneauImageBas(PanneauImageBas panneauImageBas) {
        this.panneauImageBas = panneauImageBas;
        this.panneauImageBas.repaint();
    }
    /**
     * Getter pour le panneau supérieur
     * @param panneauImageTop le panneau supérieur
     */
    public void setPanneauImageTop(PanneauImageTop panneauImageTop) {
        this.panneauImageTop = panneauImageTop;
        this.panneauImageTop.repaint();
    }
    /**
     * Getter pour le panneau d'information
     * @param panneauInfo le panneau d'information
     */
       public void setPanneauInfo(PanneauInfo panneauInfo) {
        this.panneauInfo = panneauInfo;
        this.panneauInfo.repaint();
    }
    /**
     * Getter pour le panneau d'icônes
     * @param panneauIcone le panneau d'icônes
     */
    public void setPanneauIcone(PanneauIcone panneauIcone) {
        this.panneauIcone = panneauIcone;
        this.panneauIcone.repaint();
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
    private static class  InstanceHolder{
        private static final InstancesPanneaux INSTANCE = new InstancesPanneaux();
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
    public static InstancesPanneaux getInstance() {
        return InstancesPanneaux.InstanceHolder.INSTANCE;
    }

}
