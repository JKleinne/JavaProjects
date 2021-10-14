package network.utilities;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import network.records.*;
import network.records.facility.FacilityConfig;
import network.records.facility.FacilityCoordinates;
import network.records.facility.FacilityEntryComponent;
import network.records.facility.FacilityMetadata;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/******************************************************
 Cours:   LOG121
 Session: A2021
 Groupe: 04
 Projet: Laboratoire #1
 Étudiant: Jonnie Klein Quezada


 Professeur : Benoit Galarneau
 Nom du fichier: XMLUtils.java
 Date créé: 2021-09-19
 Date dern. modif. 2021-10-12
 *******************************************************
 Historique des modifications
 *******************************************************
 JKleinne 21/10/21, 02h11 Refactoring: regrouper les classes pertinentes dans des packages plus profonds
 JKleinne 10/12/21, 01h51 Fonctionnalité de capacité de l'avion d'entrepôt
 JKleinne 10/12/21, 00h39 Correction d'un bug qui définit la capacité maximale des composants incorrecte
 JKleinne 10/2/21,  03h44 Renommer les propriétés d'usine génériques en installation
 JKleinne 25/09/21, 19h13 Logique de dessin dynamique initiale
 JKleinne 25/09/21, 16h16 Résolution de l'exception NullPointerException lors de la tentative de lecture usine > 'sortie' à partir de XML
 JKleinne 25/09/21, 15h38 Coords et métadonnées mappés dans des enregistrements de configuration pour un accès facile
 JKleinne 24/09/21, 03h21 Suppression des arguments inutilisés de la boîte à outils et suppression de XMLUtils.readXML()
 JKleinne 24/09/21, 03h10 Terminé XMLUtils.readPathing()
 JKleinne 24/09/21, 02h43 Terminé XMLUtils.readFactoryCoordinates()
 JKleinne 20/09/21, 5h25 Fini l'implémentation de XMLUtils.readFactoryMetadata()
 JKleinne 20/09/21, 03h47 Création d'enregistrements et démarrage du lecteur XML FactoryMetadata
 JKleinne 19/09/21, 15h36 a créé la structure du projet
 *******************************************************/

/**
 * Classe d'assistance pour la lecture et l'analyse du document XML de configuration
 *
 * @author Jonnie Klein Quezada
 * @since 2021-09-19
 */
public final class XMLUtils {

    /**
     * Empêcher l'initialisation accidentelle
     */
    private XMLUtils() {}

    /**
     * Mappe les coordonnées {@link network.records.facility.FacilityCoordinates} et les métadonnées
     * {@link network.records.facility.FacilityMetadata} de chaque usine
     * dans un objet {@link network.records.facility.FacilityConfig}
     * @param filePath Le chemin du fichier de configuration XML
     * @return Un ArrayList de {@link network.records.facility.FacilityConfig} contenant les métadonnées et les coordonnées de chaque Facility
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static ArrayList<FacilityConfig> getFactoryConfig(String filePath) throws ParserConfigurationException, IOException, SAXException {
        var factoriesMetadata = readFactoryMetadata(filePath);
        var factoriesCoords = readFacilityCoordinates(filePath);
        var configList = new ArrayList<FacilityConfig>();

        for(FacilityCoordinates coords : factoriesCoords) {
            FacilityMetadata metadata = factoriesMetadata.stream()
                    .filter(x -> x.factoryType().equals(coords.factoryType()))
                    .findFirst()
                    .orElse(null);

            configList.add(new FacilityConfig(coords, metadata));
        }

        return configList;
    }

    /**
     * Mappe les métadonnées de chaque usine
     * dans un objet {@link network.records.facility.FacilityMetadata}
     * @param filePath Le chemin du fichier de configuration XML
     * @return Un ArrayList de {@link network.records.facility.FacilityMetadata} contenant les métadonnées de chaque usine
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static ArrayList<FacilityMetadata> readFactoryMetadata(String filePath) throws ParserConfigurationException, IOException, SAXException {
        var toolkit = getToolKit(filePath, "metadonnees");

        Node branch = toolkit.branch();

        NodeList list = ((Element) branch).getElementsByTagName("usine");

        var factoryMetadataList = new ArrayList<FacilityMetadata>();

        for(int i = 0; i < list.getLength(); i++) {
            String factoryType = null, exitType = null;
            int productionInterval = 0;

            var icons = new ArrayList<IconMetadata>();
            var entryComponentList = new ArrayList<FacilityEntryComponent>();

            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                factoryType = element.getAttribute("type");
                NodeList iconNodesList = element.getElementsByTagName("icone");

                // Loop through each icon and create a new IconMetadata
                for(int j = 0; j < iconNodesList.getLength(); j++) {
                    Node iconNode = iconNodesList.item(j);

                    if(iconNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element iconElement = (Element) iconNode;

                        String type = iconElement.getAttribute("type");
                        String path = iconElement.getAttribute("path");

                        icons.add(new IconMetadata(type, path));
                    }
                }

                NodeList entryNodeList = element.getElementsByTagName("entree");

                // Loop through each entry and create a new FactoryEntryComponent
                for(int j = 0; j < entryNodeList.getLength(); j++) {
                    Node entryNode = entryNodeList.item(j);

                    if(entryNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element entryElement = (Element) entryNode;

                        String type = entryElement.getAttribute("type");
                        int quantity = 0;


                        if(!entryElement.getAttribute("quantite").equals(""))
                            quantity = Integer.parseInt(entryElement.getAttribute("quantite"));
                        else if(!entryElement.getAttribute("capacite").equals(""))
                            quantity = Integer.parseInt(entryElement.getAttribute("capacite"));


                        entryComponentList.add(new FacilityEntryComponent(type, quantity));
                    }
                }

                Element exitTypeElement =  (Element) element.getElementsByTagName("sortie").item(0);
                if(exitTypeElement != null) {
                    exitType = exitTypeElement.getAttribute("type");
                    productionInterval = Integer.parseInt(element.getElementsByTagName("interval-production").item(0).getTextContent());
                }
            }

            factoryMetadataList.add(new FacilityMetadata(factoryType, icons, entryComponentList, exitType, productionInterval));
        }

        return factoryMetadataList;
    }

    /**
     * Mappe les coordonnées de chaque usine
     * dans un objet {@link network.records.facility.FacilityCoordinates}
     * @param filePath Le chemin du fichier de configuration XML
     * @return Un ArrayList de {@link network.records.facility.FacilityCoordinates} contenant les coordonnées de chaque usine
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static ArrayList<FacilityCoordinates> readFacilityCoordinates(String filePath) throws IOException, SAXException, ParserConfigurationException {
        var toolkit = getToolKit(filePath, "simulation");

        Node branch = toolkit.branch();

        NodeList list = ((Element) branch).getElementsByTagName("usine");

        var facilityCoords = new ArrayList<FacilityCoordinates>();

        for(int i = 0; i < list.getLength(); i++) {
            String factoryType;
            int id, x, y;

            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                factoryType = element.getAttribute("type");
                id = Integer.parseInt(element.getAttribute("id"));
                x = Integer.parseInt(element.getAttribute("x"));
                y = Integer.parseInt(element.getAttribute("y"));

                facilityCoords.add(new FacilityCoordinates(factoryType, id, x, y));
            }
        }

        return facilityCoords;
    }

    /**
     * Mappe le cheminement de chaque composant créé par chaque usine
     * dans un objet {@link network.records.Pathing}
     * @param filePath Le chemin du fichier de configuration XML
     * @return Un ArrayList de {@link network.records.Pathing} contenant le cheminement de chaque composant
     * créé par chaque usine
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static ArrayList<Pathing> readPathing(String filePath) throws IOException, ParserConfigurationException, SAXException {
        var toolkit = getToolKit(filePath, "simulation");

        Node branch = toolkit.branch();

        NodeList list = ((Element) branch).getElementsByTagName("chemin");

        var networkPathing = new ArrayList<Pathing>();

        for(int i = 0; i < list.getLength(); i++) {
            int from, to;

            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                from = Integer.parseInt(element.getAttribute("de"));
                to = Integer.parseInt(element.getAttribute("vers"));

                networkPathing.add(new Pathing(from, to));
            }
        }

        return networkPathing;
    }

    /**
     * Fonction d'assistance qui exécute le code boilerplate
     * (Voir <a href="https://en.wikipedia.org/wiki/Boilerplate_code#:~:text=In%20computer%20programming%2C%20boilerplate%20code,Such%20code%20is%20called%20boilerplate.">Wikipedia</a>)
     * nécessaire pour la lecture XML et renvoie le Node d'un tagName donné
     * @param filePath Le chemin du fichier de configuration XML
     * @param tagName Section dans configuration.xml (metadonnees; simulation)
     * @return Un object {@link network.records.XMLToolKit} qui contient un DocumentBuilderFactory, un DocumentBuilder
     * et des métadonnées du Node spécifié
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private static XMLToolKit getToolKit(String filePath, String tagName) throws IOException, SAXException, ParserConfigurationException {
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        Node metadata = doc.getElementsByTagName(tagName).item(0);

        return new XMLToolKit(builder, doc, metadata);
    }
}
