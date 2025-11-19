package com.frauddetection.util;

/**
 * Utility for validating data fields using generics and collections.
 * Demonstrates: Generics, Collections, Exception handling via custom exception.
 */
import java.util.Set;
import java.util.HashSet;

public class ValidationUtil {
    private static final Set<String> seenUsernames = new HashSet<>();

    public static <T> boolean isUnique(T value) {
        // Generic uniqueness check (for username/userID)
        return seenUsernames.add(String.valueOf(value));
    }

    public static boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$");
    }
}
