package com.frauddetection.service;

import com.frauddetection.model.Transaction;
import com.frauddetection.config.DatabaseConfig;
import com.frauddetection.exception.DatabaseException;
import java.util.*;
import java.sql.*;

/**
 * Service class for transaction DB ops.
 * Demonstrates: Collections, Generics, Exception Handling, JDBC code, DB ops class.
 */
public class TransactionService {
    private final List<Transaction> cache = new ArrayList<>();

    public List<Transaction> getAll() throws DatabaseException {
        cache.clear();
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transactions")) {

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getString("transaction_id"));
                t.setUserId(rs.getString("user_id"));
                t.setAmount(rs.getDouble("amount"));
                t.setMerchantName(rs.getString("merchant_name"));
                cache.add(t);
            }
            return cache;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch transactions!", e);
        }
    }

    /**
     * Saves a transaction using JDBC with error handling.
     */
    public boolean save(Transaction t) {
        String sql = "INSERT INTO transactions (transaction_id, user_id, amount, merchant_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTransactionId());
            ps.setString(2, t.getUserId());
            ps.setDouble(3, t.getAmount());
            ps.setString(4, t.getMerchantName());
            ps.executeUpdate();
            cache.add(t);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
