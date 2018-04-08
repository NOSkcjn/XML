package ru.rsreu.koklyukov0907.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

import ru.rsreu.koklyukov0907.entities.FlatWare;

public class XmlSAXParser {

	public List<FlatWare> parseString(String xml) throws Exception {
		SAXParser parser = createSAXParser();
		InputStream source = new ByteArrayInputStream(xml.getBytes());
		SAXFlatWareParser flatWareParser = new SAXFlatWareParser();
		parser.parse(source, flatWareParser);
		return flatWareParser.getFlatWares();
	}

	private SAXParser createSAXParser() throws ParserConfigurationException,
			SAXException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		return factory.newSAXParser();
	}

	public void validate(String xmlPath, String xsdPath) throws SAXException, IOException {
		SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI)
				.newSchema(new StreamSource(xsdPath)).newValidator()
				.validate(new StreamSource(xmlPath));
	}
}
