package com.bz.csvprocessing;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.bz.encodings.CheckEncoding;

public class CsvFileToXmlString {

	public CsvFileToXmlString() {
		super();
	}

	/* CONVERSIONE DA FILE CSV A STRINGA XML: DENTRO QUI VERRANNO INVOCATI I METODI:
	 * - isUtf8() DI CheckEncoding;
	 * - csvToDocument() DI CsvFileToXmlString; 
	 * IN INPUT:
	 * - csvFilePath: IL PERCORSO DOVE E' PRESENTE IL FILE CSV
	 * - csvSeparator: IL SEPARATORE PER CSV E' COMMA CIOE' LA VIRGOLA
	 * - xmlRootName: E' IL NOME ROOT DELL'XML SCHEMA
	 * - rowName: IL NOME TAG DI OGNI RIGA
	 * 
	 * */
	public static String csvFileToXmlString(String csvFilePath, String csvSeparator, String xmlRootName, String rowName)
			throws ParserConfigurationException, IOException {
		String xmlString = null;
		
		CheckEncoding checkEncoding = new CheckEncoding();
		if (checkEncoding.isUtf8(csvFilePath)) {
			Document newDocument = csvToDocument(csvFilePath, csvSeparator, xmlRootName, rowName);
			xmlString = documentToString(newDocument);

		} else
			System.out.println("Codifica del file non ammesso, inserire un file con codifica UTF-8");
		return xmlString;
	}

	/* CONVERSIONE DA FILE CSV AL XML DOCUMENT */
	private static Document csvToDocument(String csvFilePath, String csvSeparator, String xmlRootName, String rowName)
			throws ParserConfigurationException, IOException {

		List<String> headers = new ArrayList<String>(5);

//		File file = new File("d:/Profiles/bzhang/Desktop/GDrive/backups/SSG/excel files/MOCK_DATA_nomi.csv");
		File file = new File(csvFilePath);
		BufferedReader reader = null;
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
		Document newDocument = domBuilder.newDocument();

		try {
			// Root element
			Element rootElement = newDocument.createElement(xmlRootName);
			newDocument.appendChild(rootElement);

			reader = new BufferedReader(new FileReader(file));
			int line = 0;

			String text = null;
			while ((text = reader.readLine()) != null) {

				//https://www.tutorialspoint.com/java/util/java_util_stringtokenizer.htm
				StringTokenizer stringTokenizer = new StringTokenizer(text, csvSeparator, false);
				String[] rowValues = new String[stringTokenizer.countTokens()];
				int index = 0;
				while (stringTokenizer.hasMoreTokens()) {

					String next = stringTokenizer.nextToken();
					rowValues[index++] = next;

				}

				// String[] rowValues = text.split(",");

				if (line == 0) { // Header row
					for (String col : rowValues) {
						headers.add(col);
					}
				} else { // Data row
					Element rowElement = newDocument.createElement(rowName);
					rootElement.appendChild(rowElement);
					for (int col = 0; col < headers.size(); col++) {
						String header = headers.get(col);
						String value = null;

						if (col < rowValues.length) {
							value = rowValues[col];
						} else {
							// ?? Default value
							value = "";
						}

						Element curElement = newDocument.createElement(header);
						curElement.appendChild(newDocument.createTextNode(value));
						rowElement.appendChild(curElement);
					}
				}
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDocument;
	}

	/* CONVERSIONE DA DOCUMENT A xmlString */
	private static String documentToString(Document newDocument) {

		ByteArrayOutputStream byteArrayOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		String xmlString = null;

		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);

			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			Source src = new DOMSource(newDocument);
			Result result = new StreamResult(outputStreamWriter);
			aTransformer.transform(src, result);

			outputStreamWriter.flush();
			xmlString = new String(byteArrayOutputStream.toByteArray());
//			System.out.println(new String(baos.toByteArray()));
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			try {
				outputStreamWriter.close();
			} catch (Exception e) {
			}
			try {
				byteArrayOutputStream.close();
			} catch (Exception e) {
			}
		}
		return xmlString;
	}

}