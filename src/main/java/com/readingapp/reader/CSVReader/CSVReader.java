package com.readingapp.reader.CSVReader;

import com.readingapp.model.Contact;
import com.readingapp.model.Customer;
import com.readingapp.reader.Reader;

import java.util.Objects;
import java.util.Scanner;

public class CSVReader implements Reader {

    private final Scanner sc;
    private final Validator validator;


    public CSVReader(Scanner scanner, Validator validator) {

        this.sc = scanner;
        this.validator = validator;

    }


    @Override
    public Customer readPerson() {

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


            Customer customer = new Customer(name, surname, age);

            for (int i = 4; i < attributes.length; i++) {

                customer.addContact(validateAttribute(attributes[i]));
            }

            return customer;
        }
        return null;
    }

    private Contact validateAttribute(String attribute) {

        String trimmedAttribute = attribute.replaceAll("\\s", "");

        int type = validator.validate(trimmedAttribute);

        return new Contact(type, attribute);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSVReader csvReader = (CSVReader) o;
        return Objects.equals(sc, csvReader.sc) &&
                Objects.equals(validator, csvReader.validator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sc, validator);
    }
}
