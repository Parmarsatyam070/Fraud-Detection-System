package com.frauddetection;

import com.frauddetection.dao.TransactionDAO;
import com.frauddetection.model.Transaction;

public class TestTransactionInsert {
    public static void main(String[] args) {
        TransactionDAO dao = new TransactionDAO();
        Transaction txn = new Transaction("t202", "u202", 2500.75, "DemoMerchant");
        boolean ok = dao.saveTransaction(txn);
        System.out.println("Transaction Insert successful? " + ok);
    }
}
