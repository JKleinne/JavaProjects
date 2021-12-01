/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: MenuFenetre.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-27
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * aracanelli 2021-11-27 22:45 p.m. Implemented right click menu for undo and redo placeholders
 * JKleinne 2021-11-26 16:31 p.m. Added Google Guava library
 *******************************************************/
package views.JPanel;

import controllers.commands.CommandCenter;
import controllers.commands.enregistrement.interfaces.ISaveElement;
import controllers.commands.enregistrement.interfaces.ISaveVisitor;
import utilities.ImageUtils;
import utilities.enums.CommandType;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Création du menu s'affichant sur la fentetre principale.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class MenuFenetre extends JMenuBar  {
    private static final long serialVersionUID = 1L;

    private static final String MENU_IMAGE_TITRE = "Menu";
    private static final String MENU_FICHIER_IMAGE = "Nouvelle Image";
    private static final String MENU_FICHIER_QUITTER = "Quitter";

    private static final String MENU_FICHIER_SAUVEGARDER = "Sauvegarder";
    private static final String MENU_FICHIER_CHARGER = "Charger";

    /**
     * Constructeur de menu
     */
    public MenuFenetre() {
        ajouterMenuImage();
    }

    /**
     * Méthode pour ajouter des éléments au menu
     */
    private void ajouterMenuImage() {
        JMenu menu = new JMenu(MENU_IMAGE_TITRE);
        MenuNouveauImage menuNouveauImage = new MenuNouveauImage(MENU_FICHIER_IMAGE);
        MenuQuitter menuQuitter = new MenuQuitter(MENU_FICHIER_QUITTER);
        MenuSauvegarder menuSauvegarder = new MenuSauvegarder(MENU_FICHIER_SAUVEGARDER);
        MenuCharger menuCharger = new MenuCharger(MENU_FICHIER_CHARGER);

        menu.add(menuSauvegarder);
        menu.add(menuCharger);
        menu.addSeparator();
        menu.add(menuNouveauImage);
        menu.addSeparator();
        menu.add(menuQuitter);

        add(menu);
    }
}

/**
 * JMenuItem personnalisé pour selectionnez un image
 * @author Équipe: 7
 * @since 2021-11-26
 */
class MenuNouveauImage extends JMenuItem {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'élément nouveau image.
     * @param titre le titre de l'élément à définir
     */
    public MenuNouveauImage(String titre) {
        setText(titre);
        setAction();
    }

    /**
     * Événement d'action pour sélectionner et charger une nouvelle image
     */
    private void setAction() {
        addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selectionnez un Image");
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Créer un filtre
            FileNameExtensionFilter filtre = new FileNameExtensionFilter(".png", "png");
            fileChooser.addChoosableFileFilter(filtre);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                //TODO refactor ImageUtils usage
                ImageUtils.getInstance().setImagePath(selectedFile.getAbsolutePath());
                try {
                    ImageUtils.getInstance().upload();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //new GetImage(selectedFile.getAbsolutePath().toString());
                System.out.println(ImageUtils.getInstance().getImagePath());
            }
        });
    }

}

/**
 * JMenuItem personnalisé pour quitter
 * @author Équipe: 7
 * @since 2021-11-26
 */
class MenuQuitter extends JMenuItem {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'élément quitter
     * @param titre le titre de l'élément à définir
     */
    public MenuQuitter(String titre) {
        setText(titre);
        setAction();
    }

    /**
     * Événement d'action pour quitter
     */
    public void setAction() {
        addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
}
