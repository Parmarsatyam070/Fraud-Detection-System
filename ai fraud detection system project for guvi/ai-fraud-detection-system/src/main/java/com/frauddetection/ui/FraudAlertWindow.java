package com.frauddetection.ui;

import com.frauddetection.model.FraudAlert;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.HashSet;
import java.util.Set;

public class FraudAlertWindow {
    private static final Set<String> takenAlertIds = new HashSet<>();

    private static void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    public static Scene getScene(FraudAlert alert) {
        Label title = new Label("Fraud Alert Details");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Text alertIdLbl = new Text("*Alert ID:");
        alertIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField alertId = new TextField();
        alertId.setPromptText("Enter Alert ID");

        Text transactionIdLbl = new Text("*Transaction ID:");
        transactionIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField transactionId = new TextField();
        transactionId.setPromptText("Enter Transaction ID");

        Text userIdLbl = new Text("*User ID:");
        userIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField userId = new TextField();
        userId.setPromptText("Enter User ID");

        Text reasonLbl = new Text("*Reason (min 30 chars):");
        reasonLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField reason = new TextField();
        reason.setPromptText("Enter Reason");

        Text severityLbl = new Text("*Severity:");
        severityLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField severity = new TextField();
        severity.setPromptText("Enter Severity");

        alertId.setOnAction(e -> transactionId.requestFocus());
        transactionId.setOnAction(e -> userId.requestFocus());
        userId.setOnAction(e -> reason.requestFocus());
        reason.setOnAction(e -> severity.requestFocus());

        Button prev = new Button("Previous");
        Button finish = new Button("Finish");
        finish.setDefaultButton(true);

        VBox root = new VBox(10, title, alertIdLbl, alertId, transactionIdLbl, transactionId, userIdLbl, userId, reasonLbl, reason, severityLbl, severity, prev, finish);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));

        severity.setOnAction(e -> finish.fire());

        finish.setOnAction(e -> {
            if (alertId.getText().isEmpty() || transactionId.getText().isEmpty()
                    || userId.getText().isEmpty() || reason.getText().isEmpty() || severity.getText().isEmpty()) {
                showError("Fields marked with * are required.");
                return;
            }
            if (takenAlertIds.contains(alertId.getText())) {
                showError("Alert ID already exists! Please enter a unique Alert ID.");
                return;
            }
            if (reason.getText().length() < 30) {
                showError("Reason must be at least 30 characters long.");
                return;
            }
            alert.setAlertId(alertId.getText());
            alert.setTransactionId(transactionId.getText());
            alert.setUserId(userId.getText());
            alert.setReason(reason.getText());
            alert.setSeverity(severity.getText());
            takenAlertIds.add(alertId.getText());
            new Alert(Alert.AlertType.INFORMATION, " Successfully you apply for fraud detection ").showAndWait();
            WindowManager.switchScene(SplashWindow.getScene());
        });

        prev.setOnAction(e -> WindowManager.switchScene(TransactionWindow.getScene(new com.frauddetection.model.Transaction())));
        return new Scene(root, 700, 500);
    }
}
