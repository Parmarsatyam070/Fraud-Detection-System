package com.frauddetection.model;

/**
 * Represents a fraud alert. Demonstrates OOP, interfaces, exception handling.
 */
public class FraudAlert implements FraudAlertOperations {
    private String alertId;
    private String transactionId;
    private String userId;
    private String reason;
    private String severity;

    public FraudAlert() {}

    public <T extends String> FraudAlert(T alertId, T transactionId, T userId, T reason, T severity) {
        this.alertId = alertId;
        this.transactionId = transactionId;
        this.userId = userId;
        this.reason = reason;
        this.severity = severity;
    }

    @Override
    public String getAlertId() { return alertId; }
    @Override
    public void setAlertId(String id) { this.alertId = id; }
    @Override
    public String getTransactionId() { return transactionId; }
    @Override
    public void setTransactionId(String id) { this.transactionId = id; }
    @Override
    public String getUserId() { return userId; }
    @Override
    public void setUserId(String id) { this.userId = id; }
    @Override
    public String getReason() { return reason; }
    @Override
    public void setReason(String reason) { this.reason = reason; }
    @Override
    public String getSeverity() { return severity; }
    @Override
    public void setSeverity(String severity) { this.severity = severity; }
}
