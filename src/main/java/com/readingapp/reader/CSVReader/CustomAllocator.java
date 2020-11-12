package com.readingapp.reader.CSVReader;

import org.apache.commons.validator.routines.EmailValidator;


public class CustomAllocator implements Allocator {

    private final EmailValidator emailValidator;


    public CustomAllocator() {

        this.emailValidator = EmailValidator.getInstance();
    }

    @Override
    public int allocate(String attribute) {


        String phoneNumberPattern = "[0-9]{9}";

        if (attribute.matches(phoneNumberPattern))
            return 2;

        else if (emailValidator.isValid(attribute))
            return 1;

        else if (attribute.startsWith("jbr"))
            return 3;

        else
            return 0;


    }

}
