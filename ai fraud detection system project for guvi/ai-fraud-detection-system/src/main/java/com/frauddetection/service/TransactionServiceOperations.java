package com.frauddetection.service;

import com.frauddetection.model.Transaction;
import java.util.List;
import java.sql.SQLException;

public interface TransactionServiceOperations {
    List<Transaction> fetchAll() throws SQLException;
}
