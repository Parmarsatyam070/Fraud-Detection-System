package com.frauddetection.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;

public class SplashWindow {
    public static Scene getScene() {
        Text welcome = new Text("AI Fraud Detection Application");
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        welcome.setFill(Color.WHITE);

        Button googleBtn = new Button("Login with Google");
        Button githubBtn = new Button("Login with GitHub");
        Button facebookBtn = new Button("Login with Facebook");
        Button start = new Button("Continue without Login");

        VBox root = new VBox(30, welcome, googleBtn, githubBtn, facebookBtn, start);
        root.setPadding(new Insets(60));
        root.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        googleBtn.setOnAction(e -> openWeb("https://accounts.google.com/signin/oauth"));
        githubBtn.setOnAction(e -> openWeb("https://github.com/login/oauth/authorize"));
        facebookBtn.setOnAction(e -> openWeb("https://www.facebook.com/v10.0/dialog/oauth"));
        start.setOnAction(e -> WindowManager.switchScene(UserWindow.getScene(new com.frauddetection.model.User())));

        return new Scene(root, 700, 500);
    }
    private static void openWeb(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception ex) {}
    }
}
