package ru.rsreu.koklyukov0907.utils;

import java.io.File;
import java.util.List;

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

public class HtmlWriter {

	public HtmlWriter() {
	}
	
	public void write(List<FlatWare> cultery, String path) {
		
	}
	
	private static Element createTable(Document document, List<FlatWare> cultery) {
		Element table = document.createElement("table");
		table.setAttribute("border", "1");
		//table header
		
		for(FlatWare flatWare : cultery) {
			table.appendChild(createFlatWareTr(document, flatWare));
		}
		return table;
	}
	
	private static Element createFlatWareTr(Document document, FlatWare flatWare) {
		Element tr = document.createElement("tr");
		tr.setAttribute("id", Integer.toString(flatWare.getId()));
		pushTd(document, tr, flatWare.getOrigin());
		pushTd(document, tr, flatWare.getType().toString());
		pushTd(document, tr, String.valueOf(flatWare.isValue()));
		return tr;
	}
	
	private static void pushTd(Document document, Element tr, String value) {
		Element td = document.createElement("td");
		td.setTextContent(value);
		tr.appendChild(td);
	}
	
	private static Transformer createTransformer() throws TransformerConfigurationException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", String.valueOf(4));
		return transformer;
	}
	
	private static void saveDocument(Document document, String path) throws TransformerException {
		Transformer transformer = createTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}
}
