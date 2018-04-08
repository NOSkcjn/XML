package ru.rsreu.koklyukov0907.utils;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.rsreu.koklyukov0907.entities.FlatWare;
import ru.rsreu.koklyukov0907.entities.Visual;

public class XmlWriter {

	private XmlWriter() {
	}

	public static void write(List<FlatWare> cultery, String path)
			throws Exception {
		Document document = createEmptyDocument();
		Element element = createFlatWares(document, cultery);
		element.setAttribute("xmlns", "http://www.w3.org/2001/XMLSchema/second");
		document.appendChild(element);
		saveDocument(document, path);
	}

	private static Document createEmptyDocument()
			throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.newDocument();
	}

	private static Transformer createTransformer()
			throws TransformerConfigurationException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", String.valueOf(4));
		return transformer;
	}

	private static void saveDocument(Document document, String path)
			throws TransformerException {
		Transformer transformer = createTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}

	private static void createSimpleElement(Document document, Element parent,
			String tagName, String text) {
		Element element = document.createElement(tagName);
		element.setTextContent(text);
		parent.appendChild(element);
	}

	private static Element createFlatWares(Document document,
			List<FlatWare> cultery) {
		Element element = document.createElement("FlatWares");

		for (FlatWare flatWare : cultery) {
			element.appendChild(createFlatWare(document, flatWare));
		}
		return element;
	}

	private static Element createFlatWare(Document document, FlatWare flatWare) {
		Element element = document.createElement("FlatWare");
		element.setAttribute("id", Integer.toString(flatWare.getId()));
		createSimpleElement(document, element, "origin", flatWare.getOrigin());
		createSimpleElement(document, element, "type", flatWare.getType()
				.toString());
		createSimpleElement(document, element, "value",
				String.valueOf(flatWare.isValue()));
		Element visualElement = createVisual(document, flatWare.getVisual());
		element.appendChild(visualElement);
		return element;
	}

	private static Element createVisual(Document document, Visual visual) {
		Element element = document.createElement("visual");
		createSimpleElement(document, element, "material", visual.getMaterial());
		createSimpleElement(document, element, "bladeWidth",
				String.valueOf(visual.getBladeWidth()));
		createSimpleElement(document, element, "bladeLength",
				String.valueOf(visual.getBladeLength()));
		return element;
	}
}
