package network.records;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;

public record XMLToolKit(DocumentBuilder builder, Document doc, Node branch) {}
