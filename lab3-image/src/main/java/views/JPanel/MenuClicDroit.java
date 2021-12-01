/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: MenuClicDroit.java
 * Date créé: 2021-11-27
 * Date dern. modif. 2021-11-28
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-28 01:23 a.m. Undo function now works for both panels
 * aracanelli 2021-11-28 00:45 a.m. Implementation of undo function
 * aracanelli 2021-11-27 23:22 p.m. Enabled undo after first action
 * aracanelli 2021-11-27 22:45 p.m. Implemented right click menu for undo and redo placeholders
 *******************************************************/
package views.JPanel;

import controllers.interfaces.IMediator;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Création d'un menu s'affichant lorsque l'utilisateur clique sur le bouton droit de sa souris
 * @author Équipe: 7
 * @since 2021-11-27
 */
public class MenuClicDroit extends JPopupMenu {
    private static final long serialVersionUID = 1L;

    private static final String MENU_UNDO = "Défaire";
    private static final String MENU_REDO = "Refaire";

    private IMediator undoMediator;
    private IMediator redoMediator;
    private JPanel panel;

    private MenuUndo menuUndo;
    private MenuRedo menuRedo;

    /**
     * Constructeur de clic droit
     * @param undoMediator Médiateur pour undo pour être capable d'appeler sur le clic.
     * @param redoMediator Médiateur pour redo pour pouvoir appeler sur le clic
     * @param panel Panneau à manipuler
     */
    public MenuClicDroit(IMediator undoMediator, IMediator redoMediator, JPanel panel){
        this.undoMediator = undoMediator;
        this.redoMediator = redoMediator;
        this.panel = panel;
        ajouterMenuClicDroit();
    }

    /**
     * Méthode pour ajouter des JMenuItems au menu de clic droit
     */
    private void ajouterMenuClicDroit(){
        menuRedo = new MenuRedo(MENU_REDO, redoMediator, panel);
        menuUndo = new MenuUndo(MENU_UNDO, undoMediator ,panel ,menuRedo);
        menuUndo.setEnabled(true);
        menuRedo.setEnabled(false);
        add(menuUndo);
        add(menuRedo);
    }

    /**
     * Getter pour accéder à la méthode setEnabled
     * @return menuUndo
     */
    public MenuUndo getMenuUndo() {return menuUndo;}
    /**
     * Getter pour accéder à la méthode setEnabled
     * @return menuRedo
     */
    public MenuRedo getMenuRedo() {return menuRedo;}
}

/**
 * JMenuItem personnalisé pour la commande undo
 * @author Équipe: 7
 * @since 2021-11-27
 */
class MenuUndo extends JMenuItem {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'éléments Undo.
     * @param titre le titre de l'élément à définir
     * @param undoMediator Médiateur pour appeler la méthode undo.
     * @param panel Panneau en cours de manipulation
     * @param redo l'élément Redo auquel on veut accéder setEnabled
     */
    public MenuUndo(String titre, IMediator undoMediator, JPanel panel, JMenuItem redo){
        setText(titre);
        setAction(undoMediator, panel, redo);
    }

    /**
     * Événement d'action pour appeler la commande d'annulation
     * @param undoMediator Médiateur pour appeler la méthode undo.
     * @param panel Panneau en cours de manipulation
     * @param redo l'élément Redo auquel on veut accéder setEnabled
     */
    private void setAction(IMediator undoMediator, JPanel panel, JMenuItem redo){
        addActionListener((ActionEvent e) -> {
            undoMediator.notify(panel, null);
            redo.setEnabled(true);
        });
    }
}

/**
 * JMenuItem personnalisé pour la commande redo
 * @author Équipe: 7
 * @since 2021-11-27
 */
class MenuRedo extends JMenuItem {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'éléments Redo.
     * @param titre le titre de l'élément à définir
     * @param redoMediator Médiateur pour appeler la méthode reddo.
     * @param panel Panneau en cours de manipulation
     */
    public MenuRedo(String titre, IMediator redoMediator, JPanel panel){
        setText(titre);
        setAction(redoMediator, panel);
    }

    /**
     * Événement d'action pour appeler la commande d'annulation
     * @param redoMediator Médiateur pour appeler la méthode redo.
     * @param panel Panneau en cours de manipulation
     */
    private void setAction(IMediator redoMediator, JPanel panel){
        addActionListener((ActionEvent e) -> {
            redoMediator.notify(panel, null);
            setEnabled(false);
        });
    }
}