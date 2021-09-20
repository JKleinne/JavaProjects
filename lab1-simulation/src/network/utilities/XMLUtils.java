package network.utilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
 *    A big part of the source code here to read XML with DOM parser is built off of
 *    or taken inspiration off of publicly-available demos from tutorials.
 *
 *    Title: Mkyong's XML Parser Source Code
 *    Author: mkyong
 *    Date: 2021
 *    Availability: https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
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

        NodeList list = doc.getElementsByTagName("usine");

        var factoryMetadata = new ArrayList<FactoryMetadata>();
        var icons = new ArrayList<IconMetadata>();
        String factoryType, entryType, exitType;
        int productionInterval;

        for(int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                factoryType = element.getAttribute("type");

                //TODO Complete FactoryMetadata reader
            }
        }

        return factoryMetadata;
    }
}
