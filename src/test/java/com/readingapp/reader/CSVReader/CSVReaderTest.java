package com.readingapp.reader.CSVReader;

import com.readingapp.model.Contact;
import com.readingapp.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVReaderTest {

    CSVReader csvReader;

    @BeforeEach
    void setUp() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream("dane-osoby.txt"));
        csvReader = new CSVReader(scanner, new CustomAllocator());
    }

    @Test
    void readPerson_success() {
        Customer customer = new Customer("Jan", "Kowalski", 12);
        customer.addContact(new Contact(2, "123123123"));
        customer.addContact(new Contact(2, "654 765 765"));
        customer.addContact(new Contact(1, "kowalski@gmail.com"));
        customer.addContact(new Contact(1, "jan@gmail.com"));


        Optional<Customer> readCustomer = csvReader.readPerson();

        assertEquals(Optional.of(customer), readCustomer);

    }
}