package com.readingapp.reader.CSVReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomAllocatorTest {

    CustomAllocator customValidator;

    @BeforeEach
    void setUp() {
        customValidator = new CustomAllocator();
    }

    @Test
    void validate_phone_success() {

        int expectedValue = 2;
        assertEquals(expectedValue, customValidator.allocate("505505505"));
    }

    @Test
    void validate_email_success() {

        int expectedValue = 1;
        assertEquals(expectedValue, customValidator.allocate("example@gmail.com"));
    }

    @Test
    void validate_jabber_success() {

        int expectedValue = 3;
        assertEquals(expectedValue, customValidator.allocate("jbrxyz"));
    }

    @Test
    void validate_unknown_success() {

        int expectedValue = 0;
        assertEquals(expectedValue, customValidator.allocate("01xYZ"));
    }
}