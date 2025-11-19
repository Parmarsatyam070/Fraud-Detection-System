module com.frauddetection {
        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.web;
        requires java.sql;
    requires java.desktop;
    opens com.frauddetection to javafx.fxml;
        exports com.frauddetection;
        }
