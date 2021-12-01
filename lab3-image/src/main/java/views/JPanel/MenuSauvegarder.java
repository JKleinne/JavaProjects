/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: MenuSauvegarder.java
 * Date créé: 2021-11-28
 * Date dern. modif. 2021-11-29
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-29 8:52 p.m. Prettified codebase
 * racanellia 2021-11-29 12:16 a.m. Fixed load method and cleaned up JFileChoose menus
 * Yulia Bakaleinik 2021-11-28 11:36 p.m. Made Deserialization work
 * Yulia Bakaleinik 2021-11-28 10:29 p.m. Making Serilization Work
 *******************************************************/
package views.JPanel;

import controllers.commands.CommandCenter;
import controllers.commands.enregistrement.interfaces.ISaveElement;
import controllers.commands.enregistrement.interfaces.ISaveVisitor;
import org.javatuples.Pair;
import utilities.ImageUtils;
import utilities.enums.CommandType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * JMenuItem personnalisé pour sauvegarder les fichiers
 * @author Équipe: 7
 * @since 2021-11-28
 */
public class MenuSauvegarder extends JMenuItem implements ISaveElement {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur pour définir le titre et les actions
     * @param titre titre à définir
     */
    public MenuSauvegarder(String titre) {
        setText(titre);
        setAction();
    }

    /**
     * Événement d'action pour pouvoir sélectionner le fichier et appeler la commande de sauvegarder.
     */
    public void setAction() {
        addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Enregistrer sous");
            fileChooser.setApproveButtonText("Save");
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Créer un filtre
            FileNameExtensionFilter filtre = new FileNameExtensionFilter(".tmp", "tmp");
            fileChooser.addChoosableFileFilter(filtre);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                var payload = new Pair<>(this,selectedFile.getAbsolutePath());
                CommandCenter.getInstance().executeCommand(CommandType.SAVE, payload);
            }
        });
    }

    /**
     * Méthode d'acceptation concrète pour le patron visiteur
     * @param v l'interface du method visit
     * @param path Le chemin absolu ou l'utilisateur peut sauvegarder l'information
     * @throws IOException
     */
    @Override
    public void accept(ISaveVisitor v, String path) throws IOException {
        v.visit(path);
    }
}
