package com.readingapp.reader;

import com.readingapp.model.Customer;

import javax.xml.stream.XMLStreamException;
import java.util.Optional;

public interface Reader {

    Optional<Customer> readPerson() throws XMLStreamException;

}
