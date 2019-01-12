package com.bz.ocstocrm;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ExecTranscodingOCSToCRM {

	// private static SimpleDateFormat sdf = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	// private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	// private static Date now = new Date();

	/*
	 * IL MODIFICATORE DEL COSTRUTTORE DEVE ESSERE 'public' PERCHE' DOVRA' ESSERE
	 * VISIBILE NELL'AMBIENTE TIBCO
	 */
	public ExecTranscodingOCSToCRM() {
		super();
		/*
		 * IL COSTRUTTORE (CHE SIA VUOTO O NON) E' OBBLIGATORIO PER CONFIGURARE LA
		 * CLASSE JAVA COME UNA RISORSA TIBCO
		 */
	}

	/*
	 * QUESTO E' IL METODO CHE VERRA' RICHIAMATO DALL'AMBIENTE TIBCO, PER QUESTO
	 * MODIVO IL MODIFICATORE DEVE RIMANERE 'public'
	 */
	public static String execXmlString(String xmlString, String rulesStr) {

		/*
		 * AL COSTRUTTORE DEL'OGGETTO REGEX CHE VERRA' PASSATA LA STRINGA DI REGOLE
		 * DOPODICHE' VERRA' RICHIAMATO UN METODO CHE CONTROLLA LA CONFORMITA' DEL
		 * FORMATO DELLA STRINGA DI REGOLE INSERITA
		 */
		RegexMatcherOCSToCRM javaRegex = new RegexMatcherOCSToCRM(rulesStr);
		String xmlString2 = null;
		try {
			/* ASSEGNARE LA STRINGA XML AL TIPO Document */
			Document document = xmlStringToDocument(xmlString);

			if (javaRegex.matchCommaSep()) {

				/*
				 * LA NORMALIZZAZIONE DEL DOCUMENTO FACILITA A SCORRERE IL DOCUMENTO XML
				 * UTILIZZANDO NodeList
				 */
				document.getDocumentElement().normalize();

				/* ASSEGNAZIONE AL NodeList */
				NodeList nList = document.getChildNodes();
				ExecTranscodingOCSToCRM setUnknownXMLStructure = new ExecTranscodingOCSToCRM();

				/* PASSATA IN INPUT LA STRINGA DI REGOLE E LA LISTA DI CAMPI E VALORI */
				setUnknownXMLStructure.modifyXmlValues(nList, rulesStr);

				/* STRINGA XML IN OUTPUT */
				xmlString2 = xmlToString(document);
//				System.out.println(xmlString2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlString2;

	}

	/* TRASFORMAZIONE DA STRINGA XML A DOCUMENTO XML */
	private static Document xmlStringToDocument(String xmlString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xmlString)));
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* TRASFORMAZIONE DA DOCUMENTO XML A STRINGA */
	private static String xmlToString(Document document) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
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

	/*
	 * QUESTA E' UNA FUNZIONE RICORSIVA. NEL QUALE VERRA' RICHIAMATA LA FUNZIONE DI
	 * TRASCODIFICA PER OGNI VALORE DEL DOCUMENTO XML, SE IN UN NODO NON COTENTIENE
	 * SOTTO ELEMENTI LA RICORSIONE NON VERRA' COINVOLTA
	 */
	private void modifyXmlValues(NodeList nList, String rulesStr) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()) {
				NodeList childList = node.getChildNodes();
				modifyXmlValues(childList, rulesStr);
			}
			if ((node.getNodeType() == Node.ELEMENT_NODE) && (node.getTextContent().startsWith("\n") == false)) {
				String tmp = node.getTextContent();
				TranscodingOCSToCRM transcoding = new TranscodingOCSToCRM();
				tmp = transcoding.transcode(tmp, rulesStr);
				node.setTextContent(tmp);
			}
		}
	}
}