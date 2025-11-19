package com.frauddetection.ui;

import com.frauddetection.model.User;
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

public class UserWindow {

    private static final Set<String> takenUsernames = new HashSet<>();
    private static final Set<String> takenUserIds = new HashSet<>();

    private static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$");
    }

    private static void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    public static Scene getScene(User user) {
        Label title = new Label("User Details");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Text userIdLbl = new Text("*User ID:");
        userIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField userId = new TextField();
        userId.setPromptText("Enter User ID");

        Text usernameLbl = new Text("*Username:");
        usernameLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField username = new TextField();
        username.setPromptText("Enter Username");

        Text emailLbl = new Text("*Email:");
        emailLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField email = new TextField();
        email.setPromptText("Enter Email (must be @gmail.com)");

        Text passwordLbl = new Text("*Password:");
        passwordLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField password = new PasswordField();
        password.setPromptText("Enter Password");

        userId.setOnAction(e -> username.requestFocus());
        username.setOnAction(e -> email.requestFocus());
        email.setOnAction(e -> password.requestFocus());

        Button next = new Button("Next");
        next.setDefaultButton(true);

        VBox root = new VBox(10, title, userIdLbl, userId, usernameLbl, username, emailLbl, email, passwordLbl, password, next);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        password.setOnAction(e -> next.fire());

        next.setOnAction(e -> {
            if (userId.getText().isEmpty() || username.getText().isEmpty()
                    || email.getText().isEmpty() || password.getText().isEmpty()) {
                showError("Fields marked with * are required.");
                return;
            }
            if (takenUsernames.contains(username.getText())) {
                showError("Username already exists! Please choose another.");
                return;
            }
            if (takenUserIds.contains(userId.getText())) {
                showError("User ID already exists! Please choose another.");
                return;
            }
            if (!isValidPassword(password.getText())) {
                showError("Password must contain at least one uppercase, one number, and one special character.");
                return;
            }
            if (!email.getText().endsWith("@gmail.com")) {
                showError("Email must end with @gmail.com");
                return;
            }
            takenUsernames.add(username.getText());
            takenUserIds.add(userId.getText());

            user.setUserId(userId.getText());
            user.setUsername(username.getText());
            user.setEmail(email.getText());
            user.setPasswordHash(password.getText());
            WindowManager.switchScene(TransactionWindow.getScene(new com.frauddetection.model.Transaction()));
        });

        return new Scene(root, 700, 500);
    }
}
