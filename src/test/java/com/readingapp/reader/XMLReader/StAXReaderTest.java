package com.readingapp.reader.XMLReader;

import com.readingapp.model.Contact;
import com.readingapp.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class StAXReaderTest {

    private StAXReader stAXReader;

    @BeforeEach
    void setUp() throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("dane-osoby.xml"));
        stAXReader = new StAXReader(eventReader);
    }

    @Test
    void initReader() {
        assertNotNull(stAXReader);
    }

    @Test
    void readPerson_success() throws XMLStreamException {

        Customer customer = new Customer("Jan", "Kowalski", 12);
        customer.addContact(new Contact(2, "123123123"));
        customer.addContact(new Contact(2, "654 765 765"));
        customer.addContact(new Contact(1, "kowalski@gmail.com"));
        customer.addContact(new Contact(1, "jan@gmail.com"));



       // Optional<Customer> readCustomer = stAXReader.readPerson();

      //  assertEquals(customer, readCustomer);
    }

}