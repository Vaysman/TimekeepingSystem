package ru.wkn.client.windows.controllers;

import javafx.scene.control.Alert;

public class Information {

    public static void writeMessage(String name, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(name);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
