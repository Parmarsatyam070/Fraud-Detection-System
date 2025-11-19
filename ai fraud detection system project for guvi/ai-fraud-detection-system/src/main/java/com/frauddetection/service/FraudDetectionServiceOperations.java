package com.frauddetection.service;

import com.frauddetection.model.Transaction;

public interface FraudDetectionServiceOperations {
    boolean isFraudulent(Transaction transaction);
}
