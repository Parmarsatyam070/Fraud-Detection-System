package com.frauddetection.service;

import com.frauddetection.model.FraudAlert;
import java.util.List;

public interface AlertServiceOperations {
    void addAlert(FraudAlert alert);
    List<FraudAlert> getAlerts();
}
