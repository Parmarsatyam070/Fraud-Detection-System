package com.frauddetection;

import com.frauddetection.ui.WindowManager;
import com.frauddetection.ui.SplashWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        WindowManager.setPrimaryStage(stage);
        stage.setTitle("AI Fraud Detection Application");
        stage.setScene(SplashWindow.getScene());
        stage.setWidth(700);
        stage.setHeight(500);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
