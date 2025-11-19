package com.frauddetection.exception;

/**
 * Custom exception for database errors.
 */
public class DatabaseException extends Exception {
    public DatabaseException(String message) { super(message); }
    public DatabaseException(String message, Throwable cause) { super(message, cause); }
}
