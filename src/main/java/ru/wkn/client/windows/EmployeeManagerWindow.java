package ru.wkn.client.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeManagerWindow extends Window {

    public EmployeeManagerWindow() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/employee-manager-window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 568, 387));
        stage.setResizable(false);
        stage.setTitle("Редактор пользователей");
        stage.show();
    }
}
