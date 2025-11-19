package com.frauddetection.dao;

import com.frauddetection.model.Transaction;
import com.frauddetection.exception.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO extends BaseDAO {

    // Save Transaction to MySQL
    public boolean saveTransaction(Transaction txn) {
        String sql = "INSERT INTO transactions (transaction_id, user_id, amount, merchant_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txn.getTransactionId());
            ps.setString(2, txn.getUserId());
            ps.setDouble(3, txn.getAmount());
            ps.setString(4, txn.getMerchantName());
            int result = ps.executeUpdate();
            System.out.println("Transaction Insert result: " + result);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve Transactions from MySQL
    public List<Transaction> getAllTransactions() throws DatabaseException {
        List<Transaction> txns = new ArrayList<>();
        String sql = "SELECT transaction_id, user_id, amount, merchant_name FROM transactions";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getString("transaction_id"));
                t.setUserId(rs.getString("user_id"));
                t.setAmount(rs.getDouble("amount"));
                t.setMerchantName(rs.getString("merchant_name"));
                txns.add(t);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error fetching transactions", e);
        }
        return txns;
    }
}
