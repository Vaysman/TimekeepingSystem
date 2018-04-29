package ru.wkn.client.windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.wkn.client.ClientLauncher;
import ru.wkn.client.windows.container.Container;

public class AuthorizationWindowController {

    @FXML
    public AnchorPane authorization;
    @FXML
    public TextField loginField;
    @FXML
    public TextField passwordField;
    @FXML
    public Button logInButton;

    @FXML
    public void logInClick(ActionEvent actionEvent) {
        try {
            if ((!loginField.getText().equals("")) && (!passwordField.getText().equals(""))) {
                Container.getStrings().add(loginField.getText());
                Container.getStrings().add(passwordField.getText());
                authorization.getScene().getWindow().hide();
                ClientLauncher clientLauncher = new ClientLauncher();
                clientLauncher.launch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
