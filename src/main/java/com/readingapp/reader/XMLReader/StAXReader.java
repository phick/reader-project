package com.readingapp.reader.XMLReader;

import com.readingapp.model.Contact;
import com.readingapp.model.Customer;
import com.readingapp.reader.Reader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.Objects;

public class StAXReader implements Reader {

    private Customer customer;
    private final XMLEventReader reader;


    public StAXReader(XMLEventReader xmlEventReader) {

        this.reader = xmlEventReader;

    }

    @Override
    public Customer readPerson() throws XMLStreamException {

        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();

            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();

                switch (startElement.getName().getLocalPart()) {

                    case "person":
                        customer = new Customer();
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        customer.setName(nextEvent.asCharacters().getData());
                        break;
                    case "surname":
                        nextEvent = reader.nextEvent();
                        customer.setSurname(nextEvent.asCharacters().getData());
                        break;
                    case "age":
                        nextEvent = reader.nextEvent();
                        customer.setAge(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "city":
                        nextEvent = reader.nextEvent();
                        break;

                    case "phone":
                        nextEvent = reader.nextEvent();
                        customer.addContact(new Contact(2, nextEvent.asCharacters().getData()));
                        break;
                    case "email":
                        nextEvent = reader.nextEvent();
                        customer.addContact(new Contact(1, nextEvent.asCharacters().getData()));
                        break;
                    case "icq":
                        nextEvent = reader.nextEvent();
                        customer.addContact(new Contact(0, nextEvent.asCharacters().getData()));
                        break;
                    case "jabber":
                        nextEvent = reader.nextEvent();
                        customer.addContact(new Contact(3, nextEvent.asCharacters().getData()));
                        break;
                }
            }
            if (nextEvent.isEndElement()) {

                EndElement endElement = nextEvent.asEndElement();

                if (endElement.getName().getLocalPart().equals("person")) {
                    return customer;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StAXReader reader1 = (StAXReader) o;
        return Objects.equals(customer, reader1.customer) &&
                Objects.equals(reader, reader1.reader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, reader);
    }
}
