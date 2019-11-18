package com.bz.xmlprocessing;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class DocumentToString {

	/* TRASFORMAZIONE DA DOCUMENTO XML A STRINGA */
	public String documentToString(Document document) throws TransformerConfigurationException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		String xmlString = null;
		try {
			transformer = tf.newTransformer();

			StringWriter writer = new StringWriter();

			/* FASE DI TRASFORMAZIONE DEL DOCUMENTO ALLA STRINGA */
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			xmlString = writer.getBuffer().toString();
			/* STAMPA IL MESSAGGIO AL CONSOLE O AL LOG */
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlString;
	}
	
}
