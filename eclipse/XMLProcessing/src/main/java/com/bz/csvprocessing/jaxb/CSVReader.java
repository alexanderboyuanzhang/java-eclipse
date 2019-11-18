package com.bz.csvprocessing.jaxb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.bz.beans.Names;
import com.bz.beans.NamesList;

public class CSVReader {

	public CSVReader() {
		super();
	}


	public static String csvToXMLString(String csvFile)
			throws JAXBException, ParserConfigurationException, TransformerException {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		List<Names> nameList = new ArrayList<Names>();

		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] name = line.split(cvsSplitBy);
				Names names = new Names();
				names.setId(name[0]);
				names.setFirstName(name[1]);
				names.setLastName(name[2]);
//				System.out.println(names.toString());
				nameList.add(names);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// end of exceptions

		NamesList namesList = new NamesList();

		namesList.setNames(nameList);

		JAXBContext jaxbContext = JAXBContext.newInstance(NamesList.class);
		Marshaller marshaller = jaxbContext.createMarshaller();

//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// Creating the Document object
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		marshaller.marshal(namesList, new File("c:/tmp/nameslist.xml"));
		marshaller.marshal(namesList, document);
		
		
		
		marshaller.marshal(namesList, System.out);
		
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		
		String xmlString = null;
		try {
			transformer = tf.newTransformer();

			StringWriter writer = new StringWriter();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			/* FASE DI TRASFORMAZIONE DEL DOCUMENTO ALLA STRINGA */
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			xmlString = writer.getBuffer().toString();
			/* STAMPA IL MESSAGGIO AL CONSOLE O AL LOG */
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(xmlString);
		return xmlString;
	}

}
