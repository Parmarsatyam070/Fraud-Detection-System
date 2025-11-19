package com.frauddetection.model;

/**
 * Transaction model with basic DB fields.
 */
public class Transaction {
    private String transactionId;
    private String userId;
    private double amount;
    private String merchantName;

    public Transaction() {}
    public Transaction(String transactionId, String userId, double amount, String merchantName) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.merchantName = merchantName;
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getMerchantName() { return merchantName; }
    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }
}
