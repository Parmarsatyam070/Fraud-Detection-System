package com.frauddetection.service;

import com.frauddetection.model.Transaction;
import com.frauddetection.util.IConstants;

public class FraudDetectionService implements FraudDetectionServiceOperations {
    @Override
    public boolean isFraudulent(Transaction transaction) {
        return transaction.getAmount() > IConstants.FRAUD_SCORE_THRESHOLD * IConstants.MAX_TRANSACTION_AMOUNT;
    }
}
