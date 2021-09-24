package network.utilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import network.records.*;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
        var toolkit = getToolKit(filePath, "metadonnees");

        DocumentBuilder builder = toolkit.builder();
        Document doc = toolkit.doc();
        Node branch = toolkit.branch();

        NodeList list = ((Element) branch).getElementsByTagName("usine");

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
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            factoryMetadataList.add(new FactoryMetadata(factoryType, icons, entryComponentList, exitType, productionInterval));
        }

        return factoryMetadataList;
    }

    public static ArrayList<FactoryCoordinates> readFactoryCoordinates(String filePath) throws IOException, SAXException, ParserConfigurationException {
        var toolkit = getToolKit(filePath, "simulation");

        DocumentBuilder builder = toolkit.builder();
        Document doc = toolkit.doc();
        Node branch = toolkit.branch();

        NodeList list = ((Element) branch).getElementsByTagName("usine");

        var factoryCoords = new ArrayList<FactoryCoordinates>();

        for(int i = 0; i < list.getLength(); i++) {
            String factoryType = null;
            int id = 0, x = 0, y = 0;

            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                factoryType = element.getAttribute("type");
                id = Integer.parseInt(element.getAttribute("id"));
                x = Integer.parseInt(element.getAttribute("x"));
                y = Integer.parseInt(element.getAttribute("y"));

                factoryCoords.add(new FactoryCoordinates(factoryType, id, x, y));
            }
        }

        return factoryCoords;
    }

    public static ArrayList<Pathing> readPathing(String filePath) throws IOException, ParserConfigurationException, SAXException {
        var toolkit = getToolKit(filePath, "simulation");

        DocumentBuilder builder = toolkit.builder();
        Document doc = toolkit.doc();
        Node branch = toolkit.branch();

        NodeList list = ((Element) branch).getElementsByTagName("chemin");

        var networkPathing = new ArrayList<Pathing>();

        for(int i = 0; i < list.getLength(); i++) {
            int from = 0, to = 0;

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

    private static XMLToolKit getToolKit(String filePath, String tagName) throws IOException, SAXException, ParserConfigurationException {
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        Node metadata = doc.getElementsByTagName(tagName).item(0);

        return new XMLToolKit(builder, doc, metadata);
    }
}
