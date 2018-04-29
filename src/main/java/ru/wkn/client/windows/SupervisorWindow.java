package ru.wkn.client.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupervisorWindow extends Window {

    public SupervisorWindow() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/supervisor-window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 322, 293));
        stage.setResizable(false);
        stage.setTitle("Страница руководителя");
        stage.show();
    }
}
