/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: Repertoire.java
 * Date créé: 2021-11-30
 * Date dern. modif. 2021-11-29
 *******************************************************
 * Historique des modifications
 *******************************************************
 * JKleinne 2021-11-29 8:52 p.m. Prettified codebase
 * Yulia Bakaleinik 2021-11-29 11:36 p.m. Made Deserialization work
 *******************************************************/
package controllers.commands.charger;

import controllers.commands.interfaces.ICommand;
import org.javatuples.Pair;
import views.JPanel.MenuCharger;

import java.awt.*;
import java.io.IOException;
/**
 * Classe qui implémente ICommand et définit l'exécution de l'application lors du chargement d'un objet sérialisé
 * @author Équipe: 7
 * @since 2021-11-30
 */
public class Charger implements ICommand {
    /**
     * Délègue le flux d'exécution à la classe implémentant IChargeElement
     * @author Équipe: 7
     * @since 2021-11-30
     * @param payload charge utile contenant l'envoyeur et le chemin absolu de l'objet sérialisé à charger
     * @throws IOException
     */
    @Override
    public void execute(Object payload) throws IOException {
        var pair = (Pair<Component,String>) payload;

        var sender = pair.getValue0();
        var path = pair.getValue1();

        if(sender instanceof MenuCharger p)
            p.accept(new Deserialization(),path);
    }
}
