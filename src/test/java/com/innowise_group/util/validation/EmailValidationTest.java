package com.innowise_group.util.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmailValidationTest {

    List<String> validEmails = new ArrayList<>();
    List<String> invalidEmails = new ArrayList<>();

    @Before
    public void setUp() {
        validEmails.add("any@mail.com");
        validEmails.add("any.bob@mail.co.in");
        validEmails.add("any#@mail.me.org");
        invalidEmails.add("any.mail.com");
        invalidEmails.add("any#mail.com");
        invalidEmails.add("@mail.me.org");
        invalidEmails.add("any@.org");
        invalidEmails.add("@.org");
        invalidEmails.add("@");
    }

    @Test
    public void testValidateValidEmails() {
        boolean valid = true;
        for (String email : validEmails) {
            if (!EmailValidation.validateEmail(email)) {
                valid = false;
            }
        }
        Assert.assertTrue(valid);
    }

    @Test
    public void testValidateInvalidEmails() {
        boolean valid = false;
        for (String email : invalidEmails) {
            if (EmailValidation.validateEmail(email)) {
                valid = true;
            }
        }
        Assert.assertFalse(valid);
    }
}
