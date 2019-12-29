package com.bz.csvprocessing.jaxb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.bz.beans.Names;
import com.bz.beans.NamesList;

public class CSVWriter {

	public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException {
		String csvFile = "d:/Profiles/bzhang/Desktop/GDrive/backups/SSG/excel files/MOCK_DATA_nomi.csv";
		String csvFileOutput = "d:/Profiles/bzhang/Desktop/GDrive/backups/SSG/excel files/MOCK_DATA_nomi_output.csv";
		CSVWriter csvReader = new CSVWriter();
		csvReader.csvToXMLString(csvFile, csvFileOutput);
	}

	void csvToXMLString(String csvFile, String csvFileOutput)
			throws JAXBException, ParserConfigurationException, TransformerException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		final String lineSep = System.getProperty("line.separator");
		String line = "";
		String cvsSplitBy = ";";

		List<Names> nameList = new ArrayList<Names>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			bw = new BufferedWriter(new FileWriter(csvFileOutput));

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] name = line.split(cvsSplitBy);
				Names names = new Names();
				names.setId(name[0]);
				names.setFirstName(name[1]);
				names.setLastName(name[2]);
				nameList.add(names);
				String addedColumn1 = String.valueOf(names.getFirstName());
				String addedColumn2 = String.valueOf(names.getLastName());
				bw.write(line + cvsSplitBy + addedColumn1 + cvsSplitBy + addedColumn2 + lineSep);
				System.out.println(names.toString());
			}

			br.close();
			bw.close();

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

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Creating the Document object
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

//	    marshaller.marshal(namesList, new File("c:/tmp/nameslist.xml"));
		marshaller.marshal(namesList, document);

	}

}
