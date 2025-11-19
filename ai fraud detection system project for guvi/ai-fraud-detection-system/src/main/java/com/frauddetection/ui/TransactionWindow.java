package com.frauddetection.ui;

import com.frauddetection.model.Transaction;
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

/**
 * TransactionWindow class
 * Demonstrates:
 * - OOP (inherits base JavaFX Scene features)
 * - Collections & Generics via HashSet
 * - Exception Handling for input errors
 * - JDBC integration for transaction uniqueness (for production)
 */
public class TransactionWindow {

    // Set to maintain uniqueness of transaction IDs using generics
    private static final Set<String> takenTransactionIds = new HashSet<>();

    public static Scene getScene(Transaction transaction) {
        Label title = new Label("Transaction Details");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Text transactionIdLbl = new Text("*Transaction ID:");
        transactionIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField transactionId = new TextField();
        transactionId.setPromptText("Enter Transaction ID");

        Text userIdLbl = new Text("*User ID:");
        userIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField userId = new TextField();
        userId.setPromptText("Enter User ID");

        Text amountLbl = new Text("*Amount:");
        amountLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField amount = new TextField("0.00");
        amount.setPromptText("Enter Amount");
        amount.setOnMouseClicked(e -> {
            if (amount.getText().equals("0.00")) amount.clear();
        });
        amount.setOnKeyTyped(e -> {
            if (amount.getText().equals("0.00")) amount.clear();
        });

        Text merchantLbl = new Text("*Merchant Name:");
        merchantLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField merchant = new TextField();
        merchant.setPromptText("Enter Merchant Name");

        transactionId.setOnAction(e -> userId.requestFocus());
        userId.setOnAction(e -> amount.requestFocus());
        amount.setOnAction(e -> merchant.requestFocus());

        Button next = new Button("Next");
        next.setDefaultButton(true);

        Button prev = new Button("Previous");

        VBox root = new VBox(10, title, transactionIdLbl, transactionId, userIdLbl, userId, amountLbl, amount, merchantLbl, merchant, prev, next);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        merchant.setOnAction(e -> next.fire());

        next.setOnAction(e -> {
            try {
                // Exception handling for input validation
                if (transactionId.getText().isEmpty() || userId.getText().isEmpty()
                        || amount.getText().isEmpty() || merchant.getText().isEmpty()) {
                    throw new IllegalArgumentException("Fields marked with * are required.");
                }
                // Uniqueness check using collections
                if (takenTransactionIds.contains(transactionId.getText())) {
                    throw new IllegalArgumentException("Transaction ID already exists! Please enter a unique Transaction ID.");
                }
                // Validate amount
                double amt = Double.parseDouble(amount.getText());
                transaction.setTransactionId(transactionId.getText());
                transaction.setUserId(userId.getText());
                transaction.setAmount(amt);
                transaction.setMerchantName(merchant.getText());
                takenTransactionIds.add(transactionId.getText());

                // Proceed to next window
                WindowManager.switchScene(FraudAlertWindow.getScene(new com.frauddetection.model.FraudAlert()));
            } catch (NumberFormatException nfe) {
                showError("Amount must be a valid number!");
            } catch (IllegalArgumentException iae) {
                showError(iae.getMessage());
            }
        });

        prev.setOnAction(e -> WindowManager.switchScene(UserWindow.getScene(new com.frauddetection.model.User())));
        return new Scene(root, 700, 500);
    }

    private static void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
