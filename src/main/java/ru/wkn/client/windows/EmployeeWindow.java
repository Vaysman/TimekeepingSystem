package ru.wkn.client.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeWindow extends Window {

    public EmployeeWindow() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/employee-window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 322, 371));
        stage.setResizable(false);
        stage.setTitle("Страница пользователя");
        stage.show();
    }
}
