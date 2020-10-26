package com.readingapp.reader.CSVReader;


import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class CustomValidator implements Validator {

    private final EmailValidator emailValidator;


    public CustomValidator() {

        this.emailValidator = EmailValidator.getInstance();
    }

    @Override
    public int validate(String attribute) {


        String phoneNumberPattern = "[0-9]{9}";

        if (attribute.matches(phoneNumberPattern))
            return 2;

        else if (emailValidator.isValid(attribute))
            return 1;

        else if (attribute.startsWith("jbr"))
            return 3;

        else {
            return 0;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomValidator that = (CustomValidator) o;
        return Objects.equals(emailValidator, that.emailValidator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailValidator);
    }
}
