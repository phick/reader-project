package com.readingapp.reader;

import com.readingapp.reader.CSVReader.CSVReader;
import com.readingapp.reader.CSVReader.CustomValidator;
import com.readingapp.reader.CSVReader.Validator;
import com.readingapp.reader.XMLReader.StAXReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderFactory {


    public Reader createReader(String type, String filePath) {

        if (type.equals("xml"))
            return createXmlReader(filePath);
        else if (type.equals("txt"))
            return createCSVReader(filePath);
        else
            throw new IllegalArgumentException("Illegal Parameter value");
    }


    private Reader createCSVReader(String filePath) {

        Scanner scanner;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            scanner = new Scanner(inputStream, "UTF-8");

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
            return null;
        }

        Validator validator = new CustomValidator();

        return new CSVReader(scanner, validator);
    }

    private Reader createXmlReader(String filePath) {

        XMLEventReader xmlEventReader;

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));

        } catch (XMLStreamException e) {
            System.out.println("XMLStream Exception" + e.getMessage());
            return null;
        } catch (FileNotFoundException e) {
            System.out.println("File not found Exception" + e.getMessage());
            return null;
        }

        return new StAXReader(xmlEventReader);
    }

}
