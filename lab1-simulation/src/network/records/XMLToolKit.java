/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeure : Bianca Popa
 Nom du fichier: XMLToolKit.java
 Date créé: 2021-09-24
 Date dern. modif. 2021-09-24
 *******************************************************
 Historique des modifications (git)
 *******************************************************
 JKleinne 9/24/21, 2:43 AM Completed XMLUtils.readFactoryCoordinates()
 *******************************************************/

package network.records;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;

/**
 * Record englobant les détails pour la fonction d'assistance network.utilities.XMLUtils#getToolKit(String, String)
 * @author Jonnie Klein Quezada
 * @since 2021-09-24
 */
public record XMLToolKit(DocumentBuilder builder, Document doc, Node branch) {}
