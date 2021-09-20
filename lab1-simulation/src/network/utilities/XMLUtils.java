package network.utilities;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import network.records.FactoryEntryComponent;
import network.records.FactoryMetadata;
import network.records.IconMetadata;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/***************************************************************************************
 *    A part of the source code here to read XML with DOM parser is built off of
 *    or taken inspiration off of publicly-available demos from tutorials.
 *
 *    Title: Mkyong's XML Parser Source Code
 *    Author: mkyong
 *    Date: 2021
 *    Availability: https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
***************************************************************************************/

/***************************************************************************************
 *    Une partie du code source ici pour lire XML avec l'analyseur DOM est
 *    construite à partir ou inspiré des démos accéssibles au public à partir des tutoriels.
 *
 *    Titre: Mkyong's XML Parser Source Code
 *    Autheur: mkyong
 *    Date: 2021
 *    Site: https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 ***************************************************************************************/

public final class XMLUtils {
    private XMLUtils() {}

    public static NodeList readXML(String filePath, String tagName) throws ParserConfigurationException, IOException, SAXException {
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName(tagName);
    }

    public static ArrayList<FactoryMetadata> readFactoryMetadata(String filePath) throws ParserConfigurationException, IOException, SAXException {
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        // Target only "usine" within the element "metadonnees"
        Node metadata = doc.getElementsByTagName("metadonnees").item(0);
        NodeList list = ((Element) metadata).getElementsByTagName("usine");

        var factoryMetadataList = new ArrayList<FactoryMetadata>();

        for(int i = 0; i < list.getLength(); i++) {
            String factoryType = null, exitType = null;
            int productionInterval = 0;

            var icons = new ArrayList<IconMetadata>();
            var entryComponentList = new ArrayList<FactoryEntryComponent>();

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
                    Node entryNode = iconNodesList.item(j);

                    if(entryNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element entryElement = (Element) entryNode;

                        String type = entryElement.getAttribute("type");
                        String quantity = entryElement.getAttribute("quantite");

                        entryComponentList.add(new FactoryEntryComponent(type, quantity));
                    }
                }

                Element exitTypeElement =  (Element) element.getElementsByTagName("sortie").item(0);
                try {
                    exitType = exitTypeElement.getAttribute("type");
                    productionInterval = Integer.parseInt(element.getElementsByTagName("interval-production").item(0).getTextContent());
                } catch (NullPointerException n) {}
            }

            factoryMetadataList.add(new FactoryMetadata(factoryType, icons, entryComponentList, exitType, productionInterval));
        }

        return factoryMetadataList;
    }

    // TODO Implement readFactoryCoordinates()
}
