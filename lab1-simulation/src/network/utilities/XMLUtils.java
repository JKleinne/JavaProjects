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
 JKleinne 10/12/21, 2:11 AM Refactoring: grouping relevant classes into deeper packages
 JKleinne 10/12/21, 1:51 AM Warehouse plane capacity functionality
 JKleinne 10/12/21, 12:39 AM Fixed bug that sets incorrect max component capacity
 JKleinne 10/2/21, 3:44 AM Renamed generic factory properties to facility
 JKleinne 9/25/21, 7:13 PM Initial dynamic drawing logic
 JKleinne 9/25/21, 4:16 PM Resolved NullPointerException when trying to read usine > 'sortie' from XML
 JKleinne 9/25/21, 3:38 PM Mapped coords and metadata into a config records for ease of access
 JKleinne 9/24/21, 3:21 AM Removed unused arguments from toolkit and removed XMLUtils.readXML()
 JKleinne 9/24/21, 3:10 AM Completed XMLUtils.readPathing()
 JKleinne 9/24/21, 2:43 AM Completed XMLUtils.readFactoryCoordinates()
 JKleinne 9/20/21, 5:25 AM Finished implmenting XMLUtils.readFactoryMetadata()
 JKleinne 9/20/21, 3:47 AM Created records and started XML FactoryMetadata reader
 JKleinne 9/19/21, 3:36 PM created structure of the proejct
 *******************************************************/

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

/**
 * Classe d'assistance pour la lecture et l'analyse du document XML de configuration
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
     * @throws ParserConfigurationException peut être lancé par getToolKit()
     * @throws IOException peut être lancé par getToolKit()
     * @throws SAXException peut être lancé par getToolKit()
     */
    public static ArrayList<FacilityConfig> getFacilityConfig(String filePath) throws ParserConfigurationException, IOException, SAXException {
        var factoriesMetadata = readFacilityMetadata(filePath);
        var factoriesCoords = readFacilityCoordinates(filePath);
        var configList = new ArrayList<FacilityConfig>();

        for(FacilityCoordinates coords : factoriesCoords) {
            FacilityMetadata metadata = factoriesMetadata.stream()
                    .filter(x -> x.facilityType().equals(coords.facilityType()))
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
     * @throws ParserConfigurationException peut être lancé par getToolKit()
     * @throws IOException peut être lancé par getToolKit()
     * @throws SAXException peut être lancé par getToolKit()
     */
    public static ArrayList<FacilityMetadata> readFacilityMetadata(String filePath) throws ParserConfigurationException, IOException, SAXException {
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
     * @throws IOException peut être lancé par getToolKit()
     * @throws SAXException peut être lancé par getToolKit()
     * @throws ParserConfigurationException peut être lancé par getToolKit()
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
     * @throws IOException peut être lancé par getToolKit()
     * @throws ParserConfigurationException peut être lancé par getToolKit()
     * @throws SAXException peut être lancé par getToolKit()
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
     * @throws IOException DocumentBuilder{instance}.parse() peut lever cette exception
     * @throws SAXException DocumentBuilder{instance}.parse() peut lever cette exception
     * @throws ParserConfigurationException DocumentBuilder{instance}.parse() peut lever cette exception
     */
    private static XMLToolKit getToolKit(String filePath, String tagName) throws IOException, ParserConfigurationException, SAXException {
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        Node metadata = doc.getElementsByTagName(tagName).item(0);

        return new XMLToolKit(builder, doc, metadata);
    }
}
