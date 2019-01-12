package com.bz.test;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.bz.csvprocessing.CsvFileToXmlString;
import com.bz.encodings.CheckEncoding;


public class Test {

	public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException, IOException {
		final String csvFilePath = "d:/Profiles/bzhang/Desktop/GDrive/backups/SSG/excel files/MOCK_DATA_nomi.csv";
String csvSeparator = ",";
		String xmlRootName = "namesList";
		String rowName = "names";
//		CsvFileToXmlString csvFileToXmlString = new CsvFileToXmlString();
		String xmlString = CsvFileToXmlString.csvFileToXmlString(csvFilePath, csvSeparator, xmlRootName, rowName);
		System.out.println(xmlString);
		
		CheckEncoding checkEncoding = new CheckEncoding();
		System.out.println(checkEncoding.isUtf8(csvFilePath));
		
		
	}

}
