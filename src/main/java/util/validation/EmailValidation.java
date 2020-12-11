package util.validation;

import java.util.regex.Pattern;

public class EmailValidation {
    private static final Pattern emailPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");

    public static boolean validateEmail(String email) {
        return emailPattern.matcher(email).matches();
    }
}
