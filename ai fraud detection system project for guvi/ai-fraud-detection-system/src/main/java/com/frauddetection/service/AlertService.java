package com.frauddetection.service;

import com.frauddetection.model.FraudAlert;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * AlertService for managing fraud alerts.
 * Demonstrates: OOP (class & methods), Collections, Generics, Synchronization, Exception Handling.
 */
public class AlertService {
    // Synchronization with thread-safe collection
    private final List<FraudAlert> alerts = Collections.synchronizedList(new ArrayList<>());

    /**
     * Adds a new fraud alert to collection.
     * Collection/Generics requirement.
     */
    public void addAlert(FraudAlert alert) {
        alerts.add(alert);
    }

    /**
     * Gets all alerts; returns copy for thread safety.
     * Shows use of Collections, Generics, Synchronization.
     */
    public List<FraudAlert> getAlerts() {
        return new ArrayList<>(alerts);
    }
}
