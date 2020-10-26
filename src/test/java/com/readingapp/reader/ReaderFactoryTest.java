package com.readingapp.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReaderFactoryTest {

    ReaderFactory readerFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        readerFactory = new ReaderFactory();
    }

    @Test
    void createReader_xml_success() {

        Reader reader = readerFactory.createReader("xml", "dane-osoby.xml");

        assertNotNull(reader);

    }

    @Test
    void createReader_txt_success() {

        Reader reader = readerFactory.createReader("txt", "dane-osoby.xml");

        assertNotNull(reader);

    }

    @Test
    void createReader_bad_parameter_exception_throw() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> readerFactory.createReader("xyz", "dane-osoby.xml"));

    }
}