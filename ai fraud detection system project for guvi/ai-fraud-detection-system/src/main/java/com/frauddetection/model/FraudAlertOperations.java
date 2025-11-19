package com.frauddetection.model;

public interface FraudAlertOperations {
    String getAlertId();
    String getTransactionId();
    String getUserId();
    String getReason();
    String getSeverity();
    void setAlertId(String id);
    void setTransactionId(String id);
    void setUserId(String id);
    void setReason(String reason);
    void setSeverity(String severity);
}
