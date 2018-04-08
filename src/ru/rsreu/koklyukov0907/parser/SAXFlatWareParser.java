package ru.rsreu.koklyukov0907.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import ru.rsreu.koklyukov0907.entities.FlatWare;
import ru.rsreu.koklyukov0907.entities.FlatWareTypes;
import ru.rsreu.koklyukov0907.entities.Visual;

public class SAXFlatWareParser extends DefaultHandler {

	private List<FlatWare> cultery;
	private FlatWare currentFlatWare = null;
	private String tag;

	public List<FlatWare> getFlatWares() {
		return cultery;
	}

	public SAXFlatWareParser() {
		cultery = new ArrayList<FlatWare>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		tag = qName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		tag = "";
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		String str = new String(ch, start, length).trim();
		if ("flatware".equals(tag)) {
			currentFlatWare = new FlatWare();
		} else if("id".equals(tag)) {
			currentFlatWare.setId(Integer.parseInt(str));
		} else if ("origin".equals(tag)) {
			currentFlatWare.setOrigin(str);
		} else if ("type".equals(tag)) {
			currentFlatWare.setType(FlatWareTypes.valueOf(str.toUpperCase()));
		} else if ("value".equals(tag)) {
			currentFlatWare.setValue(Boolean.parseBoolean(str));
		} else if ("visual".equals(tag)) {
			currentFlatWare.setVisual(new Visual());
		} else if("material".equals(tag)) {
			currentFlatWare.getVisual().setMaterial(str);
		} else if("bladeWidth".equals(tag)) {
			currentFlatWare.getVisual().setBladeWidth(Integer.parseInt(str));
		} else if("bladeLength".equals(tag)) {
			currentFlatWare.getVisual().setBladeLength(Integer.parseInt(str));
			cultery.add(currentFlatWare);
			currentFlatWare = null;
		}
	}
}
