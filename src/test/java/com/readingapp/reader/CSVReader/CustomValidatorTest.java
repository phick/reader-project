package com.readingapp.reader.CSVReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomValidatorTest {

    CustomValidator customValidator;

    @BeforeEach
    void setUp() {
        customValidator = new CustomValidator();
    }

    @Test
    void validate_phone_success() {

        int expectedValue = 2;
        assertEquals(expectedValue, customValidator.validate("505505505"));
    }

    @Test
    void validate_email_success() {

        int expectedValue = 1;
        assertEquals(expectedValue, customValidator.validate("example@gmail.com"));
    }

    @Test
    void validate_jabber_success() {

        int expectedValue = 3;
        assertEquals(expectedValue, customValidator.validate("jbrxyz"));
    }

    @Test
    void validate_unknown_success() {

        int expectedValue = 0;
        assertEquals(expectedValue, customValidator.validate("01xYZ"));
    }
}