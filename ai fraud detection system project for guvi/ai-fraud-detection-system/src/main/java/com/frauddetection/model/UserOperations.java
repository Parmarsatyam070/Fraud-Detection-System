package com.frauddetection.model;
public interface UserOperations {
    String getUserId();
    String getUsername();
    String getEmail();
    String getPasswordHash();
    void setUserId(String id);
    void setUsername(String name);
    void setEmail(String email);
    void setPasswordHash(String hash);
}
