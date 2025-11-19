package com.frauddetection.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages DB connection for all DAOs.
 */
public class DatabaseConfig {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/fraud_detection_db";
    public static final String USER = "root";
    public static final String PASS = "Parmarsatyam09@#"; // <-- Put your password here

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
