package ru.wkn.client.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationWindow {

    public AuthorizationWindow() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/authorization-window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 350, 230));
        stage.setTitle("Авторизация");
        stage.show();
    }
}
