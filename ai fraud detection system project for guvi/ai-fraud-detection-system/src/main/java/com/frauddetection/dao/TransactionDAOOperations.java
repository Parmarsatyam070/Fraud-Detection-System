package com.frauddetection.dao;

import java.util.List;
import com.frauddetection.model.Transaction;
import java.sql.SQLException;

public interface TransactionDAOOperations {
    List<Transaction> getAllTransactions() throws SQLException;
}
