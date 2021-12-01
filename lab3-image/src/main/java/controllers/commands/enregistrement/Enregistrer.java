/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Enregistrer.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 17:16 p.m. Fixed class name typos
 * Yulia Bakaleinik 2021-11-29 11:36 p.m. Made Deserialization work
 *******************************************************/
package controllers.commands.enregistrement;

import controllers.commands.interfaces.ICommand;
import models.Image;
import models.Perspective;
import org.javatuples.Pair;
import views.JPanel.MenuSauvegarder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Permet d'enregistrer une image.
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class Enregistrer implements ICommand {
    /**
     * Appelé les valeurs de MenuSauvegarder
     * @param payload il consiste d'un Component et un string
     * @throws IOException
     */
    @Override
    public void execute(Object payload) throws IOException {
        var pair = (Pair<Component,String>)payload;

        var sender = pair.getValue0();
        var path = pair.getValue1();

        if(sender instanceof MenuSauvegarder p)
            p.accept(new SerializeSaver(),path);
    }
}
