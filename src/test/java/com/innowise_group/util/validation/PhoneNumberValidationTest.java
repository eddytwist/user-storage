package com.innowise_group.util.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberValidationTest {

    List<String> validPhones = new ArrayList<>();
    List<String> invalidPhones = new ArrayList<>();

    @Before
    public void setUp() {
        validPhones.add("37500 1234567");
        validPhones.add("37529 1911491");
        invalidPhones.add("+37500 1234567");
        invalidPhones.add("375001234567");
        invalidPhones.add("00 1234567");
        invalidPhones.add("001234567");
        invalidPhones.add("1234567");
        invalidPhones.add("37500 1");
        invalidPhones.add("375001");
        invalidPhones.add("37500");
        invalidPhones.add("375 1");
        invalidPhones.add("375");
    }

    @Test
    public void testValidateValidEmails() {
        boolean valid = true;
        for (String phoneNumber : validPhones) {
            if (!PhoneNumberValidation.validatePhoneNumber(phoneNumber)) {
                valid = false;
            }
        }
        Assert.assertTrue(valid);
    }

    @Test
    public void testValidateInvalidEmails() {
        boolean valid = false;
        for (String phoneNumber : invalidPhones) {
            if (PhoneNumberValidation.validatePhoneNumber(phoneNumber)) {
                valid = true;
            }
        }
        Assert.assertFalse(valid);
    }
}
