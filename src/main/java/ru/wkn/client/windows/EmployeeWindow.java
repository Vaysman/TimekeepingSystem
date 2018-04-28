package ru.wkn.client.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class EmployeeWindow extends Window {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public EmployeeWindow(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws Exception {
        super(dataInputStream, dataOutputStream);
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/employee-window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 322, 400));
        stage.setTitle("Страница пользователя");
        root.getChildren().addAll(new Label(dataInputStream.readUTF()));
        stage.show();
    }
}
