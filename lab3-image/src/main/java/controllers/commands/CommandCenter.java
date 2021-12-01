/*******************************************************
 * Cours:   LOG121
 * Session: A2021
 * Groupe: 04
 * Projet: Laboratoire #3
 * Équipe: 7
 *
 *
 * Professeure : Bianca Popa
 * Nom du fichier: CommandCenter.java
 * Date créé: 2021-11-26
 * Date dern. modif. 2021-11-26
 * *****************************************************
 * Historique des modifications
 * *****************************************************
 * JKleinne 2021-11-26 21:08 p.m. Implemented Translation process (Command, Visitor, Mediator)
 * JKleinne 2021-11-26 17:55 p.m. Fixed uninitialized BiMap
 * JKleinne 2021-11-26 17:26 p.m. Created a command center as global singleton for executing commands
 *******************************************************/
package controllers.commands;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import controllers.commands.interfaces.ICommand;
import utilities.enums.CommandType;

import java.io.IOException;

/**
 * Classe singleton pour centraliser les commandes
 * @author Équipe: 7
 * @since 2021-11-26
 */
public class CommandCenter {

    private BiMap<CommandType, ICommand> commands = HashBiMap.create();

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
        private static final CommandCenter INSTANCE = new CommandCenter();
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
    public static CommandCenter getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Définir une BiMap de commandes qui peuvent être exécutées
     * @param commandType Type de commande basé sur un enum
     * @param command Commande à exécuter
     */
    public void setCommand(CommandType commandType, ICommand command) {
        commands.put(commandType, command);
    }

    /**
     * Méthode d'exécution centralisée pour toutes les commandes
     * @param commandType  Type de commande basé sur un enum
     * @param payload Objet à exécuter
     */
    public void executeCommand(CommandType commandType, Object payload) {
        var selectedCommand = commands.get(commandType);
        try {
            selectedCommand.execute(payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
