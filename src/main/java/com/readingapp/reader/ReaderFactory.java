package com.readingapp.reader;

import com.readingapp.reader.CSVReader.CSVReader;
import com.readingapp.reader.CSVReader.CustomAllocator;
import com.readingapp.reader.CSVReader.Allocator;
import com.readingapp.reader.XMLReader.StAXReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderFactory {


    public Reader createReader(String type, String filePath) throws FileNotFoundException, XMLStreamException {


        switch (type) {

            case "xml":

                return createXmlReader(filePath);

            case "txt":

                return createCSVReader(filePath);

            default:
                throw new IllegalArgumentException("Illegal Extension Parameter value, check filepath in config.properties");

        }
    }

    private Reader createCSVReader(String filePath) throws FileNotFoundException {


        Scanner scanner = new Scanner(new FileInputStream(filePath), "UTF-8");
        Allocator allocator = new CustomAllocator();

        return new CSVReader(scanner, allocator);
    }

    private Reader createXmlReader(String filePath) throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));

        return new StAXReader(xmlEventReader);
    }

}
