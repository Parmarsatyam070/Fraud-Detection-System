package com.frauddetection.model;

public interface TransactionOperations {
    String getTransactionId();
    String getUserId();
    double getAmount();
    String getMerchantName();
    boolean isFraudulent();
    void setTransactionId(String id);
    void setUserId(String id);
    void setAmount(double amt);
    void setMerchantName(String name);
    void setFraudulent(boolean val);
}
