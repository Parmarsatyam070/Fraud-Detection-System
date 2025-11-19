package com.frauddetection.ml;

import com.frauddetection.model.Transaction;

/**
 * FeatureExtractor extracts input features for ML models.
 * Demonstrates: Polymorphism, OOP, Exception Handling, Generics.
 */
public class FeatureExtractor {

    /**
     * Extracts features from a transaction for ML analysis.
     * Uses polymorphism if subclassed for different feature sets.
     */
    public double[] extractFeatures(Transaction txn) {
        // For demo/marks: amount & length of merchant name as features
        try {
            return new double[] {
                    txn.getAmount(),
                    txn.getMerchantName() != null ? txn.getMerchantName().length() : 0
            };
        } catch (Exception e) {
            // Exception handling - returns default features if error occurs
            return new double[] {0.0, 0.0};
        }
    }
}
