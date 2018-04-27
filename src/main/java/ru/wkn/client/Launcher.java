package ru.wkn.client;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.wkn.client.windows.AuthorizationWindow;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            AuthorizationWindow authorizationWindow = new AuthorizationWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
