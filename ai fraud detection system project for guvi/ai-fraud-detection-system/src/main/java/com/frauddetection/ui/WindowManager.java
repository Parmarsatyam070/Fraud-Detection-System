package com.frauddetection.ui;

import javafx.stage.Stage;
import javafx.scene.Scene;

public class WindowManager {
    private static Stage primaryStage;
    public static void setPrimaryStage(Stage stage) { primaryStage = stage; }
    public static void switchScene(Scene scene) { primaryStage.setScene(scene); }
}
