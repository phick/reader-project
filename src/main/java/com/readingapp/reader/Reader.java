package com.readingapp.reader;

import com.readingapp.model.Customer;

import javax.xml.stream.XMLStreamException;

public interface Reader {

    Customer readPerson() throws XMLStreamException;

}
