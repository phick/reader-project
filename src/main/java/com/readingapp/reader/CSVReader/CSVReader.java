package com.readingapp.reader.CSVReader;

import com.readingapp.model.Contact;
import com.readingapp.model.Customer;
import com.readingapp.reader.Reader;

import java.util.Optional;
import java.util.Scanner;

public class CSVReader implements Reader {

    private final Scanner sc;
    private final Allocator allocator;


    public CSVReader(Scanner scanner, Allocator allocator) {

        this.sc = scanner;
        this.allocator = allocator;

    }


    @Override
    public Optional <Customer> readPerson() {
        Customer customer = new Customer();

        if (sc.hasNextLine()) {

            String line = sc.nextLine();
            String[] attributes = line.split(",");

            String name = attributes[0];
            String surname = attributes[1];
            int age;

            if (attributes[2].equals(""))
                age = 0;
            else
                age = Integer.parseInt(attributes[2]);


            customer.setName(name);
            customer.setSurname(surname);
            customer.setAge(age);


            for (int i = 4; i < attributes.length; i++) {

                customer.addContact(validateAttribute(attributes[i]));
            }

            return Optional.of(customer);
        }

       return Optional.empty();
    }

    private Contact validateAttribute(String attribute) {

        String trimmedAttribute = attribute.replaceAll("\\s", "");

        int type = allocator.allocate(trimmedAttribute);

        return new Contact(type, attribute);

    }

}
