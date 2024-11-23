package org.example.authlab;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Validator {

    static final private int MIN_PASSWORD_LENGTH = 8;

    public static String validatePassword(String password) {
        if (password.isEmpty()) {
            return "The password cannot be empty.";
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            return "The password must be at least " + MIN_PASSWORD_LENGTH + " characters long.";
        }

        if (!password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[0-9].*") ||
                !password.matches(".*[-+_!@#$%^&*].*")) {
            return "The password must contain at least one lowercase letter, one uppercase letter, one digit, and one of the following characters: -+_!@#$%^&*";
        }

        return null; // Validation passed
    }

    public static String validateEmail(String email) {
        if (email.isEmpty()) {
            return "The email cannot be empty. Please enter a valid email address.";
        }

        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

        if (!email.matches(emailPattern)) {
            if (!email.contains("@")) {
                return "The email is missing an '@' symbol.";
            } else if (!email.contains(".")) {
                return "The email domain is missing a '.' symbol.";
            } else if (!email.matches("^[a-zA-Z0-9._%+-]+@.*$")) {
                return "The email name must contain valid characters.";
            } else if (!email.matches("^.*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
                return "The email domain or extension is invalid. Domain extensions must be 2-4 letters long.";
            } else {
                return "Invalid email pattern. Please follow the format 'name@domain.com'.";
            }
        }

        return null; // Validation passed
    }

    public static String validateCredentials(String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match.";
        }

        String emailError = validateEmail(email);
        if (emailError != null) {
            return emailError;
        }

        String passwordError = validatePassword(password);
        if (passwordError != null) {
            return passwordError;
        }

        return null; // All validations passed
    }

}