package com.bz.xmlprocessing;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLStringToDocument {
	/* TRASFORMAZIONE DA STRINGA XML A DOCUMENTO XML */
	protected Document xmlStringToDocument(String xmlString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(xmlString)));
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
}
