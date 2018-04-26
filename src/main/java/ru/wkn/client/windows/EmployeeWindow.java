package ru.wkn.client.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeeWindow {

    public EmployeeWindow(String information) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/employee-window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 322, 400));
        stage.setTitle("Аккаунт сотрудника");
        root.getChildren().addAll(new Label(information));
        stage.show();
    }
}
