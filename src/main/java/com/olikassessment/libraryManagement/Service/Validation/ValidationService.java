package com.olikassessment.libraryManagement.Service.Validation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

@Service
public class ValidationService {
    public String generateISBN() {
        StringBuilder isbn = new StringBuilder();
        Random random = new Random();

        // Generate the first three digits (978 or 979)
        isbn.append("978");

        // Generate the next 9 digits (excluding the last digit which is the check digit)
        for (int i = 0; i < 9; i++) {
            isbn.append(random.nextInt(10));
        }

        // Calculate and append the check digit
        int sum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        isbn.append(checkDigit);

        return isbn.toString();
    }

    public boolean isValidISBN(String isbn) {
        // Remove any hyphens or separators from the ISBN code
        isbn = isbn.replaceAll("[\\s-]+", "");

        // Check if the ISBN code consists of exactly 13 digits
        if (isbn.length() != 13 || !isbn.matches("\\d{13}")) {
            return false;
        }

        // Calculate the check digit based on the first 12 digits
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int calculatedCheckDigit = (10 - (sum % 10)) % 10;

        // Extract the actual check digit from the ISBN code
        int actualCheckDigit = Character.getNumericValue(isbn.charAt(12));

        // Compare the calculated check digit with the actual check digit
        return calculatedCheckDigit == actualCheckDigit;
    }

    public boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
