package com.innowise_group.util.validation;

import java.util.regex.Pattern;

public class PhoneNumberValidation {
    private static final Pattern phoneNumberPattern = Pattern.compile("^(375)\\d{2}\\s\\d{7}$");

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }
}
